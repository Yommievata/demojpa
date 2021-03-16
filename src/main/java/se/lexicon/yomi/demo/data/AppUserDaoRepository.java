package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class AppUserDaoRepository implements AppUserDao{

    private final EntityManager em;

    @Autowired
    public AppUserDaoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Integer appUserId) {
        return em.find(AppUser.class, appUserId);

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
       Collection<AppUser> result = new ArrayList<>();
       return em.createQuery("SELECT appUser from AppUser appUser", AppUser.class).getResultList();

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public AppUser update(AppUser appUser) {
       return em.merge(appUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer appUserId) {
        AppUser toRemove = findById(appUserId);
        if (toRemove != null){
            em.remove(toRemove);
        }else{
            throw new IllegalArgumentException("AppUser Could not be found");
        }
        toRemove = findById(appUserId);
    }
}


