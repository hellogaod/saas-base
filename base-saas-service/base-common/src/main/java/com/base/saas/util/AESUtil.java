package com.base.saas.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，
 * 在进行Base64编码转化；
 */
public class AESUtil {
    /*
     *加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */

    private static String sKey = "abcdef0123456789";
    private static String ivParameter = "0123456789abcdef";
    private static AESUtil instance = null;
    public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL4FE7anB8CQVSlNJTKcqMdtz9mmiN9pdRTqQB435dN8JCVS9GHDhULlm0fSgPaxeAS4BiPUexSRk9c3MCSdnq0T2rbgTCWsViZy38fKfcly+kDV19qdQVgc9YuId541liADkwtdmK7R9Llfd63x7zf8mQCZU5KhWuxRxunzmghzAgMBAAECgYB4ql6vYXghx0XdGVkiv6rKIaiYzREj1iL8ahjXV6XBW5bUgM4heviX15h4zmsIvP+692UmwZp7IEriovAWgGUu6Utgpezfp2k5IbTJVuhkT6LUWqExkqPxfsvBkVFREJNiDt1F45nAKXS49IzVynUpufNvTBpbJ4RHmuUuRFfvuQJBAN0rCFqP2SONu6BM1M8KlR/jqV+xY5vEBOqre5Uil/th2z2DRB/MsaLCgpb1QtoUXaJjIRLE4sEwwGb3Zo+kk7UCQQDb8jiWUo3SovPLcfP/w4Vf1TvaYgIuRKCYF6GmiJBhe9wv7I0WZMPT/CKWNIQI+A1byMDNdB+9CCRB+4Gu7hSHAkEAk9zcReecDTgJs58KO61gi6RiLVbkOFRx0Q56nfc10tvWfqaO2g+4xw7xzckFO9WX0CQkDW9SBkT4rR5EFE68bQJAa/RUr/uvHq7aqIWy9FtcUHBR41ttpJYMmPooXpvy+dYYefFKQq6Mq7S6qSz3jYtqyx0a/Py9Q0QGv9gZi+nRawJAf2T6ryfkCAtYrtO2CTmr7MF2gmt7GI7O547m9exSHfjNBouzVzZoHTu8ptnMOfvY5OTZB3M5+Yjwc1fu1vhJRA==";


    private AESUtil() {

    }

    public static AESUtil getInstance() {
        if (instance == null) {
            instance = new AESUtil();
        }
        return instance;
    }


    /**
     * 加密
     *
     * @param src 需要加密的原数据
     * @return
     */
    public static String encrypt(String src) {
        String result = "";
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
            result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 此处使用BASE64做转码。
        return result;

    }

    /**
     * 解密
     *
     * @param src 需要解密的加密数据
     * @return
     */
    public static String decrypt(String src) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(src);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, String privateKey) throws Exception {

        //写死私钥
        privateKey = AESUtil.privateKey;


        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey.getBytes("UTF-8"));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        PrivateKey privateKey1 = keyFactory.generatePrivate(keySpec);


        byte[] keyBytes = privateKey1.getEncoded();
        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec1);
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(key);
        signature.update(data.getBytes("UTF-8"));
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signature.sign()), "UTF-8");
    }

    public static String genAesSecret() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
            kg.init(128);
            SecretKey sk = kg.generateKey();
            String secret = Base64.getEncoder().encodeToString(sk.getEncoded());
            return secret;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此算法");
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        String s = genAesSecret();
//        System.out.println(s);
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey deskey = keygen.generateKey();
        System.out.println(Base64.getEncoder().encodeToString(deskey.getEncoded()));
        System.out.println(genAesSecret());
    }

}

