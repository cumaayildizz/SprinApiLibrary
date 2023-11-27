package com.cuma.yildiz.LibrarySpring.business.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category save(Category category);

    Category get(long id);

    Category update(Category category);

    boolean delete(long id);

    Page<Category> cursor(int page , int pageSize);
}
