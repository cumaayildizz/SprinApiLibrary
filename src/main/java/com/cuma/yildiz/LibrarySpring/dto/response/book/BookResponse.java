package com.cuma.yildiz.LibrarySpring.dto.response.book;

import com.cuma.yildiz.LibrarySpring.entities.Author;
import com.cuma.yildiz.LibrarySpring.entities.BookBorrow;
import com.cuma.yildiz.LibrarySpring.entities.Category;
import com.cuma.yildiz.LibrarySpring.entities.Publisher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;


    private String name;


    private int publicationYear;


    private int stock;


    private long authorId;


    //private List<Category> categoryList;


    private long publisherId;

}
