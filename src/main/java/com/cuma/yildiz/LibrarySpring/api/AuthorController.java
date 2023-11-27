package com.cuma.yildiz.LibrarySpring.api;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IAuthorService;
import com.cuma.yildiz.LibrarySpring.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.LibrarySpring.core.result.Result;
import com.cuma.yildiz.LibrarySpring.core.result.ResultData;
import com.cuma.yildiz.LibrarySpring.core.utils.ResultHelper;
import com.cuma.yildiz.LibrarySpring.dto.request.author.AuthorSaveRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.author.AuthorUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.response.CursorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.author.AuthorResponse;
import com.cuma.yildiz.LibrarySpring.entities.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/librarySpring/authors")
public class AuthorController  {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapperService) {
        this.authorService = authorService;
        this.modelMapper = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest , Author.class);
        this.authorService.save(saveAuthor);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(saveAuthor , AuthorResponse.class);
        return ResultHelper.created(authorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id){
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(author , AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page ,
            @RequestParam(name = "pageSize" , required = false , defaultValue = "2") int pageSize)
    {
        Page<Author> authorPage = this.authorService.cursor(page , pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(author -> this.modelMapper.forResponse()
                        .map(author , AuthorResponse.class));

        return ResultHelper.cursorMethod(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        //this.authorService.get(authorUpdateRequest.getId()); //
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest , Author.class);
        this.authorService.update(updateAuthor);

        AuthorResponse authorResponse = this.modelMapper.forResponse().map(updateAuthor , AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.authorService.delete(id);
        return ResultHelper.Ok();
    }

}
