<%@page import="com.examen.entidades.Persona"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<h1>Portlet B</h1>
<%
	Persona persona = (Persona)request.getAttribute("datos_a_B");

	String nombre = "";
	String direccion = "";
	String telefono = "";
	


	if (persona != null) {
		
		nombre = persona.getNombre();
		telefono = String.valueOf(persona.getTelefono());
		direccion = persona.getDireccion();
	}
%>

<form method="post">
	<div>
		<input type="text" name="nombre" value="<%=nombre%>" />
	</div>
	<div>
		<input type="text" name="telefono" value="<%=telefono%>" />
	</div>
	<div>
		<input type="text" name="direccion" value="<%=direccion%>" />
	</div>
</form>

