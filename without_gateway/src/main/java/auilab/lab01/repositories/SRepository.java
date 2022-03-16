package auilab.lab01.repositories;

import java.util.List;
import java.util.Optional;

public interface SRepository<E, K> {

    Optional<E> findById(K id);

    List<E> findAll();

    void create(E entity);

    void deleteById(E entity);

}
