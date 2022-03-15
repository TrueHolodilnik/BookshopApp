package auilab.lab01.dto;

import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOBookshelfConverter {

    public DTOBookshelf entityToDtoCreate(Bookshelf bookshelf){
        DTOBookshelf b = DTOBookshelf.builder()
                .build();
        return b;
    }

    public Bookshelf dtoToEntity(DTOBookshelf dto){
        Bookshelf b = Bookshelf.builder()
                .id(dto.getId())
                .build();
        return b;
    }

    public Bookshelf dtoEntityUpdate(DTOBookshelf dto){
        Bookshelf b = Bookshelf.builder()
                .build();
        return b;
    }

    public List<Bookshelf> dtoToEntity(List<DTOBookshelf> dto){
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
