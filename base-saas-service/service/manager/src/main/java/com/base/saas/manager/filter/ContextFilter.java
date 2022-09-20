package com.base.saas.manager.filter;

import com.base.saas.common.userinfo.UserContextUtil;
import org.apache.logging.log4j.ThreadContext;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class ContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            UserContextUtil.setHttpServletRequest(WebUtils.toHttp(request));
            UserContextUtil.setHttpServletResponse(WebUtils.toHttp(response));
            /* String appId = UserContextUtil.getAttribute(AppConstant.APP_SYS_ID_KEY);
            if (appId == null) {
                UserContextUtil.setAttribute(AppConstant.APP_SYS_ID_KEY, AppConstant.APP_SYS_ID_VALUE);
            }
            UserInfo user = UserContextUtil.getUserInfo();
            String account = "unknown";
            if (user != null) {
                account = user.getAccount();
            }
            ThreadContext.put("account", account);
            String requestId = UserContextUtil.getSession().getId() + ":" + System.currentTimeMillis();
            ThreadContext.put("requestId", requestId);*/
            chain.doFilter(request, response);
        } finally {
            ThreadContext.clearMap();
            UserContextUtil.localClear();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
