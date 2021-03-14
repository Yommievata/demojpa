package model;

import java.util.Collection;

public interface BookLoanDao {

    BookLoan create(BookLoan bookLoan);
    BookLoan findById(int bookLoanId);
    Collection<BookLoan> findAll();
    BookLoan update(BookLoan bookLoan);
    boolean delete(int bookLoanId);

}
