package com.cuma.yildiz.LibrarySpring.dao.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author , Long> {
}
