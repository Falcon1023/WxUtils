package org.trier.wechat.util;

import java.util.Random;

/**
 * 随机数生成算法
 */
public class NonceStrUtil {
    public static String getNonceStr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        int len = chars.length();
        for (int i = 0; i < length; i++) {
            int n = random.nextInt(len - 1);
            sb.append(chars.substring(n, n + 1));
        }
        return sb.toString();
    }
}
