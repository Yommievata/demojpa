package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AuthorDaoRepository implements AuthorDao {

    private EntityManager manager;

    public AuthorDaoRepository(EntityManager authorentity) {
        this.manager = authorentity;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author create(Author author) {
        manager.persist(author);
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDao findById(Integer authorId) {
        return manager.find(AuthorDao.class, authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
         Collection<Author> buk = new ArrayList<>();
        return manager.createQuery("SELECT author from Author author", Author.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author update(Author author) {
        return manager.merge(author);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer authorId) {
        AuthorDao toClear = findById(authorId);
        if (toClear != null){
            manager.remove(toClear);
        }else{
            throw new IllegalArgumentException("Author Information Could not be found");
        }
        toClear = findById(authorId);
    }
}
