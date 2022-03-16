/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auilab.lab01;

/**
 *
 * @author Holodilnik
 */
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import auilab.lab01.services.BookshelfService;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Holodilnik
 */

@Component
public class CLine implements CommandLineRunner {

    private BookshelfService bookshelfService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    public int CommandLine(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
        return 0;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner s = new Scanner(System.in);
        Boolean is_end = false;
        while (!is_end) {
            int option = s.nextInt();
            switch (option){
                case 1:
                    System.out.println("Bookshelves:");
                    List<Bookshelf> ts = bookshelfService.findAll();
                    for (int i = 0; i < ts.size(); i++) {
                        System.out.println(ts.get(i).getName());
                    }
                    break;
                case 2:
                    is_end = true;
                    SpringApplication.exit(context);
                    break;
            }
        }


    }

}
