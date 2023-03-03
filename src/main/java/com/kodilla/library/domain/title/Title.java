package com.kodilla.library.domain.title;

import com.kodilla.library.domain.copy.Copy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TITLES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue
    @NotFound
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLICATION_YEAR")
    private int publicationYear;

    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Copy> copies = new ArrayList<>();

    public Title(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
