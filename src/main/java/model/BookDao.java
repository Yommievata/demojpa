package model;

import java.util.Collection;

public interface BookDao {

    Book create(Book book);
    Book findById(int bookId);
    Collection<Book> findAll();
    Book update(Book book);
    boolean delete(int bookId);

}
