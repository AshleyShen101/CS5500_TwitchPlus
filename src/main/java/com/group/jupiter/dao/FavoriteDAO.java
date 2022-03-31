package com.group.jupiter.dao;

import com.group.jupiter.entity.db.Item;
import com.group.jupiter.entity.db.ItemType;
import com.group.jupiter.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FavoriteDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public FavoriteDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setFavoriteItem(String userId, Item item) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            user.getItemSet().add(item);

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
    }

    public void unsetFavoriteItem(String userId, String itemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            Item item = session.get(Item.class, itemId);
            user.getItemSet().remove(item);

            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            Objects.requireNonNull(session).getTransaction().rollback();
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
    }

    public Set<Item> getFavoriteItems(String userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId).getItemSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    public Set<String> getFavoriteItemIds(String userId) {
        Set<String> itemIds = new HashSet<>();
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, userId);
            if (Objects.isNull(user)) {
                return itemIds;
            }

            Set<Item> items = user.getItemSet();
            for (Item item : items) {
                itemIds.add(item.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemIds;
    }

    public Map<String, List<String>> getFavoriteGameIds(Set<String> favoriteItemIds) {
        Map<String, List<String>> itemMap = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            itemMap.put(type.toString(), new ArrayList<>());
        }

        try (Session session = sessionFactory.openSession()) {
            for (String itemId : favoriteItemIds) {
                Item item = session.get(Item.class, itemId);
                String itemType = item.getItemType().toString();
                String gameId = item.getGameId();
                itemMap.get(itemType).add(gameId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemMap;
    }
}

