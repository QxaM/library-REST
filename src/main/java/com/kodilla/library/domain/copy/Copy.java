package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.title.Title;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COPIES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Copy {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Column(name = "STATUS")
    private CopyStatus status;

    public Copy(Title title, CopyStatus status) {
        this.title = title;
        this.status = status;
    }
}
