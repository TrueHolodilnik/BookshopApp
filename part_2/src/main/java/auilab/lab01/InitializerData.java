/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01;

import auilab.lab01.services.BookshelfService;
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

    private BookshelfService bookshelfService;

    @Autowired
    public void InitializedData(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @PostConstruct
    private synchronized void init() {
            Bookshelf shelf = Bookshelf.builder()
                    .location("Shop_1")
                    .name("Shelf_1")
                    .build();
            bookshelfService.create(shelf);

    }

}
