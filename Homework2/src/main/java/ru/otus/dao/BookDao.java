package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {

    Book save(Book book);

    Optional<Book> findById(long id);

    Optional<Book> findByAuthors_id(Long id);

    List<Book> findAll();

    void deleteById(long id);
}
