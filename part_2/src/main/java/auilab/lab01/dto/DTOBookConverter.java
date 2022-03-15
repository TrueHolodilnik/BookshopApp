package auilab.lab01.dto;

import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class DTOBookConverter {

    public DTOBook entityToDto(Book book){
        DTOBook b = DTOBook.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .cost(book.getCost())
                .shelf_id(book.getBookshelf().getId())
                .build();
        return b;
    }

    public DTOBook entityToDtoCreate(Book book){
        DTOBook b = DTOBook.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .cost(book.getCost())
                .shelf_id(book.getBookshelf().getId())
                .build();
        return b;
    }

    public Book dtoToEntity(DTOBook dto){
        Book b = Book.builder()
                .id(dto.getId())
                .name(dto.getName())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .cost(dto.getCost())
                .build();
        return b;
    }

    public Function<DTOBook, Book> dtoEntityUpdate(Supplier<Bookshelf> supplier){
        return request -> Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .cost(request.getCost())
                .bookshelf(supplier.get())
                .build();
    }

    public List<DTOBook> entityToDto(List<Book> books){
        return books.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

    public List<Book> dtoToEntity(List<DTOBook> dto){
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
