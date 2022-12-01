<%@ page import="java.util.Map" %>
<%@ page import="com.example.ambientepruebas.UsuarioDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	System.out.println("Web");
	Map<String, UsuarioDTO> usuarios = request.getAttribute("usuarios") != null ? (Map<String, UsuarioDTO>) request.getAttribute("usuarios") : new HashMap<>();
	

%>
<!DOCTYPE html>
<html>
<head>
	<title>JSP - Hello World</title>
</head>
<body>
	<h1><%= "Hello World!" %> </h1>
	<br/>
	
	<% for (Map.Entry<String, UsuarioDTO> entry : usuarios.entrySet()) { %>
		<h1><%= entry.getValue().getNombre() %></h1>
		<h1><%= entry.getValue().getApellido() %></h1>
		<h1><%= entry.getValue().getNickname() %></h1>
	<% } %>
	
	<a href="hello-servlet">Hello Servlet</a>
</body>
</html>