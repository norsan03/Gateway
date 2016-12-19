package es.uc3m.tiw.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	public String chat (Model modelo, @RequestParam(name="IdReceptor") int IdReceptor, /*@RequestParam(name="IdProducto") int IdProducto,*/ @ModelAttribute Mensaje mensaje,@SessionAttribute(value="uLogueado") Usuario usuario){
		modelo.addAttribute("IdReceptor", IdReceptor);
		//modelo.addAttribute("IdProducto", IdProducto);
		
		return "chat";
		
	}
	
	
		
	@RequestMapping(value = "/RegistroMensaje", method = RequestMethod.POST)
	public String enviarMensaje(Model modelo, @RequestParam int IdReceptor, @ModelAttribute Mensaje mensaje, @SessionAttribute(value="uLogueado") Usuario usuario){
		
		String idEmisor = usuario.getEmail();
		String idReceptor = Integer.toString(IdReceptor);
		
		restTemplate.postForObject("http://localhost:8030/guardarMensaje/{idEmisor}/{idReceptor}", mensaje, Mensaje.class, idEmisor,idReceptor);
		
		ResponseEntity<Producto[]> response1 = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
		Producto[] productos = response1.getBody();
		modelo.addAttribute("productos", productos);
		
		return "home";
	}
	
	@RequestMapping(value = "/bandejaEntrada", method = RequestMethod.GET)
	public String bandejaEntrada(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		
		int id = (int)usuario.getId();
		ResponseEntity<Mensaje[]> response = restTemplate.getForEntity("http://localhost:8030/bandejaEntrada/{id}",Mensaje[].class, id);
		Mensaje[] mensajesTodos = (Mensaje[])response.getBody();
	
		//List <Mensaje> mensajes = Arrays.asList(mensajesTodos);
		List <Mensaje> mensajesUsuario = new ArrayList<Mensaje>();
		
		String idsesion = Integer.toString(id);
		
		for(int i=0;i<mensajesTodos.length;i++){

			if (idsesion.equals(mensajesTodos[i].getIdReceptor())){
			//	mensajesUsuario.get(i).setIdReceptor();
				
				mensajesUsuario.add(mensajesTodos[i]);
			}
				
		}
		
		modelo.addAttribute("bandejaEntrada", mensajesUsuario);
		return "bandejaEntrada";
	
	}
	
	
}