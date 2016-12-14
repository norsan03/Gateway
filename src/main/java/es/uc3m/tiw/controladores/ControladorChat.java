package es.uc3m.tiw.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;
import es.uc3m.tiw.dominios.Mensaje;

public class ControladorChat {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/enviarMensaje", method = RequestMethod.POST)
	public String guardarUsuario(Model modelo, @ModelAttribute Mensaje mensaje){
		restTemplate.postForObject("http://localhost:8030/guardarMensaje", mensaje, void.class);
		return "perfil";
			}
	/*
	@RequestMapping(value = "/mensajes", method = RequestMethod.GET)
	public List<Mensaje> listaMensajesGet(HttpServletRequest request, Model modelo){
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute(USUARIO_SESSION);
		String emailReceptor = usuarioSesion.getEmail();
	    List<Mensaje> listaMensajes = restTemplate.getForObject("http://localhost:8030/listarMensajes", emailReceptor, Mensaje.class);
	    return listaMensajes;
	} */
}
