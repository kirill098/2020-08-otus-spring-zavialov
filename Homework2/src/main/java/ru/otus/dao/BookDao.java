package ru.otus.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {

}
