package com.group.jupiter.entity.response;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void getId() {
        Game.Builder builder = new Game.Builder();
        builder.id("1234");
        builder.name("test");
        builder.boxArtUrl("https://www.twitch.tv/null");

        Game game = builder.build();

        assertEquals("1234", game.getId());
    }

    @Test
    public void getName() {
        Game.Builder builder = new Game.Builder();
        builder.id("1234");
        builder.name("test");
        builder.boxArtUrl("https://www.twitch.tv/null");

        Game game = builder.build();

        assertEquals("test", game.getName());
    }

    @Test
    public void getBoxArtUrl() {
        Game.Builder builder = new Game.Builder();
        builder.id("1234");
        builder.name("test");
        builder.boxArtUrl("https://www.twitch.tv/null");

        Game game = builder.build();

        assertEquals("https://www.twitch.tv/null", game.getBoxArtUrl());
    }
}