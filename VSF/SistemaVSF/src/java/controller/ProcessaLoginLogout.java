/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import models.dao.ClienteDAO;

/**
 *
 * @author André
 */
@WebServlet(name = "ProcessaLoginLogout", urlPatterns = {"/ProcessaLoginLogout"})
public class ProcessaLoginLogout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ClienteDAO dao = new ClienteDAO();
        Conta conta = new Conta();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
        if ("acessar".equals(action)) {
            String agencia = request.getParameter("agencia").isEmpty() ? "" : request.getParameter("agencia");
            String numConta = request.getParameter("conta").isEmpty() ? "" : request.getParameter("numConta");
            String senha = request.getParameter("senha").isEmpty() ? "" : request.getParameter("senha");
            if (!dao.login(agencia, numConta, senha)) {
                request.setAttribute("msg", "Login e/ou senha incorretos.");
                rd.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("agencia", agencia);
                session.setAttribute("conta", conta);
                session.setAttribute("senha", senha);

                rd = getServletContext().getRequestDispatcher("/portal.jsp");
            }
        }
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        } else {
            request.setAttribute("msg", "Login e senha incorretos.");
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        }
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
