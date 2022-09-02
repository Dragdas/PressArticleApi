package com.kkulpa.pressarticleapi.app.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class ArticleContentDTO {

    @Nullable
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String content;
}
