package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.BookLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
@Repository
public class BookLoanDaoRepository implements BookLoanDao {

    private final EntityManager entity;

    @Autowired
    public BookLoanDaoRepository(EntityManager entity) {
        this.entity = entity;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BookLoan create(BookLoan bookLoan) {
        entity.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(int bookLoanId) {
        return entity.find(BookLoan.class, bookLoanId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        Collection<BookLoan> search = new ArrayList<>();
        return entity.createQuery("SELECT bookLoan from BookLoan bookLoan", BookLoan.class).getResultList();
    }

    @Override
    @Transactional (rollbackFor = RuntimeException.class)
    public BookLoan update(BookLoan bookLoan) {
        return entity.merge(bookLoan);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(int bookLoanId) {
        BookLoan remove = findById(bookLoanId);
        if (remove != null){
            entity.remove(remove);
        }else{
            throw new IllegalArgumentException("BookLoan Information Could not be found");
        }
        remove = findById(bookLoanId);

    }
}
