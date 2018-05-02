/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Flori
 */
public class setAvailabilityProcessing extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/plain");
            Enumeration<String> parameterNames = request.getParameterNames();
            out.println("par");
            out.println("-----------------------");
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                out.write(paramName);
                out.write("n");
                String[] paramValues = request.getParameterValues(paramName);
                for (String paramValue : paramValues) {
                    out.write("t" + paramValue);
                    out.write("n");
                }
            }
            out.println("atr");
            out.println("-----------------------");
            Enumeration<?> e = getServletContext().getAttributeNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                // Get the value of the attribute
                Object value = getServletContext().getAttribute(name);
                if (value instanceof Map) {
                    ((Map<?, ?>) value).entrySet().stream().forEach((entry) -> {
                        out.println(entry.getKey() + "=" + entry.getValue());
                    });
                } else if (value instanceof List) {
                    for (Object element : (List) value) {
                        out.println(element);
                    }
                }
            }
            out.println("-----------------------");
        }
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
