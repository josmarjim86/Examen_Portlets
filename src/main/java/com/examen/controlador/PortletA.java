package com.examen.controlador;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import com.examen.entidades.Persona;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Portlet implementation class ProtletA
 */
public class PortletA extends GenericPortlet {

    public static final String DIRECCION = "direccion";

	public static final String TELEFONO = "telefono";

	public static final String NOMBRE = "nombre";

	public static final String ENVIAR_EVENTO_B = "enviarEventoB";

	public static final String ENVIAR_EVENTO_C = "enviarEventoC";

	public void init() {
        viewTemplate = getInitParameter("view-template");
    }

    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        include(viewTemplate, renderRequest, renderResponse);
    }

    protected void include(
            String path, RenderRequest renderRequest,
            RenderResponse renderResponse)
        throws IOException, PortletException {

        PortletRequestDispatcher portletRequestDispatcher =
            getPortletContext().getRequestDispatcher(path);

        if (portletRequestDispatcher == null) {
            _log.error(path + " is not a valid include");
        }
        else {
            portletRequestDispatcher.include(renderRequest, renderResponse);
        }
    }
 
    @ProcessAction(name=ENVIAR_EVENTO_B)
    public void enviarEventoB(ActionRequest req, ActionResponse res) throws PortletException, IOException {
    	
    	String nombreUsuario = req.getParameter(NOMBRE);
    	String telefonoUsuario = req.getParameter(TELEFONO);
    	String direccionUsuario = req.getParameter(DIRECCION);
    	
    	Persona persona = new Persona(nombreUsuario,Integer.parseInt(telefonoUsuario), direccionUsuario);
    	
    	QName qname = new QName("http://portales.eventos", "datos_a_B", "x");
    	res.setEvent(qname, persona);
    	req.setAttribute("datos", persona);
    	
    }
    
    @ProcessAction(name=ENVIAR_EVENTO_C)
    public void enviarEventoC(ActionRequest req, ActionResponse res) throws PortletException, IOException {
    	
    	String nombreUsuario = req.getParameter(NOMBRE);
    	String telefonoUsuario = req.getParameter(TELEFONO);
    	String direccionUsuario = req.getParameter(DIRECCION);
    	
    	Persona persona = new Persona(nombreUsuario,Integer.parseInt(telefonoUsuario), direccionUsuario);
    	
    	QName qname = new QName("http://portales.eventos", "datos_a_C", "x");
    	res.setEvent(qname, persona);
    	req.setAttribute("datos", persona);
    	
    }
    
    @ProcessEvent(qname = "{http://portales.eventos}datos_a_B")
	public void procesarMiEventoB(EventRequest req, EventResponse res) throws PortletException, IOException {
		
    	Event evento = req.getEvent();

		Serializable persona = evento.getValue();

		req.setAttribute("datos", persona);

	}
    
    @ProcessEvent(qname = "{http://portales.eventos}datos_a_C")
   	public void procesarMiEventoC(EventRequest req, EventResponse res) throws PortletException, IOException {
   		
       	Event evento = req.getEvent();

   		Serializable persona = evento.getValue();

   		req.setAttribute("datos", persona);

   	}
    
    protected String viewTemplate;

    private static Log _log = LogFactoryUtil.getLog(PortletA.class);

}
