package com.group.jupiter.entity.db;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getUserId() {
        User user = new User();
        user.setUserId("abcd");
        assertEquals("abcd",user.getUserId());
    }

    @Test
    public void setUserId() {
        User user = new User();
        user.setUserId("qwer");
        assertEquals("qwer",user.getUserId());
    }

    @Test
    public void getPassword() {
        User user = new User();
        user.setPassword("1113183d7dc42beec92b6a393230db4b");
        assertEquals("1113183d7dc42beec92b6a393230db4b",user.getPassword());
    }

    @Test
    public void setPassword() {
        User user = new User();
        user.setPassword("ec6a6536ca304edf844d1d248a4f08dc");
        assertEquals("ec6a6536ca304edf844d1d248a4f08dc",user.getPassword());
    }

    @Test
    public void getFirstName() {
        User user = new User();
        user.setFirstName("alan");
        assertEquals("alan",user.getFirstName());
    }

    @Test
    public void setFirstName() {
        User user = new User();
        user.setFirstName("alex");
        assertEquals("alex",user.getFirstName());
    }

    @Test
    public void getLastName() {
        User user = new User();
        user.setLastName("parker");
        assertEquals("parker",user.getLastName());
    }

    @Test
    public void setLastName() {
        User user = new User();
        user.setLastName("miller");
        assertEquals("miller",user.getLastName());
    }

    @Test
    public void getItemSet() {
        User user = new User();
        Set<Item> itemSet = new HashSet<>();
        Item item1 = new Item();
        Item item2 = new Item();
        itemSet.add(item1);
        itemSet.add(item2);
        user.setItemSet(itemSet);
        assertSame(itemSet,user.getItemSet());
    }

    @Test
    public void setItemSet() {
        User user = new User();
        Set<Item> itemSet = new HashSet<>();
        Item item3 = new Item();
        Item item4 = new Item();
        itemSet.add(item3);
        itemSet.add(item4);
        user.setItemSet(itemSet);
        assertSame(itemSet,user.getItemSet());
    }
}