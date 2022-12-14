package com.base.saas.gateway.filter;

import com.base.saas.AppConstant;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * 校验管理端后台用户登录是否失效：token失效状态码401
 */
public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    @Value("${sessionTimeout}")
    private String sessionTimeout;

    //filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
    //pre：可以在请求被路由之前调用
    //route：在路由请求时候被调用
    //post：在route或error过滤器之后被调用
    //error：处理请求时发生错误时被调用
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //filterOrder：通过int值来定义过滤器的执行顺序.
    // 优先级为0，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否执行该过滤器，此处为true，说明需要过滤
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体逻辑。需要注意:
    // 这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由
    // 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码
    @SneakyThrows
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        //1.如果不是管理端后台api，不需要下面的验证
        String serviceId = String.valueOf(ctx.get("serviceId"));
        if (!serviceId.equals(AppConstant.MANAGE_NAME)) {
            return null;
        }

        //2. 管理端后台的登录api不需要走下面的校验
        if (StringUtils.contains(request.getRequestURL().toString(), AppConstant.MANAGE_LOGIN_API_CONTAIN)
                || StringUtils.contains(request.getRequestURL().toString(), "swagger")
                || StringUtils.contains(request.getRequestURL().toString(), "api-docs")
                || StringUtils.containsIgnoreCase(request.getRequestURL().toString(), "HashCode")
        ) {
            return null;
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();

        System.out.println(request.getRequestURI() + ": " + UserContextUtil.getUserTokenId());

        if (userInfo != null) {
            if (userInfo.getUserType() == 1 && !StringUtils.isEmpty(userInfo.getAccount())) {
                //系统管理员
                RedisUtil.expire(RedisKeyConstants.LOGIN_PREFIX + userInfo.getAccount(), Integer.parseInt(sessionTimeout));

            } else if (userInfo.getUserType() == 2 && !"".equalsIgnoreCase(userInfo.getCompanyCode())) {
                //企业用户
                RedisUtil.expire(RedisKeyConstants.LOGIN_PREFIX + userInfo.getCompanyCode() + "-" + userInfo.getAccount(), Integer.parseInt(sessionTimeout));
            }


        } else {
            log.debug("Invalid user login information");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            response.addHeader("saas-error-message", URLEncoder.encode("token失效，请重新登录", "UTF-8"));
            return null;
        }
        return null;
    }
}
