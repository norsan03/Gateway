package es.uc3m.tiw.controladores;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes({"uLogueado"})
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
	
			//Código para sacar el catalogo de producto al iniciar sesion a lo bruto
			
			ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);

			Producto[] productos = response.getBody();
			modelo.addAttribute("productos", productos);

			/*ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
			Producto[] catalogo = response.getBody();
			modelo.addAttribute("catalogo", catalogo);*/
			return "home";
		}
		
		@RequestMapping(value = "/registroUsuario", method = RequestMethod.GET)
		public String registrarUsuarioGET (Model modelo, @ModelAttribute Usuario usuario){
			return "registroUsuario";
		}
		
		@RequestMapping(value="/VerUsuarioParaModificar", method=RequestMethod.GET)
		public String VerUsuarioParaModificar(Model modelo,@SessionAttribute(value="uLogueado") Usuario usuario){
			
			
			int id = (int) usuario.getId();
			
			Usuario usuarioE = restTemplate.postForObject("http://localhost:8010/verPerfilUsuario", usuario, Usuario.class);
			usuarioE.setId(id);
			System.out.println(usuarioE.getId());
			modelo.addAttribute("uLogueado", usuarioE);
			restTemplate.postForObject("http://localhost:8010/eliminarUsuario/{id}", usuario, Usuario.class, id);			
			

			return "ModificarPerfil";
		}
		
		
		@RequestMapping(value="/ModificarUsuario", method=RequestMethod.POST)
		public String modificarPerfil(Model modelo,@ModelAttribute Usuario usuario){
			
			
			int id = (int) usuario.getId();
			usuario.setId(id);
			Usuario usuarioE = restTemplate.postForObject("http://localhost:8010/ModificarUsuario", usuario, Usuario.class);
			modelo.addAttribute("uLogueado", usuarioE);
			return "ModificarPerfil";
		}
	
		
		@RequestMapping(value = "/registroUsuario", method = RequestMethod.POST)
		public String guardarUsuario(Model modelo, @ModelAttribute Usuario usuario){
			Usuario uLogueado = restTemplate.postForObject("http://localhost:8010/registroUsuario", usuario, Usuario.class);
			modelo.addAttribute("uLogueado", uLogueado);
			ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
			Producto[] productos = response.getBody();
			modelo.addAttribute("productos", productos);
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
		
		@RequestMapping(value="/borrarUsuario", method=RequestMethod.GET)
		public String borrarUsuario(Model modelo,@RequestParam(name="id") int id, @SessionAttribute(value="uLogueado") Usuario usuario ){
			restTemplate.delete("http://localhost:8010/eliminarUsuario/{id}",id);			
			return "redirect:/";
		}
		
	}