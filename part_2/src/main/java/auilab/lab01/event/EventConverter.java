package auilab.lab01.event;

import auilab.lab01.shop.Bookshelf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
public class EventConverter {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class DTOBookshelf {
        private Long id;
    }

    public DTOBookshelf entityToDto(Bookshelf bookshelf){
        DTOBookshelf b = new DTOBookshelf();
        b.setId(bookshelf.getId());
        return b;
    }

}
