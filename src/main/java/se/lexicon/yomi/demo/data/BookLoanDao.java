package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDao {

    BookLoan create(BookLoan bookLoan);
    BookLoan findById(int bookLoanId);
    Collection<BookLoan> findAll();
    BookLoan update(BookLoan bookLoan);
    void delete(int bookLoanId);

}
