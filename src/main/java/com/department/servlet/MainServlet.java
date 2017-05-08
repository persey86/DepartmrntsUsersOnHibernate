package com.department.servlet;

import com.department.servlet.actions.Action;
import com.department.validation.UniqueUserEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 02.04.2017.
 */
//Autodetect classes with autoscan path like class-controller and
// Return this name from bean if this name is exist
// equals HttpServlet
@Component(value = "MainServlet")
//Simple HttpServlet that delegates to an bean, name "MainServlet" must match like in web.xml
public class MainServlet implements HttpRequestHandler{       //extends HttpServlet {

    @Autowired
//    Main interface for configuration my applet
    private ApplicationContext applicationContext;

    private static final Logger LOGGER = Logger.getLogger(UniqueUserEmail.class);

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getRequestURI();
        Action action;
        try {

            action = applicationContext.getBean(url,Action.class);

            action.execute(request,response);

        } catch (Exception e) {

            response.sendRedirect("error");

            applicationContext.getBean("/error", Action.class);

            LOGGER.error(e.getMessage(), e);

        }
    }
}