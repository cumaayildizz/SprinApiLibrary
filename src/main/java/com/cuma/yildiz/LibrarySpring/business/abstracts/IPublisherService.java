package com.cuma.yildiz.LibrarySpring.business.abstracts;

import com.cuma.yildiz.LibrarySpring.entities.Author;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher save(Publisher publisher);

    Publisher get(long id);

    Publisher update(Publisher publisher);

    boolean delete(long id);

    Page<Publisher> cursor(int page , int pageSize);

}
