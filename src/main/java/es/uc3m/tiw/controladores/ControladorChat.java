package es.uc3m.tiw.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;
import es.uc3m.tiw.dominios.Mensaje;

@SessionAttributes({"uLogueado"})
@Controller
public class ControladorChat {
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(value="/enviarMensaje/{EmailUsuario}")
	public String enviarMensaje(@PathVariable String email, @SessionAttribute(value="uLogueado")Usuario usuario, @ModelAttribute Mensaje mensaje){
		mensaje.setIdEmisor(usuario.getEmail());
		mensaje.setIdReceptor(email);
		restTemplate.postForObject("http://localhost:8030/guardarMensaje", mensaje, Mensaje.class);
		return "perfil";
	}
	
	@RequestMapping(value = "/listaMensajes", method = RequestMethod.GET)
	public String listaMensajesGet(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		String email = usuario.getEmail();
		ResponseEntity<Mensaje[]> response = restTemplate.getForEntity("http://localhost:8030/listarMensajes/{email}",Mensaje[].class, email);
		Mensaje[] allMensajes = response.getBody();
		modelo.addAttribute("mensaje", allMensajes);
	    return "listadoMensajes";
	}

	@RequestMapping(value = "/chat")
	public String chat(Model modelo, @ModelAttribute Mensaje mensaje){
		return "chat";
	}
	
	@RequestMapping(value = "/listadoMensajes")
	public String chat2(Model modelo){
		return "listadoMensajes";
	}
}
