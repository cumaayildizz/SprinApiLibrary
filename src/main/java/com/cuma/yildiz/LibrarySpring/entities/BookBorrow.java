package com.cuma.yildiz.LibrarySpring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_borrows")
public class BookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_borrow_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "book_borrow_name", nullable = false)
    private String borrowerName;

    @Column(name = "book_borrow_email")
    private String borrowerEmail;


    @Column(name = "book_borrow_date" )
    private LocalDate borrowingDate;

    @Column(name = "book_borrow_return")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_borrow_book_id", referencedColumnName = "book_id")
    private Book book;
}
