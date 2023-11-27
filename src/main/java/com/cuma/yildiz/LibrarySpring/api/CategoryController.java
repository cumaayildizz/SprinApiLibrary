package com.cuma.yildiz.LibrarySpring.api;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IBookService;
import com.cuma.yildiz.LibrarySpring.business.abstracts.ICategoryService;
import com.cuma.yildiz.LibrarySpring.core.config.modelMapper.IModelMapperService;
import com.cuma.yildiz.LibrarySpring.core.result.Result;
import com.cuma.yildiz.LibrarySpring.core.result.ResultData;
import com.cuma.yildiz.LibrarySpring.core.utils.ResultHelper;
import com.cuma.yildiz.LibrarySpring.dto.request.Category.CategorySaveRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.Category.CategoryUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.request.book.BookUpdateRequest;
import com.cuma.yildiz.LibrarySpring.dto.response.Category.CategoryResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.CursorResponse;
import com.cuma.yildiz.LibrarySpring.dto.response.book.BookResponse;
import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/librarySpring/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IBookService bookService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService,
                              IBookService bookService,
                              IModelMapperService modelMapper)
    {
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);

        this.categoryService.save(saveCategory);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category , CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page ,
            @RequestParam(name = "pageSize" , required = false , defaultValue = "2") int pageSize)
    {
        Page<Category> categoryPage = this.categoryService.cursor(page , pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse()
                        .map(category , CategoryResponse.class));

        return ResultHelper.cursorMethod(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest , Category.class);
        this.categoryService.update(updateCategory);

        CategoryResponse categoryResponse = this.modelMapper.forResponse()
                .map(updateCategory , CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.categoryService.delete(id);
        return ResultHelper.Ok();
    }


}
