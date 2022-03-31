package com.group.jupiter.service;

import com.group.jupiter.dao.FavoriteDAO;
import com.group.jupiter.entity.db.Item;
import com.group.jupiter.entity.db.ItemType;
import com.group.jupiter.entity.response.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    private static final int DEFAULT_GAME_LIMIT = 3;
    private static final int DEFAULT_PER_GAME_RECOMMENDATION_LIMIT = 10;
    private static final int DEFAULT_TOTAL_RECOMMENDATION_LIMIT = 20;

    private final GameService gameService;
    private final SearchService searchService;
    private final FavoriteDAO favoriteDAO;

    @Autowired
    public RecommendationService(GameService gameService, SearchService searchService, FavoriteDAO favoriteDAO) {
        this.gameService = gameService;
        this.searchService = searchService;
        this.favoriteDAO = favoriteDAO;
    }

    public Map<String, List<Item>> recommendItemsByDefault() throws RecommendationException {
        Map<String, List<Item>> recommendItemMap = new HashMap<>();

        List<Game> topGames;
        try {
            topGames = gameService.topGames(DEFAULT_GAME_LIMIT);
        } catch (TwitchException e) {
            throw new RecommendationException("Failed to get data for recommendItemsByDefault");
        }

        for (ItemType type : ItemType.values()) {
            recommendItemMap.put(type.toString(), recommendByTopGames(type, topGames));
        }
        return recommendItemMap;
    }

    private List<Item> recommendByTopGames(ItemType type, List<Game> games) throws RecommendationException {
        List<Item> recommendedItems = new ArrayList<>();

        for (Game game : games) {
            List<Item> items;

            try {
                String gameId = game.getId();
                items = searchService.searchByType(gameId, type, DEFAULT_PER_GAME_RECOMMENDATION_LIMIT);
            } catch (TwitchException e) {
                throw new RecommendationException("Failed to get data for recommendByTopGames");
            }

            for (Item item : items) {
                if (recommendedItems.size() == DEFAULT_TOTAL_RECOMMENDATION_LIMIT) {
                    return recommendedItems;
                }
                recommendedItems.add(item);
            }
        }

        return recommendedItems;
    }

    public Map<String, List<Item>> recommendItemsByUser(String userId) throws RecommendationException {
        Map<String, List<Item>> recommendItemMap = new HashMap<>();

        Set<String> favoriteItemIds = favoriteDAO.getFavoriteItemIds(userId);
        Map<String, List<String>> favoriteGameIds = favoriteDAO.getFavoriteGameIds(favoriteItemIds);

        for (Map.Entry<String, List<String>> entry : favoriteGameIds.entrySet()) {
            List<String> gameIds = entry.getValue();
            String strType = entry.getKey();
            ItemType type = ItemType.valueOf(strType);
            if (gameIds.isEmpty()) {
                List<Game> topGames;
                try {
                    topGames = gameService.topGames(DEFAULT_GAME_LIMIT);
                } catch (TwitchException e) {
                    throw new RecommendationException("Failed to get data for recommendItemsByUser");
                }
                recommendItemMap.put(strType, recommendByTopGames(type, topGames));
            } else {
                recommendItemMap.put(strType, recommendByFavoriteHistory(favoriteItemIds, gameIds, type));
            }
        }

        return recommendItemMap;
    }

    private List<Item> recommendByFavoriteHistory(Set<String> favoriteItemIds, List<String> gameIds, ItemType type) {
        Map<String, Long> favoriteGameIdByCount = new HashMap<>();
        for (String gameId : gameIds) {
            favoriteGameIdByCount.put(gameId, favoriteGameIdByCount.getOrDefault(gameId, 0L) + 1);
        }

        List<Map.Entry<String, Long>> sortedFavoriteGameIdListByCount = new ArrayList<>(favoriteGameIdByCount.entrySet());
        sortedFavoriteGameIdListByCount.sort(
                (Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) -> Long.compare(e2.getValue(), e1.getValue())
        );

        if (sortedFavoriteGameIdListByCount.size() > DEFAULT_GAME_LIMIT) {
            sortedFavoriteGameIdListByCount = sortedFavoriteGameIdListByCount.subList(0, DEFAULT_GAME_LIMIT);
        }

        List<Item> recommendedItems = new ArrayList<>();
        for (Map.Entry<String, Long> favoriteGame : sortedFavoriteGameIdListByCount) {
            List<Item> items;
            try {
                String gameId = favoriteGame.getKey();
                items = searchService.searchByType(gameId, type, DEFAULT_PER_GAME_RECOMMENDATION_LIMIT);
            } catch (TwitchException e) {
                throw new RecommendationException("Failed to get data for recommendByFavoriteHistory");
            }

            for (Item item : items) {
                if (recommendedItems.size() == DEFAULT_TOTAL_RECOMMENDATION_LIMIT) {
                    return recommendedItems;
                }
                if (!favoriteItemIds.contains(item.getId())) {
                    recommendedItems.add(item);
                }
            }
        }

        return recommendedItems;
    }
}
