package com.group.jupiter.entity.db;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void getId() {
        Item item = new Item();
        item.setId("1234");
        assertEquals("1234",item.getId());
    }

    @Test
    public void setId() {
        Item item = new Item();
        item.setId("abcd");
        assertEquals("abcd", item.getId());
    }

    @Test
    public void getTitle() {
        Item item = new Item();
        item.setTitle("title");
        assertEquals("title", item.getTitle());
    }

    @Test
    public void setTitle() {
        Item item = new Item();
        item.setTitle("WCS WINTER Europe qualifier II: Luolis vs Hellraiser");
        assertEquals("WCS WINTER Europe qualifier II: Luolis vs Hellraiser", item.getTitle());
    }

    @Test
    public void getUrl() {
        Item item = new Item();
        item.setUrl("https://www.twitch.tv/videos/366282939");
        assertEquals("https://www.twitch.tv/videos/366282939",item.getUrl());
    }

    @Test
    public void setUrl() {
        Item item = new Item();
        item.setUrl("https://www.twitch.tv/null");
        assertEquals("https://www.twitch.tv/null",item.getUrl());
    }

    @Test
    public void getThumbnailUrl() {
        Item item = new Item();
        item.setThumbnailUrl("https://static-cdn.jtvnw.net/cf_vods/d2nvs31859zcd8/cf8f12b9daa4e2554818_divinesiatv_14772441901_5056332498//thumb/thumb366282939-480x480.jpg");
        assertEquals("https://static-cdn.jtvnw.net/cf_vods/d2nvs31859zcd8/cf8f12b9daa4e2554818_divinesiatv_14772441901_5056332498//thumb/thumb366282939-480x480.jpg",item.getThumbnailUrl());
    }

    @Test
    public void setThumbnailUrl() {
        Item item = new Item();
        item.setThumbnailUrl("https://clips-media-assets2.twitch.tv/100660082970470268-offset-153206-preview-480x272.jpg");
        assertEquals("https://clips-media-assets2.twitch.tv/100660082970470268-offset-153206-preview-480x272.jpg",item.getThumbnailUrl());
    }

    @Test
    public void getGameId() {
        Item item = new Item();
        item.setGameId("490422");
        assertEquals("490422",item.getGameId());
    }

    @Test
    public void setGameId() {
        Item item = new Item();
        item.setGameId("97265");
        assertEquals("97265",item.getGameId());
    }

    @Test
    public void getBroadcasterName() {
        Item item = new Item();
        item.setBroadcasterName("divinesiatv");
        assertEquals("divinesiatv", item.getBroadcasterName());
    }

    @Test
    public void setBroadcasterName() {
        Item item = new Item();
        item.setBroadcasterName("Sweet_Anita");
        assertEquals("Sweet_Anita", item.getBroadcasterName());
    }

    @Test
    public void getItemType() {
        Item item = new Item();
        item.setItemType(ItemType.CLIP);
        assertEquals(ItemType.CLIP, item.getItemType());
    }

    @Test
    public void setItemType() {
        Item item = new Item();
        item.setItemType(ItemType.STREAM);
        assertEquals(ItemType.STREAM, item.getItemType());
    }

    @Test
    public void getUserSet() {
        Item item = new Item();
        Set<User> userSet = new HashSet<>();
        User user1 = new User();
        User user2 = new User();
        userSet.add(user1);
        userSet.add(user2);
        item.setUserSet(userSet);
        assertSame(userSet,item.getUserSet());
    }

    @Test
    public void setUserSet() {
        Item item = new Item();
        Set<User> userSet = new HashSet<>();
        User user3 = new User();
        User user4 = new User();
        userSet.add(user3);
        userSet.add(user4);
        item.setUserSet(userSet);
        assertSame(userSet,item.getUserSet());
    }
}