package com.cuma.yildiz.LibrarySpring.business.concretes;

import com.cuma.yildiz.LibrarySpring.business.abstracts.ICategoryService;
import com.cuma.yildiz.LibrarySpring.core.config.Exceptions.NotFoundException;
import com.cuma.yildiz.LibrarySpring.core.utils.Messages;
import com.cuma.yildiz.LibrarySpring.dao.abstracts.CategoryRepo;
import com.cuma.yildiz.LibrarySpring.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(long id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND));
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public boolean delete(long id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page , pageSize);
        return this.categoryRepo.findAll(pageable);
    }
}
