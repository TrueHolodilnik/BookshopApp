/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01.controllers;

import auilab.lab01.dto.DTOBook;
import auilab.lab01.dto.DTOBookConverter;
import auilab.lab01.services.BookService;
import auilab.lab01.services.BookshelfService;
import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Holodilnik
 */
@RestController
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;
    private BookshelfService bookshelfService;
    DTOBookConverter bookConverter;

    @Autowired
    public BookController(BookService bookService, DTOBookConverter bookConverter, BookshelfService bookshelfService) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
        this.bookshelfService = bookshelfService;
    }

    @GetMapping("findAll")
    public List<DTOBook> findAll(){
        List<Book> books = bookService.findAll();
        return bookConverter.entityToDto(books);
    }

    @GetMapping("{id}")
    public DTOBook findById(@PathVariable("id") Long id){
        Book book = bookService.find(id).orElse(new Book());
        return bookConverter.entityToDto(book);
    }

    @PostMapping("{id}")
    public DTOBook save(@RequestBody DTOBook dto, @PathVariable("id") Long id){
        Optional<Bookshelf> b = bookshelfService.find(id);
        if (b.equals(null)) {
            return bookConverter.entityToDtoCreate(new Book());
        }
        Book book = bookConverter.dtoEntityUpdate(b::get).apply(dto);
        bookService.create(book);
        return bookConverter.entityToDtoCreate(book);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Book temp){
        Book book = bookService.find(id).orElse(null);
        if (!book.equals(null)) {
            book.setGenre(temp.getGenre());
            book.setCost(temp.getCost());
            book.setAuthor(temp.getAuthor());
            book.setName(temp.getName());
            bookService.update(book);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.find(id);
        if (book.isPresent()) {
            bookService.delete(book.get().getId());
        }
    }
}
