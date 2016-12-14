package es.uc3m.tiw.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;

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
		
		@RequestMapping(value="/home", method = RequestMethod.GET)
		public String home(Model modelo){
			return "home";
		}
		
		@RequestMapping(value = "/index", method = RequestMethod.POST)
		public String loginUsuario(Model modelo, @ModelAttribute Usuario usuario){
			Usuario uValidado = restTemplate.postForObject("http://localhost:8010/login", usuario, Usuario.class);
			modelo.addAttribute("uValidado",uValidado);
			return "home";
		}
		
		@RequestMapping(value = "/registroUsuario", method = RequestMethod.GET)
		public String registrarUsuarioGET (Model modelo, @ModelAttribute Usuario usuario){
			return "registroUsuario";
		}
		

		@RequestMapping(value = "/registroUsuario", method = RequestMethod.POST)
		public String guardarUsuario(Model modelo, @ModelAttribute Usuario usuario){
			
			Usuario uregistrado = restTemplate.postForObject("http://localhost:8010/registroUsuario", usuario, Usuario.class);
			modelo.addAttribute("uregistrado", uregistrado);
			return "home";
			
		
		}
			
		//}
		
		
		@RequestMapping(value="/Perfil")
		public String verUsuario(Model modelo, @ModelAttribute Usuario usuario){
			return "Perfil";
			
		}
		

		
	}