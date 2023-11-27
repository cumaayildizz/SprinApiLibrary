package com.cuma.yildiz.LibrarySpring.dto.request.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookUpdateRequest {

    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private long id;

    @NotNull(message = "Yazar Adı boş veya null(tanımsız) olamaz!")
    private String name;

    private int publicationYear;


    private int stock;


    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private long authorId;


    //private List<Category> categoryList;


    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private long publisherId;
}
