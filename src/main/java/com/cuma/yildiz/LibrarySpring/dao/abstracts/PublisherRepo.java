package com.cuma.yildiz.LibrarySpring.dao.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {

}
