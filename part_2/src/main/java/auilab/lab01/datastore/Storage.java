/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01.datastore;

import auilab.lab01.CUtility;
import auilab.lab01.shop.Book;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author Holodilnik
 */
@Component
public class Storage {

    private Set<Book> books = new HashSet<>();

    public synchronized List<Book> findAllBooks() {
        return books.stream().map(CUtility::clone).collect(Collectors.toList());
    }
    
    public Optional<Book> findBook(Long id) {
        return books.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CUtility::clone);
    }
    
    public synchronized void createBook(Book e) throws IllegalArgumentException {
        if (findBook(e.getId()) != null) {
            throw new IllegalArgumentException(String.format("The book \"%s\" is already present", e.getName()));
        }
        e.setId(findAllBooks().stream().mapToLong(Book::getId).max().orElse(0) + 1);
        books.add(e);
    }
    
    public synchronized void deleteBook(Long id) throws IllegalArgumentException {
        Optional<Book> e = findBook(id);
        if (e != null) {
            Book b = e.orElse(new Book());
            books.remove(b);
        }
        else {
            throw new IllegalArgumentException(String.format("The book with id \"%d\" does not exist", id));
        }
    }

    
}
