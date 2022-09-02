package com.kkulpa.pressarticleapi.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CONTENT", nullable = false)
    private ArticleContent articleContent;

    @Column(name = "PUBLICATION_DATE", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "PUBLISHER", nullable = false)
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "AUTHOR", nullable = false)
    private Author author;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDate timestamp;
}
