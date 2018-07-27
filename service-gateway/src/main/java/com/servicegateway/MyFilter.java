package com.servicegateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fly on 2018/7/26.
 */
public class MyFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURI();
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> list = new ArrayList<>();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            list.add(name);
            System.out.println("name:" + request.getParameter(name));
        }
        if(list.size() <= 0){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("至少需要一个参数");
//            requestContext.getResponse().setCharacterEncoding("utf-8");
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
        }
        return null;
    }
}
