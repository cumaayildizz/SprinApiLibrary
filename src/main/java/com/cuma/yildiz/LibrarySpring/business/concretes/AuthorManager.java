package com.cuma.yildiz.LibrarySpring.business.concretes;

import com.cuma.yildiz.LibrarySpring.business.abstracts.IAuthorService;
import com.cuma.yildiz.LibrarySpring.core.config.Exceptions.NotFoundException;
import com.cuma.yildiz.LibrarySpring.core.utils.Messages;
import com.cuma.yildiz.LibrarySpring.dao.abstracts.AuthorRepo;
import com.cuma.yildiz.LibrarySpring.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorManager implements IAuthorService {
    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(long id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(long id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }


    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page , pageSize);
        return this.authorRepo.findAll(pageable);
    }
}
