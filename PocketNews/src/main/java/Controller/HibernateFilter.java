package Controller;


import Model.database.HibernateSessionFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.*;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-30
 * Time: 上午8:38
 * To change this template use File | Settings | File Templates.
 */
public class HibernateFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
        filterConfig.getInitParameter("txt");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);


        Session session = HibernateSessionFactory.getSession();

        HibernateSessionFactory.closeSession();

    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
