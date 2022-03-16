/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01.repositories;

import auilab.lab01.datastore.Storage;
import auilab.lab01.shop.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements SRepository<Book, Long> {

    private Storage store;

    @Autowired
    public BookRepository(Storage store) {
        this.store = store;
    }

    @Override
    public Optional<Book> findById(Long id) {
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
    public void deleteById(Book entity) {
        store.deleteBook(entity.getId());
    }

}
