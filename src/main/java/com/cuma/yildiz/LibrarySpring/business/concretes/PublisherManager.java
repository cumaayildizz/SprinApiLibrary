package com.cuma.yildiz.LibrarySpring.business.concretes;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IPublisherService;
import com.cuma.yildiz.LibrarySpring.core.config.Exceptions.NotFoundException;
import com.cuma.yildiz.LibrarySpring.core.utils.Messages;
import com.cuma.yildiz.LibrarySpring.dao.abstracts.PublisherRepo;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(long id) {
        return this.publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(long id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page , pageSize);
        return this.publisherRepo.findAll(pageable);
    }
}
