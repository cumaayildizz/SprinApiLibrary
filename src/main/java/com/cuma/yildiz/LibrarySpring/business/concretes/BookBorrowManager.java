package com.cuma.yildiz.LibrarySpring.business.concretes;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IBookBorrowService;
import com.cuma.yildiz.LibrarySpring.core.config.Exceptions.NotFoundException;
import com.cuma.yildiz.LibrarySpring.core.utils.Messages;
import com.cuma.yildiz.LibrarySpring.dao.abstracts.BookBorrowRepo;
import com.cuma.yildiz.LibrarySpring.dao.abstracts.BookRepo;
import com.cuma.yildiz.LibrarySpring.entities.Book;
import com.cuma.yildiz.LibrarySpring.entities.BookBorrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowManager implements IBookBorrowService {

    private final BookBorrowRepo bookBorrowRepo;

    public BookBorrowManager(BookBorrowRepo bookBorrowRepo) {
        this.bookBorrowRepo = bookBorrowRepo;
    }


    @Override
    public BookBorrow save(BookBorrow bookBorrow) {
        return this.bookBorrowRepo.save(bookBorrow);
    }

    @Override
    public BookBorrow get(long id) {
        return this.bookBorrowRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public BookBorrow update(BookBorrow bookBorrow) {
        this.get(bookBorrow.getId());
        return this.bookBorrowRepo.save(bookBorrow);
    }

    @Override
    public boolean delete(long id) {
        BookBorrow bookBorrow = this.get(id);
        this.bookBorrowRepo.delete(bookBorrow);
        return true;
    }

    @Override
    public Page<BookBorrow> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page , pageSize);
        return this.bookBorrowRepo.findAll(pageable);
    }
}
