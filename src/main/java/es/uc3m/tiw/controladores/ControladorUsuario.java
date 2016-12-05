package es.uc3m.tiw.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Usuario;

@Controller
public class ControladorUsuario {

		@Autowired
		RestTemplate restTemplate;
		
		
		@RequestMapping(value="/RegistrarUsuario")
		public String registrarUsuario (Model modelo){
			Usuario usuario=new Usuario();
			modelo.addAttribute(usuario);
			return "registroUsuario";
		}
		@PostMapping("/registrar")
		public String guardarUnUsuario(Model modelo, @ModelAttribute Usuario usuario){
			Usuario usuarioGuardado = restTemplate.postForObject("http://localhost:8010/registroUsuario", usuario, Usuario.class);
			modelo.addAttribute(usuarioGuardado);
			return "index";
			
		}
		@PostMapping("/loguin")
		public String validarUsuario(Model modelo, @ModelAttribute Usuario usuario){
			Usuario usuarioValidado = restTemplate.postForObject("http://localhost:8010/login", usuario, Usuario.class);
			modelo.addAttribute("usuarioValidado",usuarioValidado);
			modelo.addAttribute("logueado", true);
			return "Perfil";
			
		}
		@RequestMapping(value="/perfilUsuario",method=RequestMethod.GET)
		public String verUsuario(Model modelo, @ModelAttribute Usuario usuario){
			return "Perfil";
			
		}
		
}
