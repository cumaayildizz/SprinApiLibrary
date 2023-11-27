package com.cuma.yildiz.LibrarySpring.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private long id;

    @NotNull(message = "Yayımcı Adı boş veya null(tanımsız) olamaz!")
    private String name;

    private int establishmentYear;

    private String address;
}
