package auilab.lab01.services;

import auilab.lab01.event.BookshelfEventRepository;
import auilab.lab01.repositories.BookshelfRepository;
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
    private BookshelfEventRepository eventRepository;

    @Autowired
    public BookshelfService (BookshelfRepository repository, BookshelfEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
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

    @Transactional
    public void create(Bookshelf e) {
        repository.save(e);
        eventRepository.create(e);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        eventRepository.delete(id);
    }

    @Transactional
    public void update(Bookshelf shelf) {
        repository.save(shelf);
    }

}
