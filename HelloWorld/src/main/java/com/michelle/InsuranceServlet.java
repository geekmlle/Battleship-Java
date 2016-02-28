package com.michelle;

/**
 * Created by blondieymollo on 2/21/16.
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Database;

@WebServlet("/InsuranceServlet")
public class InsuranceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Database db = new Database();

        request.setAttribute("code", db.readFile());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/servlets/insurance.jsp");
        rd.forward(request, response);
    }


}
