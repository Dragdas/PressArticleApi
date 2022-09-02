package com.kkulpa.pressarticleapi.app.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ArticleDTO {

    @Nullable
    private Long id;
    @NonNull
    private ArticleContentDTO articleContent;
    @NonNull
    private LocalDate publicationDate;
    @NonNull
    private String publisher;
    @NonNull
    private AuthorDTO author;
    @Nullable
    private LocalDate timestamp;
}
