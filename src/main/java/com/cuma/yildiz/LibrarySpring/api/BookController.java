package com.cuma.yildiz.LibrarySpring.api;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IAuthorService;
import com.cuma.yildiz.LibrarySpring.business.abstracts.IBookService;
import com.cuma.yildiz.LibrarySpring.business.abstracts.IPublisherService;
import com.cuma.yildiz.LibrarySpring.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.LibrarySpring.core.result.Result;
import com.cuma.yildiz.LibrarySpring.core.result.ResultData;
import com.cuma.yildiz.LibrarySpring.core.utils.ResultHelper;
import com.cuma.yildiz.LibrarySpring.dto.request.author.AuthorUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.book.BookSaveRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.book.BookUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.response.CursorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.author.AuthorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.book.BookResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.publisher.PublisherResponse;
import com.cuma.yildiz.LibrarySpring.entities.Author;
import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/librarySpring/books")
public class BookController {
    private final IBookService bookService;
    private final IPublisherService publisherService;
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService bookService,
                          IPublisherService publisherService,
                          IAuthorService authorService,
                          IModelMapperService modelMapper)
    {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest , Book.class);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook , BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book , BookResponse.class));
    }

    @GetMapping("/{id}/publisher")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> getPublisher(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getPublisher() , PublisherResponse.class));
    }

    @GetMapping("/{id}/author")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book.getAuthor() , AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page ,
            @RequestParam(name = "pageSize" , required = false , defaultValue = "2") int pageSize)
    {
        Page<Book> bookPage = this.bookService.cursor(page , pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse()
                        .map(book , BookResponse.class));

        return ResultHelper.cursorMethod(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest , Book.class);
        this.bookService.update(updateBook);

        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook , BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.bookService.delete(id);
        return ResultHelper.Ok();
    }


}
