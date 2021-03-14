package Repository;

import model.Author;
import model.AuthorDao;
import model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AuthorDaoRepository implements AuthorDao {

    private EntityManager auten;

    public AuthorDaoRepository(EntityManager authorentity) {
        this.auten = authorentity;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author create(Author author) {
        auten.persist(author);
        return author;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDao findById(Integer authorId) {
        return auten.find(AuthorDao.class, authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
         Collection<Author> buk = new ArrayList<>();
        return auten.createQuery("SELECT author from Author author", Author.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Author update(Author author) {
        return auten.merge(author);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer authorId) {
        AuthorDao toClear = findById(authorId);
        if (toClear != null){
            auten.remove(toClear);
        }else{
            throw new IllegalArgumentException("Author Information Could not be found");
        }
        toClear = findById(authorId);
        return toClear == null;
    }
}
