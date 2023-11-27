package com.cuma.yildiz.LibrarySpring.dao.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book , Long> {
}
