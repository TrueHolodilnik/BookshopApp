package auilab.lab01.services;

import auilab.lab01.repositories.BookshelfRepository;
import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Holodilnik
 */
@Service
public class BookshelfService {

    private BookshelfRepository repository;

    @Autowired
    public BookshelfService (BookshelfRepository repository) {
        this.repository = repository;
    }

    public Optional<Bookshelf> find(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Bookshelf create(Bookshelf e) {
        return repository.save(e);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
