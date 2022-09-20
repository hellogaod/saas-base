package com.base.saas.util;

import com.alibaba.fastjson.util.IOUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * RSA加密-支持大数据加密
 * @author Huang ZheZhi
 */
public class RsaUtils {

    private static final String CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";
    private static RSAPrivateKey privateKey;
    private static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+BRO2pwfAkFUpTSUynKjHbc/Z\n" +
            "pojfaXUU6kAeN+XTfCQlUvRhw4VC5ZtH0oD2sXgEuAYj1HsUkZPXNzAknZ6tE9q2\n" +
            "4EwlrFYmct/Hyn3JcvpA1dfanUFYHPWLiHeeNZYgA5MLXZiu0fS5X3et8e83/JkA\n" +
            "mVOSoVrsUcbp85oIcwIDAQAB";

    /**
     * 使用私钥加密
     */
    public static String privateEncrypt(String data){
        try{
            keyGenerator();
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 使用私钥解密
     */
    public static String privateDecrypt(String data){
        try{
            keyGenerator();
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    public static void main(String[] args) {
        String ss = "HmQqmYKT6phiX0ZdJTE9LEALus2chfpGMq+mieCibEbRblmIfcVQmH7UaQJ9c66pXai0rWYM+B+Getrkk9d5v/pzRHtlnovgV2R1d8NQogTSVFj4Vrtpa70XVvrDL7s287JCP1QzaQftwfOx3Lzk00Yc4rkPSe/DNLFUD8a+f8mOV7YIZLAXBAD6v0t/nGtk/XB5f0f4KCy7x7O0TwnmiIvUhCxR4NIyixz/3guV15NAItDPmljHcXMRT63ILQMiI3KT+RKapHjbgAQ+aOj8sBiHDiXd4EKxu2zZoV6J2FK3ibbi3RdVo/ML5cQC3dnRX+avSr9LPYNXf+ehwseWVPYr0wSHFgYrUFMKgbJnZaXUJcgFKynGDzNW0qRUYXOl5jMxLQydFnqFq/Nxz/3pCa7pVFEqZmAC0J2Wp1NB8koBHjYqaACS/cZBnVo3+tYiJxB7xehcBhfx9RdSTDZ2nfY+4xdHeYa0hb2ThNatPor9/PauRCVDZJ58oPkOrqUfaiIu22MNkNDXdQeeoSRV9dPn7B3UzmQp4/azQHZdAVE4r8thm5sxpYzCGoeMEfz2MyruBUa3VFQeYyDmN8ZpOaeBYPiBhaFix1u1vM07W5rgaOuY2xf6lnVJMY9f1Jn4EQsu1ULfkYMYQh7fBHVPlwxjVVn2V8flO/ckBwMWBWc:";
        String yy = "WHZq%2Fnf17+5NjBCib2srFku+CeA+Y%2FhDLQZ7WnQVc3IeaweVKqInG0Q3uJs6adUaFgg4eHefqJ2foFJ%2FhBXaykAGxZpE7EcA5TtrCFDpHAUdDp6KkCKZaRdCSld6dU0ncNxZ3HfK8fQI7YWwQvLpvXXwzWvIH0p6osvv4XyDFVyFawEsZegFVR6une0%2FxWXPVl+Y1yb2EMYZOEehPDHjSWz%2FkmwcxS153LXB%2FC%2FyQJjNJ7EMorGSQEiVWwHIQoCyGFEHnKaWPrQ2hVmo53+K52AK3JRJaOQyOtD+ecisi9eqW7Af6aNiZRzNpYQWtdpX6n36uIgA7vIbOK8f6AA8hzT645XvWErwQ9f1shRyjrfUoCP59uqDeUQA1XbsCouiQHsoXaw%2F8f7fLlw3ZqyblaeynDRwCVM%2FqhUh65nbYLv%2FLtshMtXfqTodIY%2FrAG70As%2FcSlCRehBxoaLnzPTzUghnZa%2F+FsrH7qzStGWpZ0+f4r8NRS2nyzZmR627mPuCvU1LmqHMAbBRQabSFDx+LHZBKf2Mn07Wfl+L0tc8oXEUHifVpJkyj9p8F0FG5zQb43qHJIKeesbVbl%2F93iLg%2FVQ3omqPNum7l6WZIrAqqJ9%2FCkuRZvnvW9SzB5xWApac1A2Ov0SqjACPwj08BrDERWnzZskducw%2FUdJoxrj67CE=";
        String cc = "WHZq/nf17+5NjBCib2srFku+CeA+Y/hDLQZ7WnQVc3IeaweVKqInG0Q3uJs6adUaFgg4eHefqJ2foFJ/hBXaykAGxZpE7EcA5TtrCFDpHAUdDp6KkCKZaRdCSld6dU0ncNxZ3HfK8fQI7YWwQvLpvXXwzWvIH0p6osvv4XyDFVyFawEsZegFVR6une0/xWXPVl+Y1yb2EMYZOEehPDHjSWz/kmwcxS153LXB/C/yQJjNJ7EMorGSQEiVWwHIQoCyGFEHnKaWPrQ2hVmo53+K52AK3JRJaOQyOtD+ecisi9eqW7Af6aNiZRzNpYQWtdpX6n36uIgA7vIbOK8f6AA8hzT645XvWErwQ9f1shRyjrfUoCP59uqDeUQA1XbsCouiQHsoXaw/8f7fLlw3ZqyblaeynDRwCVM/qhUh65nbYLv/LtshMtXfqTodIY/rAG70As/cSlCRehBxoaLnzPTzUghnZa/+FsrH7qzStGWpZ0+f4r8NRS2nyzZmR627mPuCvU1LmqHMAbBRQabSFDx+LHZBKf2Mn07Wfl+L0tc8oXEUHifVpJkyj9p8F0FG5zQb43qHJIKeesbVbl/93iLg/VQ3omqPNum7l6WZIrAqqJ9/CkuRZvnvW9SzB5xWApac1A2Ov0SqjACPwj08BrDERWnzZskducw/UdJoxrj67CE:";
        System.out.println(privateDecrypt(ss));
    }

    //获取key
    private static void keyGenerator(){
        try {
            //位数：4096位,可以加密500字符，密钥可以改为配置文件的方式，以后调整
            String key = "MIIJRAIBADANBgkqhkiG9w0BAQEFAASCCS4wggkqAgEAAoICAQDCxR3xmZmY83EX\n" +
                    "ILI28/cdba41UreSr4DqyRrGYNGiwUwAnq3Uf86KMarje+zXonarGDZYu2q5M66p\n" +
                    "7mi6vMyHtldqhst8rCtaI6qHthJduV3XEdWD2aYBFntHt7/fs0MOlRb2lQdMBF3Q\n" +
                    "T345Do8jOF02ZTEcdOM5Y+6FF4dJiegtgjHZz4m1FELZdy6LRLuMWAdICPHKCpik\n" +
                    "35OQEx/3kpJiJNFus0oroB2q4W/aPO29FauK8bZjBBGPLRIVK1wpof3vswAGEazd\n" +
                    "hyI/BZ6zI32vX9D0FgtjFxJbsKRWRU2+EJrZdSG34wlPJhAJenT79UUUSKCLorAP\n" +
                    "baOTXawDzzKUKBcTaYVNyNjrNcGvBtPr4OiBWY5YmvvSg2pyy9Z5HExpBKaU9ce8\n" +
                    "157u5xIWYWwZqBc7CClZLTw6nW+/wZ7d0S1IxVb1EpOR69QjN2aaRTLuoighrlPl\n" +
                    "DXv33Lw2vZFgAloTVvqOlB3zGGx67cxic4DXrOaMV92YCWqk1K366wN6lSTog2TT\n" +
                    "9YZcnr1DJQJ0xHS01h5FPdREJV0l39LuCwBa2D/FiyHw3QWisDAam42hWA6QgjRc\n" +
                    "tL2vExvd2iMX7mT1g6+Ynnb5NsjMIkjSWIpwwiXfVlZ+rHGbcIBKqC+xLMaRyl5l\n" +
                    "WJy2cv+9kw+DlGruO/5tL+YnhF1WfQIDAQABAoICAQCjnY9P++Zp9GdWNGNBgqRe\n" +
                    "llq39Kq9kK1Njm7tozu364V7J9GDJ3JL6PlTbDFD2/iwNgDBfX9j4SSjH+HerOds\n" +
                    "QiMhG+co7kDk0f1ZT/wRu7w3BYZF4rpvQ4GAd725oA9+2oOV1Q9yLmDYs06ng7F+\n" +
                    "tsSnA07ERvTyqx4ds+w0PKNMR4i1HoDiGwklQddZqv2aIgDL5KepQEmGh0Kgz1AV\n" +
                    "W42Y4LEGu5pdF36UpBigTnqwUtnuOKssdqiBjC0al3guiSSXDdnBP2vmFLUSGBh/\n" +
                    "CBt3DZis0y/xp9oGKFeFGWb7eVt7vcf7f7+8O49RRwuq9Rl4tqIXay1XaJT3IMN0\n" +
                    "XzAszgXe33up3ibgYBvUY3yijRWI1vp+rTHwOBMrQ8QYmDa8PlIN2riVe+5NNKSU\n" +
                    "o53SOSMyfTcoWJIvU6rzMybsb90qcx5cIwIB26HBROUufB4LvTgqBNDB23kyQ920\n" +
                    "aPyH1+j/+XIb4Qbwo1sW6LSr/bAk0MKQ7qMxYKRLjiBNSreMxasjNGlpT5gIgv4U\n" +
                    "bbuS++62xN5wqY3SdR1IKmNHQPEb6m1z7GewNv3BjTSO4PBDIHei06uNPvlLkuFx\n" +
                    "pCKHWvO2BQIq1QxID7asLjWzd/naVS8i3YBtV8f6+p7EgsRPyrZAQOveCqhSiy7D\n" +
                    "2TZG9XF3cAlPgLJcy4ibSQKCAQEA848vSgiN4NBbQ1F/CGsH5xfcAshV+XTduZiM\n" +
                    "zm4+5q97FMzvxuTskx2mMWYSmVk4Vhj+hW+kDz5IzsXy1risptqHFPy7bxCX9l3E\n" +
                    "zY+p1AUGG0KDEaoHKtLrhtA+f7OgC3VwPp3vUFMmznmNzGiv+Ized10vYmFcZoiB\n" +
                    "tcJFyz6uE6fkO88PJg5gtigt0vwpRtGwPZy6iWZL7t/Wp0j2QepzgLNalezUoAh7\n" +
                    "hhnXHOl6/wSrC3/Pknh9Y/clZsSrbhLrYrg6n+Fdk3FRfc+wpnWYvHQV3XnNe1NU\n" +
                    "eOV/6An6mOjmwUaiW1+ugbzCt61B5FtflQHxa3Ut4aOjdYbBUwKCAQEAzLf0zU3q\n" +
                    "T7bUNsE/TNrnvkaMKbcgcg+Tv/0fz0BID/joyWe86+M2ERLl5+PfSzZO/cggzcuS\n" +
                    "sUN/0itvTT8/+fTD/ya2Fo6b+LiJOu6Hb/sDK+Ffng1pApWLCDuiA/yQAX+edliV\n" +
                    "zxYWWtsxTwxBKVRv7rpl6b9s0IynhyANL5o7QnPCoTSzMLty9F3k3aKn2e5c1aAB\n" +
                    "3y2XtvQ5Gfms16Kz5sAAFaP/dtLn7jQjF752XlThzG20cyi+IX31VojRsfheVKkW\n" +
                    "2DLIVOoVvbjRNcmjZfSV4MmII4Sk7pT55s+6Acmqm/rw/ZJ1TNMTSe5WHFcfIpcC\n" +
                    "S2m7W5scEPh+7wKCAQEAgu9W3NX8b/+TEH2fbUcc2H9bJ6PLlG/fQwdfqxpZdmqg\n" +
                    "IG6L+MDY7b3iumvFU+tC/gYxzkpWSFuTiJWiFwzwxEJ2nDHCEdM53phKVLgG9JQ+\n" +
                    "1CjNjZF6iOsa8z7AWqaWQ5K+5OrC3MvW8Okn8dvVvij87+6IqrrskbKRCMWH/9uS\n" +
                    "eb6oU/7chfj0xPiUqfgluIzB0l2Pao40BBcRrpzqxkTkEyxYZoELTX1B6rW5jzF1\n" +
                    "jeNUkLoFPRjJUBRtZvITtvk00b14xrrBM6KXzKr6rIJLugQuPQJeFQ6XOucl+/hh\n" +
                    "YBLU+tKeNcEab2SqyVO7OKhmmhSRVNOJ9uOh3Gy1QQKCAQAlcz2YVTmZDZQy1BVV\n" +
                    "ZPJiD5vSgcqwswLPa2nnSxYjveAwAAdCi9U3OGLQkhImOzAmIvdFToluXi/C7ek1\n" +
                    "k22Lt2gnhBhpdfs4e+G24y3poVzKKTeJvWBJdbvybJ5N5mUdsQ/3DGuTb7H1OwYB\n" +
                    "NPeyrNyxKm48XaV1XVXEVWu0Gp3eoTiorvBc6N21sHuOxBO6vW1EMzpEbp3DfVf4\n" +
                    "1YD2DbCldNhPHSo9vYVqQSVuUYxr41FPSX5qVri5fcZk5zCdQxdWm3wvXtLhHdWO\n" +
                    "WIIFOfbnTuAgOB3siXZ2otA4liWr8A2C7VwqtyE4RaDQtIYSNynd9QwZaaU5s91l\n" +
                    "iWAHAoIBAQC1h2BO2+HiEDgumfyE7klUYiLEGluVQOrBovOxCyetbURD4WNMJStG\n" +
                    "quhvRBlMu49EKXz5npcA3cgMpRCR6gEEWqQDsKpUYolJWDhUDnx7V8wck3Mp5AcJ\n" +
                    "fUVkyJE6PuPLCe3MKcnoNi+piBPqRaRfQc6SVmDwV6rEbzy+d3XYBbKhBvfXEGcl\n" +
                    "g5WYiwkF3X3VVyiL2lBoTht40+3kqqeHEAkncJgg+t4uLa1aKR8MNfs4WCoI13Mg\n" +
                    "dmjXwEPEeZcPE9h7UgIvOO/71Mpi2yAkCpbhU1ph1ZQgMXvwNtpcJa09sFJeoAqy\n" +
                    "+ZmRtHfF6lxdoc/oEPNjV8ALifMqhu3X".replace("\n","");
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            //通过PKCS#8编码的Key指令获得私钥对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            throw new RuntimeException("不支持的密钥", e);
        }
    }

    //切割字节，避免长数据阈值溢出导致数据不完整
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.close(out);
        return resultDatas;
    }

}
