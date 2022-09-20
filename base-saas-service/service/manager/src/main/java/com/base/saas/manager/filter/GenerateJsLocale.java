package com.base.saas.manager.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * @ClassName GenerateJsLocale
 * @Description 加载系统支持的多语言资源文件
 * @Author coder_bao
 * @Date 2018/8/31 13:28
 */
@Component
public class GenerateJsLocale implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(GenerateJsLocale.class);
    @Value("${localelist}")
    private String localelist;

    public static final Map<String, String> _LOCALEMAP = new HashMap<>();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {//取配置文件的
            List<String> localeList = Arrays.asList(localelist.split(","));
            for (String string : localeList) {
                //try (InputStream in = new FileInputStream((new ClassPathResource("classpath:i18n/messages_" + string + ".properties")).getFile())) {
                try (InputStream in = GenerateJsLocale.class.getClassLoader().getResourceAsStream("i18n/messages_" + string + ".properties")) {
                    Properties prop = new Properties();
                    try (Reader reader = new InputStreamReader(in, "UTF-8")) {
                        prop.load(reader);
                        String language = "_localeLanguage=" + JSON.toJSONString(prop);
                        _LOCALEMAP.put(string, language);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Failed to start GenerateJsLocale", ex);
            throw new RuntimeException("Failed to start GenerateJsLocale", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
