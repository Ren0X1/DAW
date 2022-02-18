/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.proyectosubidaarchivos;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jaxft
 */
public class datosCarpeta extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        File f = new File("C:/subidaArchivos");
        if(!f.exists()){
            String no="no";
        }
        ArrayList archivos=buscaArchivos(f);
        request.setAttribute("listaArch", archivos);
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

    public ArrayList buscaArchivos(File f) {//método recursivo
        ArrayList<String> total=new ArrayList<>();//creo ArrayList porque no sé cuántos archivos recogeré
        for (File file : f.listFiles()) {//recorro los archivos dentro de la carpeta
            String ficherito=file.getName();//cojo el nombre de los archivos
            if (!file.isDirectory()) {
                if(!ficherito.substring(ficherito.indexOf(".")+1).equals("tmp")){ //aparecian archivos de la carpeta ocultos y salían más archivos de los debidos
                    total.add(ficherito);
                }
            } else {
                buscaArchivos(file);
            }
        }
        return total;
    }
}
