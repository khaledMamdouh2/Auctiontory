package com.auctiontory.view.filters;

import com.auctiontory.model.entity.UserType;
import com.auctiontory.view.bean.UserBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/addAuction.xhtml"})
public class AddAuctionFilter implements Filter{
    @Inject
    UserBean userBean;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserType userType = userBean.getUser().getType();
        if(userType == UserType.ADMIN || userType == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("home.xhtml");
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
