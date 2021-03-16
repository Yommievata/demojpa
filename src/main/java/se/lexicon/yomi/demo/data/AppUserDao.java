package se.lexicon.yomi.demo.data;

import se.lexicon.yomi.demo.entity.AppUser;

import java.util.Collection;

public interface AppUserDao {

    AppUser create(AppUser appUser);
    AppUser findById(Integer appUserId);
    Collection<AppUser> findAll();
    AppUser update(AppUser appUser);
    void delete(Integer appUserId);

}
