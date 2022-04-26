package com.group.jupiter.entity.request;

import com.group.jupiter.entity.db.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class FavoriteRequestBodyTest {

    @Test
    public void getFavoriteItem() {
        Item favoriteItem = new Item();
        FavoriteRequestBody favoriteRequestBody = new FavoriteRequestBody(favoriteItem);

        assertSame(favoriteItem, favoriteRequestBody.getFavoriteItem());
    }
}