<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.examen.entidades.Persona"%>
<portlet:defineObjects />

<portlet:actionURL name="enviarEventoB" var="urlEnviarEventoB"/>
<portlet:actionURL name="enviarEventoC" var="urlEnviarEventoC"/>
	
	<h1>Portlet A</h1>
	
	<%
	Persona persona = (Persona)renderRequest.getAttribute("datos");

	String nombre = "";
	String direccion = "";
	String telefono = "";
	


	if (persona != null) {
		
		nombre = persona.getNombre();
		telefono = String.valueOf(persona.getTelefono());
		direccion = persona.getDireccion();
	}
%>
	
	
	
	
<form  method = "post">
	<div><input type = "text" name= "nombre" value="<%=nombre %>"/></div>
	<div><input type = "text" name= "telefono" value="<%=telefono %>"/></div>
	<div><input type = "text" name= "direccion" value="<%=direccion %>"/></div>
	<input type = "submit" formaction="<%=urlEnviarEventoB %>" value = "Envía a B"/>
	<input type = "submit" formaction="<%=urlEnviarEventoC%>" value = "Envía a C">
</form>
