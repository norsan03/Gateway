/*package es.uc3m.tiw.controladores;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;
import es.uc3m.tiw.dominios.Admin;
import es.uc3m.tiw.dominios.Mensaje;
import es.uc3m.tiw.dominios.Producto;

@SessionAttributes({"uLogueado"})
@Controller
public class ControladorChat {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/enviarMensaje", method = RequestMethod.POST)
	public String guardarUsuario(Model modelo, @ModelAttribute Mensaje mensaje){
		restTemplate.postForObject("http://localhost:8030/guardarMensaje", mensaje, Mensaje.class);
		return "perfil";
	}
	
	@RequestMapping(value = "/mensajes", method = RequestMethod.GET)
	public String listaMensajesGet(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		String email= (String)usuario.getEmail();
		ResponseEntity<Mensaje[]> response = restTemplate.getForEntity("http://localhost:8030/listarMensajes/{email}",Mensaje[].class, email);
		Mensaje[] misMensajes = response.getBody();
		modelo.addAttribute("mensaje", misMensajes);
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
*/