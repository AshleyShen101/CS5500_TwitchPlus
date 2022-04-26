package com.group.jupiter.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    //test normal user_id and password encryption
    @Test
    public void encryptPassword1() {
        assertEquals("1113183d7dc42beec92b6a393230db4b",Util.encryptPassword("1234","1234") );
    }


    //test both empty string
    @Test
    public void encryptPassword2() {
        assertEquals("74be16979710d4c4e7c6647856088456",Util.encryptPassword("","") );
    }

    //test empty password
    @Test
    public void encryptPassword3() {
        assertEquals("7b3713abe3c07a914e17e7d5a0e3c453",Util.encryptPassword("1234","") );
    }

    //test empty userid
    @Test
    public void encryptPassword4() {
        assertEquals("ec6a6536ca304edf844d1d248a4f08dc",Util.encryptPassword("","1234") );
    }
}