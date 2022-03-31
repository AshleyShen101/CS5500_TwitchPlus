package com.group.jupiter.service;

import com.group.jupiter.dao.FavoriteDAO;
import com.group.jupiter.entity.db.Item;
import com.group.jupiter.entity.db.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteService {
    private final FavoriteDAO favoriteDAO;

    @Autowired
    public FavoriteService(FavoriteDAO favoriteDAO) {
        this.favoriteDAO = favoriteDAO;
    }

    public void setFavoriteItem(String userId, Item item) {
        favoriteDAO.setFavoriteItem(userId, item);
    }

    public void unsetFavoriteItem(String userId, String itemId) {
        favoriteDAO.unsetFavoriteItem(userId, itemId);
    }

    public Map<String, List<Item>> getFavoriteItems(String userId) {
        Map<String, List<Item>> itemMap = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            itemMap.put(type.toString(), new ArrayList<>());
        }

        Set<Item> favorites = favoriteDAO.getFavoriteItems(userId);
        for (Item item : favorites) {
            itemMap.get(item.getItemType().toString()).add(item);
        }
        return itemMap;
    }
}
