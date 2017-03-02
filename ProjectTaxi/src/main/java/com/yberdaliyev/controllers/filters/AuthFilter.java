package com.yberdaliyev.controllers.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Yerlan on 28.02.2017.
 */
//@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    private static Logger logger = Logger.getLogger(AuthFilter.class);
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        logger.trace("on auth filter");
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
