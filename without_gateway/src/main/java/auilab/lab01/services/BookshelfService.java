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

    public BookshelfService (BookshelfRepository repository) {
        this.repository = repository;
    }

    public Optional<Bookshelf> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Bookshelf> findByName(String name) {
        List<Bookshelf> temp = repository.findAll();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getName().equals(name)) return Optional.of(temp.get(i));
        }
        return null;
    }

    public List<Bookshelf> findAll() {
        return repository.findAll();
    }

    public void create(Bookshelf e) {
        repository.create(e);
    }

    public void delete(Long id) {
        repository.deleteById(repository.findById(id).get());
    }

}
