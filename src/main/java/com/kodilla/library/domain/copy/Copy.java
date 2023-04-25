package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.rent.Rent;
import com.kodilla.library.domain.title.Title;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COPIES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Copy {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Column(name = "STATUS")
    @Setter
    private CopyStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rent> rentsList = new ArrayList<>();

    public Copy(Title title, CopyStatus status) {
        this.title = title;
        this.status = status;
    }
}
