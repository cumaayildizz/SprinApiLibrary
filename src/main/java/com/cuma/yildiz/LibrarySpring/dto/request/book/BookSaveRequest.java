package com.cuma.yildiz.LibrarySpring.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    private String name;


    private int publicationYear;


    private int stock;


    private long authorId;


    //private List<Category> categoryList;


    private long publisherId;

}
