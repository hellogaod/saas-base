package com.base.saas.manage.filter;

import com.base.saas.userinfo.UserContextUtil;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;

/**
 * 当前类的作用：当前过滤器的doFilter方法的request实例对象生命周期始终存在于当前项目，所以通过该request对象获取X-UserToken
 */
@Component
public class ContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) {
        try {
            UserContextUtil.setHttpServletRequest(WebUtils.toHttp(request));

            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
