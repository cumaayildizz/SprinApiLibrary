package com.cuma.yildiz.LibrarySpring.dto.response.bookBorrow;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowResponse {

    private Long id;

    private String borrowerName;

    private String borrowerEmail;;

    private LocalDate borrowingDate;

    private LocalDate returnDate;

    private long bookId;



}
