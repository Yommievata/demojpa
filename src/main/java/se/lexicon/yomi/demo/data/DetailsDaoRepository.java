package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class DetailsDaoRepository implements DetailsDao{

    private final EntityManager entityManager;

    @Autowired
    public DetailsDaoRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Details create(Details details) {
       entityManager.persist(details);
       return details;
    }

    @Override
    @Transactional (readOnly = true)
    public Details findById(Integer detailsId) {
        return entityManager.find(Details.class, detailsId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        Collection<Details> result = new ArrayList<>();
        return entityManager.createQuery("SELECT details from Details details", Details.class).getResultList();
    }

    @Override
    @Transactional (rollbackFor = RuntimeException.class)
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer detailsId) {
        Details toRemove = findById(detailsId);
        if (toRemove != null){
            entityManager.remove(toRemove);
        }else{
            throw new IllegalArgumentException("Details Could not be found");
        }
        toRemove = findById(detailsId);
    }
}
