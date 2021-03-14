package model;

import java.util.Collection;

public interface DetailsDao {

    Details create(Details details);
    Details findById(Integer detailsId);
    Collection<Details> findAll();
    Details update(Details details);
    boolean delete(Integer detailsId);

}
