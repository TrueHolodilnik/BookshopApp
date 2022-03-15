package auilab.lab01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class DTOBook {

        private Long id;
        private String name;
        private String author;
        private String genre;
        private Long shelf_id;
        private int cost;

}
