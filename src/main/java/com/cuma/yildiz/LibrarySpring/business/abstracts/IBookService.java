package com.cuma.yildiz.LibrarySpring.business.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);

    Book get(long id);

    Book update(Book book);

    boolean delete(long id);

    Page<Book> cursor(int page , int pageSize);
}
