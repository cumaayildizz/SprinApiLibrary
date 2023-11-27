package com.cuma.yildiz.LibrarySpring.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {

    @NotNull(message = "Yayımcı Adı boş veya null(tanımsız) olamaz!")
    private String name;

    private int establishmentYear;

    private String address;
}
