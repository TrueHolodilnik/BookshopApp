package auilab.lab01.controllers;

import auilab.lab01.dto.*;
import auilab.lab01.services.BookshelfService;
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
@RequestMapping("api/bookshelf")
public class BookshelfController {

    private BookshelfService bookshelfService;

    DTOBookshelfConverter bookshelfConverter;

    @Autowired
    public BookshelfController(BookshelfService bookshelfService, DTOBookshelfConverter bookshelfConverter) {
        this.bookshelfService = bookshelfService;
        this.bookshelfConverter = bookshelfConverter;
    }

    @GetMapping("findAll")
    public List<DTOBookshelf> findAll(){
        List<Bookshelf> bookshelves = bookshelfService.findAll();
        return bookshelfConverter.entityToDto(bookshelves);
    }

    @GetMapping("{id}")
    public DTOBookshelf findById(@PathVariable("id") Long id){
        Bookshelf bookshelf = bookshelfService.find(id).orElse(null);
        return bookshelfConverter.entityToDto(bookshelf);
    }

    @PostMapping("")
    public DTOBookshelf save(@RequestBody DTOBookshelf dto){
        Bookshelf bookshelf = bookshelfConverter.dtoEntityUpdate(dto);
        bookshelfService.create(bookshelf);
        return bookshelfConverter.entityToDtoCreate(bookshelf);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Bookshelf temp){
        Bookshelf bookshelf = bookshelfService.find(id).orElse(null);
        if (!bookshelf.equals(null)) {
            bookshelf.setLocation(temp.getLocation());
            bookshelf.setName(temp.getName());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        Optional<Bookshelf> book = bookshelfService.find(id);
        if (book.isPresent()) {
            bookshelfService.delete(book.get().getId());
        }
    }

}
