package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.Author;

import java.util.Collection;

public interface AuthorDao {

    Author create(Author author);
    AuthorDao findById(Integer authorId);
    Collection<Author> findAll();
    Author update(Author author);
    void delete(Integer authorId);
}
