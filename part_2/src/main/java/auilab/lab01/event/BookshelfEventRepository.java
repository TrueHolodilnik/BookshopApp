package auilab.lab01.event;

import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BookshelfEventRepository {

    private RestTemplate restTemplate;
    private EventConverter converter;

    @Autowired
    public BookshelfEventRepository(@Value("http://localhost:8081") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
        converter = new EventConverter();
    }

    public void delete(Long id) {
        restTemplate.delete("/api/bookshelf/{id}", id);
    }

    public void create(Bookshelf bookshelf) {
        restTemplate.postForLocation("/api/bookshelf", converter.entityToDto(bookshelf));
    }
}
