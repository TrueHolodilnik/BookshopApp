package auilab.lab01.controllers;

import auilab.lab01.dto.DTOBook;
import auilab.lab01.dto.DTOBookConverter;
import auilab.lab01.dto.DTOBookshelf;
import auilab.lab01.dto.DTOBookshelfConverter;
import auilab.lab01.services.BookService;
import auilab.lab01.services.BookshelfService;
import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bookshelf/{id}/books")
public class BookshelfBookController {

    private BookService bookService;
    private BookshelfService bookshelfService;
    DTOBookConverter bookConverter;
    DTOBookshelfConverter bookshelfConverter;

    @Autowired
    public BookshelfBookController(BookService bookService, BookshelfService bookshelfService, DTOBookConverter bookConverter) {
        this.bookService = bookService;
        this.bookshelfService = bookshelfService;
        this.bookConverter = bookConverter;
    }

    @GetMapping("findAll")
    public List<DTOBook> getBooks(@PathVariable("id") Long id) {
        Optional<Bookshelf> b = bookshelfService.find(id);
        List<DTOBook> result = new LinkedList<DTOBook>();
        if (!b.isPresent()) return result;
        List<Book> books = bookService.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookshelf().getId() == id) {
                result.add(bookConverter.entityToDto(books.get(i)));
            }
        }
        return result;
    }

    @GetMapping("{book_id}")
    public DTOBook getBook(@PathVariable("id") Long id, @PathVariable("book_id") Long book_id) {
        Optional<Bookshelf> b = bookshelfService.find(id);
        DTOBook result = new DTOBook();
        if (!b.isPresent()) return result;
        List<Book> books = bookService.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookshelf().getId() == id && books.get(i).getId() == book_id) {
                return bookConverter.entityToDto(books.get(i));
            }
        }
        return result;
    }

    @PostMapping
    public void createBook(@RequestBody DTOBook dto, @PathVariable("id") Long id) {
        Optional<Bookshelf> b = bookshelfService.find(id);
        if (!b.isPresent()) return;
        Book book = bookConverter.dtoEntityUpdate(b::get).apply(dto);
        bookService.create(book);
    }

    @DeleteMapping("{book_id}")
    public void deleteBook(@PathVariable("id") Long id, @PathVariable("book_id") Long book_id) {
        Optional<Bookshelf> b = bookshelfService.find(id);
        if (!b.isPresent()) return;
        List<Book> books = bookService.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookshelf().getId() == id && books.get(i).getId() == book_id) {
                bookService.delete(books.get(i).getId());
            }
        }
    }

    @PutMapping("{book_id}")
    public void updateBook(@RequestBody DTOBook dto, @PathVariable("id") Long id, @PathVariable("book_id") Long book_id) {
        Optional<Bookshelf> b = bookshelfService.find(id);
        if (!b.isPresent()) return;
        List<Book> books = bookService.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookshelf().getId() == id && books.get(i).getId() == book_id) {
                Book book = books.get(i);
                book.setName(dto.getName());
                book.setAuthor(dto.getAuthor());
                book.setCost(dto.getCost());
                book.setGenre(dto.getGenre());
                bookService.update(book);
                break;
            }
        }
    }

}
