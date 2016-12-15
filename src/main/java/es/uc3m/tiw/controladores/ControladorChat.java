/*package es.uc3m.tiw.controladores;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;
import es.uc3m.tiw.dominios.Admin;
import es.uc3m.tiw.dominios.Mensaje;

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
	public List<Mensaje> listaMensajesGet(HttpServletRequest request, Model modelo, @ModelAttribute Usuario usuario){
		String emailReceptor = usuario.getEmail();
		Map<String, String> parametros = new HashMap<>();
		parametros.put("idReceptor", emailReceptor);
	    List<Mensaje> listaMensajes = restTemplate.getForObject("http://localhost:8030/listarMensajes/{idReceptor}",parametros );
	    return listaMensajes;
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