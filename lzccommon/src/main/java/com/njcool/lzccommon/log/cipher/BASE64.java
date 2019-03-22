package com.njcool.lzccommon.log.cipher;

/**
 * @Description: Base64加密解密
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2017-01-12 10:58
 */
public class BASE64 {

    public static byte[] encode(byte[] plain) {
        return android.util.Base64.encode(plain, android.util.Base64.DEFAULT);
    }

    public static String encodeToString(byte[] plain) {
        return android.util.Base64.encodeToString(plain, android.util.Base64.DEFAULT);
    }

    public static byte[] decode(String text) {
        return android.util.Base64.decode(text, android.util.Base64.DEFAULT);
    }

    public static byte[] decode(byte[] text) {
        return android.util.Base64.decode(text, android.util.Base64.DEFAULT);
    }
}
