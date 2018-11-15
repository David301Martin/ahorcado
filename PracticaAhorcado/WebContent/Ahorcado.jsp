<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String palabraAleatoria=(String)request.getAttribute("palabraAleatoria");
 int vidasRestantes=(int)request.getAttribute("vidasRestantes");
 String letrasUsadas=(String)request.getAttribute("letrasUsadas");
 String [] celdas=(String[])request.getAttribute("celdas");
 String ultimaLetra=(String)request.getAttribute("ultimaLetra");
 int intentosUsados=(int)request.getAttribute("intentosUsados"); 
 String error=(String)request.getAttribute("error");
 String FinDelJuego=(String)request.getAttribute("FinDelJuego");
 %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Juego del ahorcado</title>
</head>
<body>
<form action="Ahorcado" method="post">
	<p>Vidas restantes: <%=vidasRestantes %></p>
	<p>Letras usadas: <%=letrasUsadas %></p>
	<p>Número de intentos: <%=intentosUsados %></p>
	<p>La ultima letra seleccionada: <%=ultimaLetra %></p>
	
<%if(FinDelJuego==null){ %>	
	<% for(int i=0;i<celdas.length;i++){%>
	<%=celdas[i]%>
	<%}%>
	
	<input type="text" name="letra" maxlength="1" size="1">
	<input type="submit" value="enviar">
	
	<%if(error!=null){ %>
	<%=error %>
	<%} %>
<%}else{ %>
	<h1><%=FinDelJuego %></h1>
	<a href="http://localhost:8080/PracticaAhorcado/Ahorcado?empezar=nuevo">Jugar de nuevo</a>
<%} %>
</form>
</body>
</html>