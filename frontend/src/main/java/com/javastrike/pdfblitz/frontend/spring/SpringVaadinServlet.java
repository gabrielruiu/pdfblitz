package com.javastrike.pdfblitz.frontend.spring;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class SpringVaadinServlet extends AbstractApplicationServlet {

    /** Class serial version unique identifier. */
    private static final long serialVersionUID = 1L;

    private Class<? extends Application> clazz;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(
                config.getServletContext());

        Application application = wac.getBean("application", Application.class);

        clazz = application.getClass();
    }

    /**
     * Gets the application from the Spring context.
     *
     * @return The Spring bean named 'application'
     */
    @Override
    protected Application getNewApplication(HttpServletRequest request)
            throws ServletException {

        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(
                request.getSession().getServletContext());

        return wac.getBean("application", Application.class);
    }

    /**
     * @see com.vaadin.terminal.gwt.server.AbstractApplicationServlet#getApplicationClass()
     */
    @Override
    protected Class<? extends Application> getApplicationClass()
            throws ClassNotFoundException {

        return clazz;
    }
}
