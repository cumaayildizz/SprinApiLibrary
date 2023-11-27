package com.cuma.yildiz.LibrarySpring.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private long id;

    @NotNull(message = "Yazar Adı boş veya null(tanımsız) olamaz!")
    private String name;

    private LocalDate birthDate;

    private String country;
}
