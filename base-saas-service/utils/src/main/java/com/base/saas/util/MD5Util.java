package com.base.saas.util;

import java.security.MessageDigest;

/**
 * <strong>Title : <br>
 * </strong> <strong>Description : MD5加密</strong><br>
 * <strong>Create on : 2018年05月14日 16:44<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Vbill Co.,Ltd.<br>
 * </strong>
 * <p>
 *
 * @author department:技术开发部 <br>
 *         username:程锐 <br>
 *         email: <br>
 * @version <strong>zw有限公司-运营平台</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class MD5Util {

    /**
     * 全局数组
     */
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
    * @Description:  将字节转换为字符串和数字, 返回形式为数字跟字符串
    * @Param: [需要转换的字节: bByte]
    * @return: java.lang.String
    * @Date: 2018/5/15
    */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
    * @Description:  将字节转换为数字, 返回形式只为数字
    * @Param: [需要转换的字节: bByte]
    * @return: java.lang.String
    * @Date: 2018/5/15
    */
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    /**
    * @Description:  转换字节数组为16进制字串
    * @Param: [传过来的字节数组: bByte]
    * @return: java.lang.String
    * @Date: 2018/5/15
    */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }



    /**
    * @Description:  将字符串转换为MD5加密
    * @Param: [需要加密的字符串: strObj]
    * @return: java.lang.String
    * @Date: 2018/5/15
    */
    public static String GetMD5Code(String strObj) throws Exception {
        String resultString = null;
        resultString = new String(strObj);
        MessageDigest md = MessageDigest.getInstance("MD5");
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        resultString = byteToString(md.digest(strObj.getBytes()));
        return resultString;
    }

    /**
    * @Description: 无参构造器
    */
    public MD5Util() {}
}
