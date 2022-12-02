<%@ page import="java.util.Map" %>
<%@ page import="com.example.ambientepruebas.UsuarioDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	System.out.println("Web");
	//Map<String, UsuarioDTO> usuarios = request.getAttribute("usuarios") != null ? (Map<String, UsuarioDTO>) request.getAttribute("usuarios") : new HashMap<>();
	String usuario = (String) request.getAttribute("usuario");
	UsuarioDTO usuario2 = (UsuarioDTO) request.getAttribute("usuario2");
	UsuarioDTO user =  new Gson().fromJson(usuario, UsuarioDTO.class);

	System.out.println(usuario);
	if(usuario2!=null)
	System.out.println("el usuario 2 es:"+usuario2.getNickname());
%>
<!DOCTYPE html>
<html>
<head>
	<title>JSP - Hello World</title>
</head>
<body>
	<h1><%= "Hello World!" %> </h1>
	<br/>
	<h2><%= usuario %></h2>
	<h3>User</h3>
	<%
		if(user != null){
	%>
		<h2><%= user.getNickname()%></h2>
		<h2><%= user.getNombre()%></h2>
	<%
		}
	%>
	
	<a href="hello-servlet">Hello Servlet</a>
</body>
</html>