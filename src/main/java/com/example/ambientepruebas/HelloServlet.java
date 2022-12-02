package com.example.ambientepruebas;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    //Map<String, UsuarioDTO> usuarios = new Fetch().Set("/usuarios/findAll").Get().getContentMap();
    //String usuario = new Fetch().Set("/usuarios/findByNickname?nickname=lachiqui").Get().getBody();

    //System.out.println("el usuario es:"+usuario);
    //System.out.println("los usuarios son "+usuarios);
//    Map<String, UsuarioDTO> usuarios = new HashMap<>();
//    for (Map.Entry<String, Object> entry : objects.entrySet()) {
//      usuarios.put(entry.getKey(), (UsuarioDTO) entry.getValue());
//    }
//
     HttpClient httpClient = HttpClient.newBuilder()
             .version(HttpClient.Version.HTTP_2).build();
    final HttpRequest requestPosts = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:8081/api/usuarios/findByNickname?nickname=lachiqui"))
            .build();

    try {
       final HttpResponse <String> responsee = httpClient.send(requestPosts,HttpResponse.BodyHandlers.ofString());
     // List<UsuarioDTO> posts
      System.out.println(responsee.body());
      UsuarioDTO mirta = new Gson().fromJson(responsee.body(), UsuarioDTO.class);
      System.out.println(mirta.getNickname());


      request.setAttribute("usuario",responsee.body());
      request.setAttribute("usuario2",mirta);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }




    HttpClient httpClient2 = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2).build();
    final HttpRequest requestPosts2 = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:8081/api/usuarios/findAll"))
            .build();

    try {
      final HttpResponse <String> responsee2 = httpClient2.send(requestPosts2,HttpResponse.BodyHandlers.ofString());
      // List<UsuarioDTO> posts
      System.out.println(responsee2.body());

      //ObjectMapper mapper = new ObjectMapper();
      //Map<String, UsuarioDTO> map = mapper.readValue(responsee2.body(), new TypeReference<Map<String, UsuarioDTO>>(){});

     Gson gson = new Gson();
      Map<String, UsuarioDTO> map = gson.fromJson(responsee2.body(), new TypeToken<Map<String, UsuarioDTO>>(){}.getType());
      System.out.println("el map es"+map);

      System.out.println("el usuario obtenido"+map.get("lachiqui"));

      //String temp = (String) usuariodto.get("lachiqui");
      UsuarioDTO lachiqui2 = map.get("lachiqui");
      System.out.println("prueba:"+lachiqui2.getNickname());

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }







    //usuarios.forEach((k, v) -> System.out.println(k + " " + v));
    

    dispatchPage("/index.jsp", request, response);
  }
  
  public void destroy() {
  }
}