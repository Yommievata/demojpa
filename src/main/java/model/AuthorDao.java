package model;

import java.util.Collection;

public interface AuthorDao {

    Author create(Author author);
    AuthorDao findById(Integer authorId);
    Collection<Author> findAll();
    Author update(Author author);
    boolean delete(Integer authorId);
}
