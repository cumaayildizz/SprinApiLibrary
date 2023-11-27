package com.cuma.yildiz.LibrarySpring.dto.request.bookBorrow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowSaveRequest {

    private String borrowerName;

    private String borrowerEmail;;

    private LocalDate borrowingDate;

    private LocalDate returnDate;

    private long BookId;
}
