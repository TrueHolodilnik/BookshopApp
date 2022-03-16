/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01.services;

import auilab.lab01.repositories.BookRepository;
import auilab.lab01.shop.Book;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Holodilnik
 */
@Service
public class BookService {
    
    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> find(Long id) {
        return repository.findById(id);
    }

    public Optional<Book> findByName(String name) {
        List<Book> temp = repository.findAll();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getName().equals(name)) return Optional.of(temp.get(i));
        }
        return null;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Book e) {
        repository.create(e);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(repository.findById(id).get());
    }

}
