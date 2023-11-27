package com.cuma.yildiz.LibrarySpring.dto.response.author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private long id;
    private String name;
    private LocalDate birthDate;
    private String country;
}
