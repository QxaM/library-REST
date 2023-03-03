package com.kodilla.library.domain.reader;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "READERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name ="ID", unique = true)
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = new Date();
    }
}
