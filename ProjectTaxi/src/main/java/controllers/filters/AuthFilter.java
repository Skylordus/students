package controllers.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
        chain.doFilter(req, resp);
        logger.error("on filter");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
