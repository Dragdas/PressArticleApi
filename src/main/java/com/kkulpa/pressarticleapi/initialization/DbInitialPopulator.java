package com.kkulpa.pressarticleapi.initialization;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.ArticleContent;
import com.kkulpa.pressarticleapi.app.domain.Author;
import com.kkulpa.pressarticleapi.repository.ArticleContentRepository;
import com.kkulpa.pressarticleapi.repository.ArticleRepository;
import com.kkulpa.pressarticleapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitialPopulator {

    private final ArticleRepository articleRepository;
    private final ArticleContentRepository articleContentRepository;
    private final AuthorRepository authorRepository;

    private final static  List<Author> authors = List.of(
            new Author(null, "Bob", "Smith"),
            new Author(null, "Zuza", "Chlebowsky")
    );

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        authorRepository.saveAll(authors);

        List<Article> articles = List.of(
                new Article(null,
                        new ArticleContent(null, "Boring title", "Super boring article"),
                        LocalDate.now().minusDays(3),
                        "NY Times",
                        authorRepository.findById(1L).get(),
                        LocalDate.now()
                ),

                new Article(null,
                        new ArticleContent(null, "Another boring title", "Boring article"),
                        LocalDate.now().minusDays(5),
                        "Times",
                        authorRepository.findById(2L).get(),
                        LocalDate.now()
                ),

                new Article(null,
                        new ArticleContent(null, "Breaking news", "Super important news"),
                        LocalDate.now().minusDays(1),
                        "WS Journal",
                        authorRepository.findById(1L).get(),
                        LocalDate.now()
                )

        );

        articleRepository.saveAll(articles);

    }

}
