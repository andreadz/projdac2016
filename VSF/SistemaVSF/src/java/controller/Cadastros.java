/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import models.dao.*;
import models.Cliente;

/**
 *
 * @author Andre
 */
@WebServlet(name = "Cadastros", urlPatterns = {"/Cadastros"})
public class Cadastros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd = request.getRequestDispatcher("");
        String action = request.getParameter("action");
        ClienteDAO daoCli = new ClienteDAO();
        ContaDAO daoConta = new ContaDAO();
        if (session.getAttribute("usuario") == null) {
            request.setAttribute("msg", "Usuário e não senha informados.");
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        }

        if ("cadcliente".equals(action)) {
            String nome = request.getParameter("nome");
            String rg = request.getParameter("rg");
            String endereco = request.getParameter("endereco");
            String numero = request.getParameter("numero");
            String complemento = request.getParameter("complemento");
            String cep = request.getParameter("cep");
            String telefone = request.getParameter("telefone");
            String senha = request.getParameter("senha");
            double renda = Double.parseDouble(request.getParameter("renda"));
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf").isEmpty() ? "" : request.getParameter("cpf");
            String cnpj = request.getParameter("cnpj").isEmpty() ? "" : request.getParameter("cnpj");

            Cliente cliente = new Cliente(0, cpf, cnpj, nome, rg, endereco, cep, telefone, email, senha, renda);
            cliente = daoCli.cadastrarCliente(cliente);
            session.setAttribute("cliente", cliente);

            request.setAttribute("msg", "Cliente cadastrado com sucesso.");
            rd = getServletContext().getRequestDispatcher("/cadastroconta.jsp");
        }
        if ("cadconta".equals(action)) {
            String agencia = request.getParameter("agencia");
            Cliente cliente = (Cliente) session.getAttribute("usuario");
            
            double limite = verificaLimite(cliente.getRenda());
            Conta conta = daoConta.criarConta(agencia, cliente, limite );
        }
    }

    public double verificaLimite(double renda) {
        double limite = 0.0;
        if (renda <= 1000.0) {
            limite = 0;
        } else if (renda <= 1500.00) {
            limite = 0;
        } else if (renda <= 2000.00) {
            limite = 0;
        } else if (renda <= 2500.00) {
            limite = 0;
        }
        return limite;
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
