package com.group.jupiter.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
    public static String encryptPassword(String userId, String password) {
        return DigestUtils.md5Hex(userId + DigestUtils.md5Hex(password).toLowerCase());
    }
}
