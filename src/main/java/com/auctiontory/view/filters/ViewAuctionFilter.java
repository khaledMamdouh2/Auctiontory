package com.auctiontory.view.filters;

import com.auctiontory.view.bean.UserBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter({"/viewBatch.xhtml" , "/viewParts.xhtml"})
public class ViewAuctionFilter implements Filter {
    @Inject
    UserBean userBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(userBean.getUser().getType() == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("home.xhtml");
        }
        else{
            filterChain.doFilter(servletRequest , servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
