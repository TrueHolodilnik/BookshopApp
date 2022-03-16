/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01.repositories;

import auilab.lab01.shop.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Holodilnik
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
    private Storage storage;

    @Autowired
    public BookRepository(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Book> find(Long id) {
        return store.findBook(id);
    }

    @Override
    public List<Book> findAll() {
        return store.findAllBooks();
    }

    @Override
    public void create(Book entity) {
        store.createBook(entity);
    }

    @Override
    public void delete(Book entity) {
        store.deleteBook(entity.getId());
    }

    @Override
    public void update(Book entity) {
        store.updateBook(entity);
    }
     */

}
