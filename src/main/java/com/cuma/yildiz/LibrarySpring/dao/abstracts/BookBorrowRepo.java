package com.cuma.yildiz.LibrarySpring.dao.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowRepo extends JpaRepository<BookBorrow, Long> {
}
