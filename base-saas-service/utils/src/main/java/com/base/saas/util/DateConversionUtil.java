package com.base.saas.util;


import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <strong>Title :<br>
 * </strong> <strong>Description :日期工具类</strong>@类注释说明写在此处@<br>
 * <strong>Create on : 2018年05月15日<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Vbill Co.,Ltd.<br>
 * </strong>
 * <p>
 *
 * @author department:技术开发部 <br>
 *         username:zhangtao <br>
 *         email: <br>
 * @version <strong>zw有限公司-运营平台</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class DateConversionUtil {


    private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static String STYLE_1 = "yyyy-MM-dd HH:mm:ss";

    public final static String STYLE_2 = "yyyy-MM-dd";

    public final static String STYLE_3 = "yyyyMMdd";

    public final static String STYLE_4 = "yyyyMMddhh";

    public final static String STYLE_5 = "yyyyMMddhhmm";

    public final static String STYLE_6 = "yyyy年MM月dd日HH时mm分ss秒";

    public final static String STYLE_7 = "yyyy年MM月dd日HH时mm分";

    public final static String STYLE_8 = "yyyy年MM月dd日";

    public final static String STYLE_9 = "hhmmss";

    public final static String STYLE_10 = "yyyyMMddhhmmss";

    public final static String STYLE_11 = "yyyyMMddHHmmss";

    public final static String STYLE_12 = "yyyyMMddHHmmssSSS";

    public final static String STYLE_13 = "dd";

    public final static String STYLE_14 = "MM-dd";

    public final static String STYLE_15 = "HHmmss";

    public final static String STYLE_16 = "yyyy-MM";

    public final static String STYLE_17 = "MM";

    public final static String STYLE_18 = "yyyy年MM月dd日HH时";

    public final static String STYLE_19 = "HH";

    public final static String STYLE_20 = "yyyy-MM-dd HH:mm";

    private static Map<String, SimpleDateFormat> sdfMap = new HashMap<String, SimpleDateFormat>();

    static {
        sdfMap.put(STYLE_1, new SimpleDateFormat(STYLE_1));
        sdfMap.put(STYLE_2, new SimpleDateFormat(STYLE_2));
        sdfMap.put(STYLE_3, new SimpleDateFormat(STYLE_3));
        sdfMap.put(STYLE_4, new SimpleDateFormat(STYLE_4));
        sdfMap.put(STYLE_5, new SimpleDateFormat(STYLE_5));
        sdfMap.put(STYLE_6, new SimpleDateFormat(STYLE_6));
        sdfMap.put(STYLE_7, new SimpleDateFormat(STYLE_7));
        sdfMap.put(STYLE_8, new SimpleDateFormat(STYLE_8));
        sdfMap.put(STYLE_9, new SimpleDateFormat(STYLE_9));
        sdfMap.put(STYLE_10, new SimpleDateFormat(STYLE_10));
        sdfMap.put(STYLE_11, new SimpleDateFormat(STYLE_11));
        sdfMap.put(STYLE_12, new SimpleDateFormat(STYLE_12));
        sdfMap.put(STYLE_13, new SimpleDateFormat(STYLE_13));
        sdfMap.put(STYLE_14, new SimpleDateFormat(STYLE_14));
        sdfMap.put(STYLE_15, new SimpleDateFormat(STYLE_15));
        sdfMap.put(STYLE_16, new SimpleDateFormat(STYLE_16));
        sdfMap.put(STYLE_17, new SimpleDateFormat(STYLE_17));
        sdfMap.put(STYLE_18, new SimpleDateFormat(STYLE_18));
        sdfMap.put(STYLE_19, new SimpleDateFormat(STYLE_19));
        sdfMap.put(STYLE_20, new SimpleDateFormat(STYLE_20));
    }

    public static void main(String[] args) {
        String aa ="2021-03-11+08:00";
        System.out.println(aa.replaceAll("\\+"," "));
        System.out.println(strConvertToDate("2021-03-11 08:00",STYLE_20));
    }
    /**
     * 获取当前时间 格式为yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        return sdfMap.get(STYLE_11).format(new Date());
    }

    /**
     * 获取指定格式的时间
     *
     * @param style 格式
     * @return
     */
    public static String getCurrentTime(String style) {
        if (StringUtils.isEmpty(style)) {
            style = STYLE_1;
        }
        return sdfMap.get(style).format(new Date());
    }

    /**
     * 求两个日期之间相隔天数,要求时间格式一致的2个日期
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param style     日期格式 必须跟开始时间和结束时间格式一样
     * @return long 相差天数
     */
    public static int getBetweenDaysWithStyle(String startDate, String endDate, String style) {
        long quot = 0;
        SimpleDateFormat ft = null;
        if (StringUtils.isEmpty(style) || sdfMap.get(style) == null) {
            style = STYLE_2;
        }
        ft = sdfMap.get(style);
        try {
            Date date1 = ft.parse(startDate);
            Date date2 = ft.parse(endDate);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(Long.toString(Math.abs(quot)));
    }

    /**
     * 将指定日期格式转换成日期类型
     *
     * @param str
     * @return
     */
    public static Date strConvertToDate(String str, String style) {
        if (StringUtils.isNotEmpty(str)) {
            try {
                return sdfMap.get(style).parse(str);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * yyyyMMdd转日期格式
     * @param str
     * @param beForStyle
     * @param afterStyle
     * @return
     */
    public static Date StrToDate(String str,String beForStyle,String afterStyle) {
        SimpleDateFormat formatter = new SimpleDateFormat(beForStyle);
        formatter.setLenient(false);
        Date newDate = null;
        try {
            newDate = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formatter = new SimpleDateFormat(afterStyle);
        String s= formatter.format(newDate);
        try {
            return sdfMap.get(afterStyle).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期类型转化为制定的日期格式
     *
     * @param date
     * @return
     */
    public static String dateConvertToStr(Date date, String style) {
        return sdfMap.get(style).format(date);
    }

    /**
     * 获取当前日期的任意前后多少天日期
     *
     * @param num   多少天
     * @param style 时间格式
     * @return
     */
    public static String getDay(int num, String style) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);    //得到前一天
        Date date = calendar.getTime();
        return sdfMap.get(style).format(date);
    }

    /**
     * 将yyyymmdd天格式的时间字符串转为yyyy-mm-dd格式
     *
     * @param str    需要转换的日期
     * @param style1 需要转换的日期格式
     * @param style2 转换之后的日期格式
     * @return 返回已格式化的字符串
     * @throws Exception
     */
    public static String getDateString(String str, String style1, String style2) throws Exception {
        SimpleDateFormat s = new SimpleDateFormat(style1);
        SimpleDateFormat s1 = new SimpleDateFormat(style2);
        Date date = s.parse(str);
        return s1.format(date);
    }

    /**
     * 获取当月的第几天时间
     *
     * @param style 时间格式
     * @param num   时间格式
     * @return
     */
    public static String getDayByMonth(int num, String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, num);//设置为几号
        String firstTime = format.format(c.getTime());
        return firstTime;
    }

    /**
     * 获取当月最后一天的日期
     *
     * @param style 时间格式
     * @return
     */
    public static String getLastDayByMonth(String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastTime = format.format(ca.getTime());
        return lastTime;
    }

    /**
     * 得到当前时间所处的时间段
     * 1 凌晨 2 早上 3中午 4下午 5 晚上
     *
     * @return
     */
    public static int PeriodOfTime() {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int result = 0;
        //早上
        if (hours >= 0 && hours <= 5) {
            //凌晨
            result = 1;
        } else if (hours >= 6 && hours <= 10) {
            //早上
            result = 2;
        } else if (hours >= 11 && hours <= 13) {
            //中午
            result = 3;
        } else if (hours >= 14 && hours <= 18) {
            //下午
            result = 4;
        } else if (hours >= 19 && hours <= 24) {
            //晚上
            result = 5;
        }
        return result;
    }
}
