package com.cuma.yildiz.LibrarySpring.api;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IBookBorrowService;
import com.cuma.yildiz.LibrarySpring.business.abstracts.IBookService;
import com.cuma.yildiz.LibrarySpring.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.LibrarySpring.core.result.Result;
import com.cuma.yildiz.LibrarySpring.core.result.ResultData;
import com.cuma.yildiz.LibrarySpring.core.utils.ResultHelper;
import com.cuma.yildiz.LibrarySpring.dto.request.book.BookUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.bookBorrow.BookBorrowSaveRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.bookBorrow.BookBorrowUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.response.CursorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.author.AuthorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.book.BookResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.bookBorrow.BookBorrowResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.publisher.PublisherResponse;
import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.BookBorrow;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/librarySpring/book-borrows")
public class BookBorrowController {

    private final IBookService bookService;
    private final IBookBorrowService bookBorrowService;
    private final IModelMapperService modelMapper;

    public BookBorrowController(IBookService bookService,
                                IBookBorrowService bookBorrowService,
                                IModelMapperService modelMapper)
    {
        this.bookService = bookService;
        this.bookBorrowService = bookBorrowService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowResponse> save(@Valid @RequestBody BookBorrowSaveRequest bookBorrowSaveRequest){
        BookBorrow saveBookBorrow = this.modelMapper.forRequest().map(bookBorrowSaveRequest, BookBorrow.class);

        Book book = this.bookService.get(bookBorrowSaveRequest.getBookId());
        saveBookBorrow.setBook(book);

        saveBookBorrow.setReturnDate(null);
        this.bookBorrowService.save(saveBookBorrow);

        if(book.getStock() > 0){
            book.setStock(book.getStock() - 1);
        }
        
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBorrow, BookBorrowResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowResponse> get(@PathVariable("id") int id){
        BookBorrow bookBorrow = this.bookBorrowService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(bookBorrow, BookBorrowResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowResponse>> cursor(
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page ,
            @RequestParam(name = "pageSize" , required = false , defaultValue = "2") int pageSize)
    {
        Page<BookBorrow> bookBorrowPage = this.bookBorrowService.cursor(page , pageSize);
        Page<BookBorrowResponse> bookBorrowResponsePage = bookBorrowPage
                .map(bookBorrow -> this.modelMapper.forResponse()
                        .map(bookBorrow , BookBorrowResponse.class));

        return ResultHelper.cursorMethod(bookBorrowResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowResponse> update(@Valid @RequestBody BookBorrowUpdateRequest bookBorrowUpdateRequest){
        BookBorrow updateBookBorrow = this.modelMapper.forRequest()
                .map(bookBorrowUpdateRequest , BookBorrow.class);

        this.bookBorrowService.update(updateBookBorrow);

        BookBorrowResponse bookBorrowResponse = this.modelMapper.forResponse()
                .map(updateBookBorrow , BookBorrowResponse.class);
        return ResultHelper.success(bookBorrowResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.bookBorrowService.delete(id);
        return ResultHelper.Ok();
    }

}
