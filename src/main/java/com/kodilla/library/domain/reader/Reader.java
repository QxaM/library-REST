package com.kodilla.library.domain.reader;

import com.kodilla.library.domain.rent.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "READERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reader {

    @Id
    @GeneratedValue
    @Column(name ="ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rent> rentsList = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        createdDate = new Date();
    }

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = new Date();
    }
}
