package Repository;

import model.Book;
import model.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
@Repository
public class BookDaoRepository implements BookDao {

    private final EntityManager en;

    @Autowired
    public BookDaoRepository(EntityManager en) {
        this.en = en;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Book create(Book book) {
        en.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(int bookId) {
        return en.find(Book.class, bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        Collection<Book> seek = new ArrayList<>();
        return en.createQuery("SELECT book from Book book", Book.class).getResultList();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Book update(Book book) {
        return en.merge(book);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(int bookId) {
        Book toRemove = findById(bookId);
        if (toRemove != null){
            en.remove(toRemove);
        }else{
            throw new IllegalArgumentException("Book Information Could not be found");
        }
        toRemove = findById(bookId);
        return toRemove == null;

    }
}
