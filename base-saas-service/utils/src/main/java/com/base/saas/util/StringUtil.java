package com.base.saas.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wangtao
 * @Date: 2018/05/14 17:42
 * @Description: 字符串工具类
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils{
    private static final char[] newLine = new char[2];
    static {
        newLine[0] = 13;
        newLine[1] = 10;
    }
    private static final String newLineString = new String(newLine);

    private static final String[] htmlSpecial = { "\u0026", "\u00A9", "\u00AE", "\u2122", "\"", "\u003C", "\u003E", newLineString };
    private static final String[] htmlESC = { "&amp;", "&copy;", "&reg;", "&trade;", "&quot;", "&lt;", "&gt;", "<br>" };

    private static final String[] jsSpecial = { "\\", "'", "\"", newLineString };
    private static final String[] jsESC = { "\\\\", "\\'", "\\\"", "\\n" };

    private static final String[] sqlSpecial = { "'" };
    private static final String[] sqlESC = { "''" };

    /**
     * Private constructor to prevent instantiation.
     */
    private StringUtil() {}

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 17:52
     * @Params: str（需要处理的字符串）
     * @Description: 如果是null字符串, 则返回""字符串
     * @return: str（处理后的字符串）
     */
    public static String filterNull(String str) {
        if (str == null) {
            return new String();
        }
        else {
            return str;
        }
    }

    /**
     * 判断字符串是否是json格式
     * @param content
     * @return
     */
    public static boolean isJSON(String content){
        if(isNotEmpty(content)){
            if(content.startsWith("{") && content.endsWith("}")){
                return true;
            }else if(content.startsWith("[") && content.endsWith("]")){
                return true;
            }
        }
        return false;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 17:54
     * @Params: obj(需要处理的对象)
     * @Description: 如果是null对象, 则返回""字符串
     * @return: String
     */
    public static String filterNullObject(Object obj) {
        if (obj == null) {
            return new String();
        }
        else {
            return obj.toString();
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 17:57
     * @Params: original(源字符串)，oldString（需要替换的字符串），newString（新字符串）
     * @Description: 把源字符串中的字符串（oldString）替换成新串（newString）
     * @return: String
     */
    public static String replace(final String original, final String oldString, final String newString) {
        return replace(original, oldString, newString, 0);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 17:57
     * @Params: original(源字符串)，oldString（需要替换的字符串），newString（新字符串），counts（替换次数）
     * @Description: 把源字符串中的字符串（oldString）替换成新串（newString） 多处替换
     * @return: String
     */
    public static String replace(final String original, final String oldString, final String newString, final int counts) {
        if (original == null || oldString == null || newString == null) {
            return "";
        }
        if (counts < 0){
            throw new IllegalArgumentException("parameter counts can not be negative");
        }
        final StringBuffer sb = new StringBuffer();
        int end = original.indexOf(oldString);
        int start = 0;
        final int stringSize = oldString.length();
        int currentCount = 0;
        while (end != -1) {
            if (counts == 0 || currentCount < counts) {
                sb.append(original.substring(start, end));
                sb.append(newString);
                start = end + stringSize;
                end = original.indexOf(oldString, start);
                currentCount++;
            } else{
                break;
            }
        }
        end = original.length();
        sb.append(original.substring(start, end));
        return sb.toString();
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:03
     * @Params: str(需要替换的字符串)
     * @Description: 将一个字符串中带有的HTML特殊字符转换为HTML的转义字符。 例如： & --> "amp;" > --> "gt;"
     * @return: String
     */
    public static String escapeHTMLSpecial(final String str) {
        return escapeSpecial(str, htmlSpecial, htmlESC);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:04
     * @Params: str(需要替换的字符串)
     * @Description: 将一个字符串中带有的JS特殊字符转换为JS的转义字符。 例如： \ --> "\\" ' --> "\'" " --> "\""
     * @return:
     */
    public static String escapeJSSpecial(String str) {
        return escapeSpecial(str, jsSpecial, jsESC);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:04
     * @Params: str(需要替换的字符串)
     * @Description: 将一个字符串中带有的SQL特殊字符转换为转义字符。 例如： ' --> "''"
     * @return:
     */
    public static String escapeSQLSpecial(String str) {
        return escapeSpecial(str, sqlSpecial, sqlESC);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:05
     * @Params: str(需要替换的字符串)，maxByteLength（截取长度），more（代替字符串）
     * @Description: 获得截取到指定字节长度后的字符串，多出部分用指定字符串代替。
     * @return: String
     */
    public static String getMoreString(final String str, final int maxByteLength, final String more) {
        if (str == null){
            return "";
        }
        int len = 0;
        StringBuffer buf = new StringBuffer();
        char c;
        boolean isSingleChar;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            byte[] bytes = charToByte(c);
            if (bytes[0] == 0) {
                len++;
            } else {
                len += 2;
            }
            if (len > maxByteLength) {
                buf.append(more == null ? "..." : more);
                break;
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:06
     * @Params: sourceString（源字符串），delim（分隔符）
     * @Description: 把由delim分割的字符串分裂并形成字符串数组。
     * @return: String[]
     */
    public static String[] split(String sourceString, String delim) {
        if (sourceString == null || delim == null){
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(sourceString, delim);
        List stringList = new ArrayList();
        for (; st.hasMoreTokens(); stringList.add(st.nextToken())){
        }
        return (String[]) (stringList.toArray(new String[stringList.size()]));
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:08
     * @Params: number(替换的数字)
     * @Description: 整数到字节数组的转换
     * @return: byte[]
     */
    public static byte[] intToByte(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = b.length - 1; i > -1; i--) {
            b[i] = new Integer(temp & 0xff).byteValue(); //将最高位保存在最低位
            temp = temp >> 8; //向右移8位
        }
        return b;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:09
     * @Params: b(替换的字节数组)
     * @Description: 字节数组到整数的转换
     * @return: int
     */
    public static int byteToInt(byte[] b) {
        int s = 0;
        for (int i = 0; i < 3; i++) {
            if (b[i] >= 0){
                s = s + b[i];
            }
            else{
                s = s + 256 + b[i];
            }
            s = s * 256;
        }
        //最后一个之所以不乘，是因为可能会溢出
        if (b[3] >= 0){
            s = s + b[3];
        }

        else{
            s = s + 256 + b[3];
        }
        return s;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:10
     * @Params: ch（替换的字符）
     * @Description: 字符到字节转换
     * @return: byte[]
     */
    public static byte[] charToByte(char ch) {
        int temp = (int)ch;
        byte[] b = new byte[2];
        for (int i = b.length - 1; i > -1; i--) {
            b[i] = new Integer(temp & 0xff).byteValue(); //将最高位保存在最低位
            temp = temp >> 8; //向右移8位
        }
        return b;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:10
     * @Params: b(替换的字节数组)
     * @Description: 字节到字符转换
     * @return: char
     */
    public static char byteToChar(byte[] b) {
        int s = 0;
        if (b[0] > 0){
            s += b[0];
        }
        else{
            s += 256 + b[0];
        }
        s *= 256;
        if (b[1] > 0){
            s += b[1];
        }
        else{
            s += 256 + b[1];
        }
        char ch = (char)s;
        return ch;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:10
     * @Params: d(替换的double)
     * @Description: double到字节转换
     * @return:
     */
    public static byte[] doubleToByte(double d) {
        byte[] b = new byte[8];
        long l = Double.doubleToLongBits(d);
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(l).byteValue();
            l = l >> 8;

        }
        return b;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:11
     * @Params: b(替换的字节数组)
     * @Description: 字节到double转换
     * @return:
     */
    public static double byteToDouble(byte[] b) {
        long l;

        l = b[0];
        l &= 0xff;
        l |= ((long)b[1] << 8);
        l &= 0xffff;
        l |= ((long)b[2] << 16);
        l &= 0xffffff;
        l |= ((long)b[3] << 24);
        l &= 0xffffffffL;
        l |= ((long)b[4] << 32);
        l &= 0xffffffffffL;

        l |= ((long)b[5] << 40);
        l &= 0xffffffffffffL;
        l |= ((long)b[6] << 48);

        l |= ((long)b[7] << 56);
        return Double.longBitsToDouble(l);
    }

    private static String escapeSpecial(String str, String[] special, String[] esc) {
        String result = str;
        for (int i = 0; i < special.length; i++) {
            result = replace(result, special[i], esc[i]);
        }
        if(result.contains("%")){
            result = result.replaceAll("%", "/%");
        }
        if(result.contains("_")){
            result = result.replaceAll("_", "/_");
        }
        return result;
    }

    private static final int base64Int(char c) {
        if (c >= 'a'){
            if (c <= 'z'){
                return (c - 97) + 26;
            }
            else{
                return -1;
            }
        }

        if (c >= 'A'){
            if (c <= 'Z'){
                return (c - 65) + 0;
            }
            else{
                return -1;
            }
        }
        if (c >= '0') {
            if (c <= '9'){
                return (c - 48) + 52;
            }
            return c != '=' ? -1 : 0;
        }
        if (c == '+'){
            return 62;
        }
        return c != '/' ? -1 : 63;
    }
    private static final char base64Char(int i) {
        if (i >= 52) {
            if (i < 62){
                return (char) ((i - 52) + 48);
            }
            if (i == 62){
                return '+';
            }
            return i != 63 ? '?' : '/';
        }
        if (i >= 26){
            return (char) ((i - 26) + 97);
        }
        if (i >= 0){
            return (char) ((i - 0) + 65);
        }
        else {
            return '?';
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:12
     * @Params: s（base64）
     * @Description: base64转字节数组
     * @return:
     */
    public static final byte[] base64ToBytes(String s) {
        int k = 0;
        char ac[] = s.toCharArray();
        int i = ac.length;
        int j = 0;
        if (ac[i - 1] == '='){
            j++;
        }
        if (ac[i - 2] == '='){
            j++;
        }
        i = (i / 4) * 3 - j;
        byte abyte0[] = new byte[i];
        int l = 0;
        int i1 = 0;
        while (l + 3 < ac.length) {
            k = base64Int(ac[l + 0]) << 18 | base64Int(ac[l + 1]) << 12 | base64Int(ac[l + 2]) << 6 | base64Int(ac[l + 3]);
            l += 4;
            if (i1 >= i - 2){
                break;
            }
            abyte0[i1++] = (byte) (k >> 16 & 0xff);
            abyte0[i1++] = (byte) (k >> 8 & 0xff);
            abyte0[i1++] = (byte) (k & 0xff);
        }
        if (i1 < abyte0.length){
            abyte0[i1++] = (byte) (k >> 16 & 0xff);
        }
        if (i1 < abyte0.length){
            abyte0[i1++] = (byte) (k >> 8 & 0xff);
        }
        return abyte0;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:12
     * @Params: abyte0[](字节数组)
     * @Description: 字节数组转base64
     * @return:
     */
    public static final String bytesToBase64(byte abyte0[]) {
        int j1 = 0;
        int k1 = 0;
        int i = (((abyte0.length + 3) - 1) / 3) * 4;
        char ac[] = new char[i];
        for (int j = abyte0.length - 2; j1 < j;) {
            int k = (abyte0[j1 + 0] & 0xff) << 16 | (abyte0[j1 + 1] & 0xff) << 8 | abyte0[j1 + 2] & 0xff;
            j1 += 3;
            ac[k1++] = base64Char(k >> 18 & 0x3f);
            ac[k1++] = base64Char(k >> 12 & 0x3f);
            ac[k1++] = base64Char(k >> 6 & 0x3f);
            ac[k1++] = base64Char(k & 0x3f);
        }

        if (j1 < abyte0.length - 1) {
            int l = (abyte0[j1 + 0] & 0xff) << 16 | (abyte0[j1 + 1] & 0xff) << 8;
            ac[k1++] = base64Char(l >> 18 & 0x3f);
            ac[k1++] = base64Char(l >> 12 & 0x3f);
            ac[k1++] = base64Char(l >> 6 & 0x3f);
            ac[k1++] = '=';
        } else if (j1 < abyte0.length) {
            int i1 = (abyte0[j1 + 0] & 0xff) << 16;
            ac[k1++] = base64Char(i1 >> 18 & 0x3f);
            ac[k1++] = base64Char(i1 >> 12 & 0x3f);
            ac[k1++] = '=';
            ac[k1++] = '=';
        }
        return new String(ac, 0, k1);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:15
     * @Params: str（需处理字符串）
     * @Description: 首字母小写
     * @return: String
     */
    public static String lowerFirst(String str) {
        if (StringUtil.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:15
     * @Params: str（需处理字符串）
     * @Description: 首字母大写
     * @return: String
     */
    public static String upperFirst(String str) {
        if (StringUtil.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:17
     * @Params: html(需处理的字符串)
     * @Description: 替换掉HTML标签方法
     * @return:
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:17
     * @Params: str(目标字符串),length(截取长度)
     * @Description: 缩略字符串（不区分中英文字符）
     * @return: String
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:17
     * @Params: str(目标字符串),length(截取长度)
     * @Description: 缩略字符串（替换html）
     * @return: String
     */
    public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:21
     * @Params: val(需要转型的对象)
     * @Description: 对象转换为Double类型
     * @return: Double
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:21
     * @Params: val(需要转型的对象)
     * @Description: 对象转换为Float类型
     * @return: Float
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:21
     * @Params: val(需要转型的对象)
     * @Description: 对象转换为Long类型
     * @return: Long
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/14 18:21
     * @Params: val(需要转型的对象)
     * @Description: 对象转换为Integer类型
     * @return: Integer
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: tel(需加密的手机号)
     * @Description: 加密手机号
     * @return: String
     */
    public static String encryptPhone(String tel) {
        if (tel != null && !"".equals(tel.trim())) {
            if (tel.length() == 11) {
                tel = AesUtil.encrypt(tel);
            }
        }
        return tel;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: tel(需解密的手机号)
     * @Description: 解密手机号
     * @return: String
     */
    public static String decryptPhone(String tel) {
        if (tel != null && !"".equals(tel.trim())) {
            if (tel.length() > 11) {
                tel = AesUtil.decrypt(tel);
            }
        }
        return tel;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: customerName(需加密的用户名)
     * @Description: 加密用户名
     * @return: String
     */
    public static String encryptCustomerName(String customerName) {
        if (customerName != null && !"".equals(customerName.trim())) {
            if (customerName.matches("[\\u4e00-\\u9fa5]+")) {
                customerName = AesUtil.encrypt(customerName);
            }
        }
        return customerName;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: customerName(需解密的用户名)
     * @Description: 解密用户名
     * @return: String
     */
    public static String decryptCustomerName(String customerName) {
        if (customerName != null && !"".equals(customerName.trim())) {
            if (!customerName.matches("[\\u4e00-\\u9fa5]+")) {
                customerName = AesUtil.decrypt(customerName);
            }
        }
        return customerName;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: idCard(需加密的身份证)
     * @Description: 加密身份证
     * @return: String
     */
    public static String encryptIdCard(String idCard) {
        if (idCard != null && !"".equals(idCard.trim())) {
            if (idCard.length() == 18) {
                idCard = AesUtil.encrypt(idCard);
            }
        }
        return idCard;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: idCard(需解密的身份证)
     * @Description: 解密身份证
     * @return: String
     */
    public static String decryptIdCard(String idCard) {
        if (idCard != null && !"".equals(idCard.trim())) {
            if (idCard.length() > 18) {
                idCard = AesUtil.decrypt(idCard);
            }
        }
        return idCard;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: card(需加密的银行卡号)
     * @Description: 加密银行卡号
     * @return: String
     */
    public static String encryptCard(String card) {
        if (card != null && !"".equals(card.trim())) {
            if (card.matches("^[0-9]*$")) {
                card = AesUtil.encrypt(card.replaceAll(" ",""));
            }
        }
        return card;
    }

    /**
     * @Author: wangtao
     * @Date: 2018/05/15 09:03
     * @Params: card(需解密的银行卡号)
     * @Description: 解密银行卡号
     * @return: String
     */
    public static String decryptCard(String card) {
        if (card != null && !"".equals(card.trim())) {
            if (!card.replaceAll(" ","").matches("^[0-9]*$")) {
                card = AesUtil.decrypt(card);
            }
        }
        return card;
    }

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    public static void main(String[] args) {
        System.out.println(getPinYinHeadChar("哈哈哈哈").toUpperCase().substring(0,4));
        String code = new GUIDUtil().toString();
        String lastCode = code.substring(code.length()-8,code.length());
        System.out.println(lastCode);
    }
}
