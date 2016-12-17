package es.uc3m.tiw.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;
import es.uc3m.tiw.dominios.Mensaje;
import es.uc3m.tiw.dominios.Producto;

@SessionAttributes({"uLogueado"})
@Controller
public class ControladorChat {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/chatt", method = RequestMethod.GET)
	public String chat (Model modelo, @RequestParam(name="IdReceptor") int IdReceptor, @ModelAttribute Mensaje mensaje,@SessionAttribute(value="uLogueado") Usuario usuario){
		modelo.addAttribute("IdReceptor", IdReceptor);
		
		
		return "chat";
		
	}
		
	@RequestMapping(value = "/RegistroMensaje", method = RequestMethod.POST)
	public String enviarMensaje(Model modelo, @RequestParam int IdReceptor, @ModelAttribute Mensaje mensaje,@SessionAttribute(value="uLogueado") Usuario usuario){
		
		String idEmisor = Long.toString(usuario.getId());
		String idReceptor = Integer.toString(IdReceptor);
		
		Map<String, String> ids = new HashMap<>();
		ids.put("idPropietario",idEmisor);
		ids.put("idReceptor", idReceptor);
				
		restTemplate.postForObject("http://localhost:8030/guardarMensaje{ids}", mensaje, Mensaje.class,ids);
		
		return "home";
	}
	/*
	@RequestMapping(value = "/bandejaEntrada", method = RequestMethod.GET)
	public String bandejaEntrada(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		String emailReceptor = usuario.getEmail();
		ResponseEntity<Mensaje[]> response = restTemplate.getForEntity("http://localhost:8030/listarMensajes/{email}",Mensaje[].class, emailReceptor);
		Mensaje[] mensajesRecibidos = response.getBody();
		modelo.addAttribute("mensajes", mensajesRecibidos);
	    return "bandejaEntrada";
	}
	*/
	@RequestMapping(value = "/enviarMensaje")
	public String chat(Model modelo, @ModelAttribute Mensaje mensaje){
		return "enviarMensaje";
	}
	
	@RequestMapping(value = "/bandejaEntrada")
	public String chat2(Model modelo){
		return "bandejaEntrada";
	}
}