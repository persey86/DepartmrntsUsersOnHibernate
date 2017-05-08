package com.department.servlet.actions;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 05.05.17.
 */
//Autodetect classes with autoscan path like class-controller and
// Return this name from bean if this name is exist,
// equals HttpServlet
@Component(value = "/error")
public class ErrorAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request,response);
    }
}