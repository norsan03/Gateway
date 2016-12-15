package es.uc3m.tiw.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes({"uLogueado","logueado"})
@Controller
public class ControladorUsuario {

		@Autowired
		RestTemplate restTemplate;
		
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String login(Model modelo){
			return "index";
		}
	
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String login2(Model modelo){
			return "index";
		}
		
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String loginUsuario(Model modelo, @ModelAttribute Usuario usuario){
			
			Usuario uLogueado = restTemplate.postForObject("http://localhost:8010/validar", usuario, Usuario.class);
			modelo.addAttribute("uLogueado",uLogueado);
			modelo.addAttribute("logueado",true);
			
			//Código para sacar el catalogo de producto al iniciar sesion a lo bruto
			/*ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
			Producto[] catalogo = response.getBody();
			modelo.addAttribute("catalogo", catalogo);*/
			
			return "home";
		}
		
		@RequestMapping(value = "/registroUsuario", method = RequestMethod.GET)
		public String registrarUsuarioGET (Model modelo, @ModelAttribute Usuario usuario){
			return "registroUsuario";
		}
		
		@RequestMapping(value = "/registroUsuario", method = RequestMethod.POST)
		public String guardarUsuario(Model modelo, @ModelAttribute Usuario usuario){
			Usuario uLogueado = restTemplate.postForObject("http://localhost:8010/registroUsuario", usuario, Usuario.class);
			modelo.addAttribute("uLogueado", uLogueado);
			return "home";
		}
		
		 @RequestMapping(value="/cerrarSesion", method=RequestMethod.GET)
		 public String cerrarSesion(SessionStatus status) {
		    status.setComplete();
		    return "/index";
		}
		
		@RequestMapping(value="/Perfil")
		public String verUsuario(){
			return "Perfil";
		}
		
	}