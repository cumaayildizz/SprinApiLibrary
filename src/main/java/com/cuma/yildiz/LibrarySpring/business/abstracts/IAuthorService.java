package com.cuma.yildiz.LibrarySpring.business.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);

    Author get(long id);

    Author update(Author author);

    boolean delete(long id);

    Page<Author> cursor(int page , int pageSize);

}
