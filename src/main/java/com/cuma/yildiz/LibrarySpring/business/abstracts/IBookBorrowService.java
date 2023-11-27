package com.cuma.yildiz.LibrarySpring.business.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.BookBorrow;
import org.springframework.data.domain.Page;

public interface IBookBorrowService {

    BookBorrow save(BookBorrow bookBorrow);

    BookBorrow get(long id);

    BookBorrow update(BookBorrow book);

    boolean delete(long id);

    Page<BookBorrow> cursor(int page , int pageSize);
}
