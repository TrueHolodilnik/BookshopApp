/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01;

import auilab.lab01.services.BookService;
import auilab.lab01.services.BookshelfService;
import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 *
 * @author Holodilnik
 */
@Component
public class InitializerData {

    private BookService bookService;

    private BookshelfService bookshelfService;

    @Autowired
    public void InitializedData(BookService bookService, BookshelfService bookshelfService) {
        this.bookService = bookService;
        this.bookshelfService = bookshelfService;
    }

    @PostConstruct
    private synchronized void init() {
        if (bookService.findAll().isEmpty() && bookshelfService.findAll().isEmpty()) {

            Bookshelf shelf = Bookshelf.builder()
                    .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                    .location("Shop_1")
                    .name("Shelf_1")
                    .build();
            bookshelfService.create(shelf);

            Book a = Book.builder()
                    .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                    .name("Book_1")
                    .author("Author_1")
                    .genre("Horror")
                    .cost(100)
                    .bookshelf(shelf)
                    .build();

            Book b = Book.builder()
                    .id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                    .name("Book_2")
                    .author("Author_2")
                    .genre("Fantasy")
                    .cost(10)
                    .bookshelf(shelf)
                    .build();

            bookService.create(a);
            bookService.create(b);


        }
    }

}
