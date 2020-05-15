package com.changing.bg.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    // RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    // RSA最大解密密文大小
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static byte[] decryptBASE64(String key) {
        return Base64Util.decode(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64Util.encode(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     * <p>
     * [@param](https://my.oschina.net/u/2303379) data 加密数据
     * [@param](https://my.oschina.net/u/2303379) privateKey 私钥
     * [@return](https://my.oschina.net/u/556800)
     * [@throws](https://my.oschina.net/throws) Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     * <p>
     * [@param](https://my.oschina.net/u/2303379) data 加密数据
     *
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
        return decryptByPrivateKey(decryptBASE64(data), key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] encryptByPublicKey(String data, String publicKey) throws Exception {
        return encryptByPublicKey(data.getBytes("UTF-8"), publicKey);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64Util.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap) throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, String> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, Base64Util.encode(keyPair.getPublic().getEncoded()));// 公钥
        keyMap.put(PRIVATE_KEY, Base64Util.encode(keyPair.getPrivate().getEncoded()));// 私钥
        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> initKey = initKey();
//        String publicKey = initKey.get(PUBLIC_KEY);
//        String privateKey = initKey.get(PRIVATE_KEY);

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEIsLBOgiJdzxgyOaB5/PYgj8gx5zFkqFWUNV7TJCuKDP8vqkZ0KZJgWb80uZKmDziOX5VJNc9ARmNFE/GE4tQGCDuhn4kS4FN0P32qMJa2dPThHhwZHa6nHdstTMtx7Z0QaAyawgcV2JoNNM8gufUkzr8+DdS0CbY0wFCRyFHywIDAQAB";
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMQiwsE6CIl3PGDI5oHn89iCPyDHnMWSoVZQ1XtMkK4oM/y+qRnQpkmBZvzS5kqYPOI5flUk1z0BGY0UT8YTi1AYIO6GfiRLgU3Q/faowlrZ09OEeHBkdrqcd2y1My3HtnRBoDJrCBxXYmg00zyC59STOvz4N1LQJtjTAUJHIUfLAgMBAAECgYAtRXS8kv+bczktgdGq1HY1WELn9wsZk3HH8ZqE/jQ67q6pPPzdJ4rqVoMGAkwDlcYf1XNyhbFktFdIeBK9vo8eAuEDR4KMntZ5mDTGqEGH4l7ox/viti+DkxfxjGDHkyJaiNqNUZgj6LTtn0VJXfAroxf0AD+uK94G8Kp3X8aEyQJBAPJW3NPAya3Ztm538RiFXnC00z20Z28b6hPcMJIOzkf5K9GV3x4ablzzrRkiBCE06y7bb3HMVfNpjORl67Z+kiUCQQDPMSWTBzSVG1KywEHlDhbDEoamXyBCvdZcpcjD3/PeI6QkVzA/7GszgD2z2hip1YtLKhLGv8RLiJENZoUD/LcvAkAK4AUG56blS1jZej5yiEGlsyerpDXkEY+elooDc/Cj4DWsMRqTIkKDn1fMQe+HgfVv498Lb00IIFt3QG+kf+eVAkA9jn16XEasEi0UvHgXZIMwu71TiqmcCqi4Z+zo/Q2ILxCiV2EXbNdAutYaLC7trmsvDrX7ZJgeVNm7oHzefWOzAkAtVjVJPHPt6VZHbNkTkMcAbBGLpT23m6/D+I/B0qWDz1U9WF5pUgygs7lKTbyIyjakp+CCsXjbfPQD/25ohx3p";
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        TreeMap<String, String> params = new TreeMap<>();
        params.put("busiDate", "20200516150725");
        params.put("extInfo", "我要传递扩展信息");
        params.put("lndJrnlId", "20200515094901");
        params.put("ordrId", "PO2020051514520375044880");
        params.put("pdNo", "CPBH10001709120413");
        params.put("syscode", "00001");
        params.put("randomNum", "1010");
        StringBuilder signStr = new StringBuilder();
        for (String key : params.keySet()) {
            signStr.append(params.get(key));
        }

        byte[] encodedData = RSAUtil.encryptByPublicKey(signStr.toString(), publicKey);
        String encryptBASE64 = encryptBASE64(encodedData);
        System.err.println("加密前: " + signStr);
        System.out.println("加密后:" + encryptBASE64);

        byte[] decodedData = RSAUtil.decryptByPrivateKey(encryptBASE64, privateKey);
        String outputStr = new String(decodedData);
        System.err.println("解密后: " + outputStr);

        System.err.println("\r\n私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSAUtil.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSAUtil.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
    }

}
