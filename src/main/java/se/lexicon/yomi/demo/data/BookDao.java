package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.Book;

import java.util.Collection;

public interface BookDao {

    Book create(Book book);
    Book findById(int bookId);
    Collection<Book> findAll();
    Book update(Book book);
    void delete(int bookId);

}
