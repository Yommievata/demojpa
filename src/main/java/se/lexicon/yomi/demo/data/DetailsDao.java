package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.Details;

import java.util.Collection;

public interface DetailsDao {

    Details create(Details details);
    Details findById(Integer detailsId);
    Collection<Details> findAll();
    Details update(Details details);
    void delete(Integer detailsId);

}
