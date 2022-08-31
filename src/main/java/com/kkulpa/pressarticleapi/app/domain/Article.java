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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTENT")
    private ArticleContent articleContent;

    @Column(name = "PUBLICATION_DATE")
    private LocalDate publicationDate;

    @Column(name = "PUBLISHER")
    private String publisher;

    @ManyToOne
    @JoinColumn(name = "AUTHOR")
    private Author author;

    @Column(name = "TIMESTAMP")
    private LocalDate timestamp;

}
