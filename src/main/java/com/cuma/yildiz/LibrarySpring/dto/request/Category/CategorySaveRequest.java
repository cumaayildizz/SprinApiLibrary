package com.cuma.yildiz.LibrarySpring.dto.request.Category;

import jakarta.validation.constraints.NotNull;

public class CategorySaveRequest {

    @NotNull(message = "Kategori Adı boş veya null(tanımsız) olamaz!")
    private String name;

    private String description;
}
