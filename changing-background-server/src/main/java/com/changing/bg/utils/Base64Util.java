package com.changing.bg.utils;


import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/************
 * java8 自带的base64编码,只能在java8及以上使用
 *
 */
public class Base64Util {

    private static Encoder encoder = Base64.getEncoder();

    private static Decoder decoder = Base64.getDecoder();

    //用于处理url的编码（比如/）
    private static Encoder urlEncoder = Base64.getUrlEncoder();
    //用于处理url的编码（比如/）
    private static Decoder UrlDecoder = Base64.getDecoder();

    public static String encoding(String src) {
        return encoder.encodeToString(src.getBytes(Charset.defaultCharset()));
    }

    public static String decoding(String src) {
        return new String(decoder.decode(src.getBytes(Charset.defaultCharset())));
    }

    public static String encode(byte[] src) {
        return encoder.encodeToString(src);
    }

    public static byte[] decode(String src) {
        return decoder.decode(src.getBytes(Charset.defaultCharset()));
    }

}