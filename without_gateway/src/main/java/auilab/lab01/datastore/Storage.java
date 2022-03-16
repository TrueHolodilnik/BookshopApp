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

import auilab.lab01.shop.Bookshelf;
import org.springframework.stereotype.Component;

/**
 *
 * @author Holodilnik
 */
@Component
public class Storage {

    private Set<Book> books = new HashSet<>();
    private Set<Bookshelf> bookshelves = new HashSet<>();

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
        findBook(e.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The book name \"%s\" is not unique", e.getName()));
                },
                () -> books.add(CUtility.clone(e)));
    }
    
    public synchronized void deleteBook(Long id) throws IllegalArgumentException {
        Optional<Book> e = findBook(id);
        if (!e.isEmpty()) {
            Book b = e.orElse(new Book());
            books.remove(b);
        }
        else {
            throw new IllegalArgumentException(String.format("The book with id \"%d\" does not exist", id));
        }
    }

    public synchronized List<Bookshelf> findAllBookshelves() {
        return bookshelves.stream().map(CUtility::clone).collect(Collectors.toList());
    }

    public Optional<Bookshelf> findBookshelf(Long id) {
        return bookshelves.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CUtility::clone);
    }

    public synchronized void createBookshelf(Bookshelf e) throws IllegalArgumentException {
        findBookshelf(e.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The bookshelf name \"%s\" is not unique", e.getName()));
                },
                () -> bookshelves.add(CUtility.clone(e)));
    }

    public synchronized void deleteBookshelf(Long id) throws IllegalArgumentException {
        Optional<Bookshelf> e = findBookshelf(id);
        if (!e.isEmpty()) {
            Bookshelf b = e.orElse(new Bookshelf());
            bookshelves.remove(b);
        }
        else {
            throw new IllegalArgumentException(String.format("The bookshelf with id \"%d\" does not exist", id));
        }
    }

    
}
