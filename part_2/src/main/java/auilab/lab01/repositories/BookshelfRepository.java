package auilab.lab01.repositories;

import auilab.lab01.shop.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

    /**
     private Storage storage;

     @Autowired
     public BookshelfRepository(Storage storage) {
        this.storage = storage;
     }

     @Override
     public Optional<Bookshelf> find(Long id) {
        return store.findBookshelf(id);
     }

     @Override
     public List<Bookshelf> findAll() {
        return store.findAllBookshelf();
     }

     @Override
     public void create(Bookshelf entity) {
        store.createBookshelf(entity);
     }

     @Override
     public void delete(Book entity) {
        store.deleteBookshelf(entity.getId());
     }

     @Override
     public void update(Bookshelf entity) {
        store.updateBookshelf(entity);
     }
     */

}
