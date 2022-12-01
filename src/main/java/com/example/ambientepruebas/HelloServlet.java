package com.example.ambientepruebas;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  
  protected void dispatchPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    RequestDispatcher view = request.getRequestDispatcher(page);
    view.forward(request, response);
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    System.out.println("Servlet");
    Map<String, UsuarioDTO> usuarios = new Fetch().Set("/usuarios/findAll").Get().getContentMap();
//    Map<String, UsuarioDTO> usuarios = new HashMap<>();
//    for (Map.Entry<String, Object> entry : objects.entrySet()) {
//      usuarios.put(entry.getKey(), (UsuarioDTO) entry.getValue());
//    }
//
  
    usuarios.forEach((k, v) -> System.out.println(k + " " + v));
    
    request.setAttribute("usuarios", usuarios);
    dispatchPage("/index.jsp", request, response);
  }
  
  public void destroy() {
  }
}