package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COPIES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Copy {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Column(name = "STATUS")
    private CopyStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rent> rentsList = new ArrayList<>();

    public Copy(Title title, CopyStatus status) {
        this.title = title;
        this.status = status;
    }
}
