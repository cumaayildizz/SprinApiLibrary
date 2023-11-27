package com.cuma.yildiz.LibrarySpring.dao.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category , Long> {
}
