package auilab.lab01.repositories;

import auilab.lab01.datastore.Storage;
import auilab.lab01.shop.Book;
import auilab.lab01.shop.Bookshelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookshelfRepository implements SRepository<Bookshelf, Long> {

     private Storage store;

     @Autowired
     public BookshelfRepository(Storage store) {
        this.store = store;
     }

     @Override
     public Optional<Bookshelf> findById(Long id) {
        return store.findBookshelf(id);
     }

     @Override
     public List<Bookshelf> findAll() {
        return store.findAllBookshelves();
     }

     @Override
     public void create(Bookshelf entity) {
        store.createBookshelf(entity);
     }

     @Override
     public void deleteById(Bookshelf entity) {
        store.deleteBookshelf(entity.getId());
     }

}
