package com.kodilla.library.domain.rent;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.reader.Reader;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @ManyToOne(
            targetEntity = Copy.class,
            fetch = FetchType.EAGER

    )
    @JoinColumn(name = "COPY_ID")
    private Copy copy;

    @ManyToOne(
            targetEntity = Reader.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @Column(name = "RENTAL_DATE")
    private Date rentalDate;

    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public Rent(Copy copy, Reader reader, Date rentalDate, Date returnDate) {
        this.copy = copy;
        this.reader = reader;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rent(Copy copy, Reader reader, Date rentalDate) {
        this.copy = copy;
        this.reader = reader;
        this.rentalDate = rentalDate;
    }
}
