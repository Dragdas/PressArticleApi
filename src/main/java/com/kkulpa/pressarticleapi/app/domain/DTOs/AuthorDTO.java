package com.kkulpa.pressarticleapi.app.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class AuthorDTO {
    @Nullable
    private Long id;
    private String firstName;
    private String lastName;

}
