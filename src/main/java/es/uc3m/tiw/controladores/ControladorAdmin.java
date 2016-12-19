package es.uc3m.tiw.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Admin;
import es.uc3m.tiw.dominios.Mensaje;
import es.uc3m.tiw.dominios.Producto;
import es.uc3m.tiw.dominios.Usuario;


@SessionAttributes({"aLogueado"})
@Controller
public class ControladorAdmin {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/ADMlogin", method = RequestMethod.GET)
	public String loginAdmin(Model modelo, @ModelAttribute Admin administrador){
		return "ADMlogin";
	}
	
	
	@RequestMapping(value = "/ADMlogin", method = RequestMethod.POST)
	public String loginAdminPOST(Model modelo, @ModelAttribute Admin administrador){
		Admin aLogueado = restTemplate.postForObject("http://localhost:8010/loginAdministrador", administrador, Admin.class);
		modelo.addAttribute("aLogueado",aLogueado);
		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
		Producto[] productos = response.getBody();
		modelo.addAttribute("productos", productos);
		return "ADMproductos";
	}
	
	@RequestMapping(value = "/ADMproductoEDIT")
	public String edProductoAdmin(Model modelo, @ModelAttribute Producto producto){
		return "ADMproductoEDIT";
	}
	
	@RequestMapping(value = "/ADMproductoEDIT", method = RequestMethod.POST)
	public String edProductoAdminPOST(Model modelo, @ModelAttribute Producto producto){
		Producto productoEditado = restTemplate.postForObject("http://localhost:8010/editPadmin", producto, Producto.class);
		modelo.addAttribute(productoEditado);
		return "ADMproductoESP";
	}
	
	
	@RequestMapping(value = "/ADMusuarioEDIT")
	public String edUsuarioAdmin(Model modelo, @ModelAttribute Usuario usuario){
		return "ADMusuarioEDIT";
	}
	
	@RequestMapping(value = "/ADMusuarioEDIT", method = RequestMethod.POST)
	public String edUsuarioAdminPOST(Model modelo, @ModelAttribute Usuario usuario){
		Usuario usuarioEditado = restTemplate.postForObject("http://localhost:8010/editUadmin", usuario, Usuario.class);
		modelo.addAttribute(usuarioEditado);
		return "ADMusuarioESP";
	}
	
	
	@RequestMapping(value="/ADMverProductoEspecifico/{id}", method=RequestMethod.GET)
	public String ADMverProductoEspecifico(Model modelo, @PathVariable int id, @ModelAttribute Producto producto,@SessionAttribute(value="aLogueado") Admin admin){	
		Producto productoEspecifico = restTemplate.postForObject("http://localhost:8020/obtenerProducto/{id}", producto, Producto.class, id);
		modelo.addAttribute("productoEspecifico", productoEspecifico);
		return "ADMproductoESP";
	}
	
	@RequestMapping(value="/ADMverUsuarioEspecifico/{id}", method=RequestMethod.GET)
	public String ADMverUsuarioEspecifico(Model modelo, @PathVariable int id, @ModelAttribute Usuario usuario,@SessionAttribute(value="aLogueado") Admin admin){	
		Usuario usuarioEspecifico = restTemplate.postForObject("http://localhost:8020/obtenerUsuario/{id}", usuario, Usuario.class, id);
		modelo.addAttribute("usuarioEspecifico", usuarioEspecifico);
		return "ADMusuarioESP";
	}
	
	 @RequestMapping(value="/cerrarSesionA", method=RequestMethod.GET)
	 public String cerrarSesionA(SessionStatus status) {
	    status.setComplete();
	    return "/ADMlogin";
	}
	 
		@RequestMapping(value = "/busquedaSimpleA", method = RequestMethod.POST)
		public String catalogoGetA(Model modelo, @RequestParam(name="busquedaIntroducida") String texto){
			Producto[] productos = restTemplate.postForObject("http://localhost:8020/busquedaSimple", texto, Producto[].class);
			modelo.addAttribute("productos",productos);
			return "ADMproductos";
		}
	
	@RequestMapping(value="/verProductoEspecificoA", method=RequestMethod.GET)
	public String verProductoEspecificoA(Model modelo, @RequestParam(name="id") int id, @ModelAttribute Producto producto,@SessionAttribute(value="aLogueado") Admin admin){
			Producto productoE = restTemplate.postForObject("http://localhost:8020/obtenerProducto/{id}", producto, Producto.class, id);
			modelo.addAttribute("producto", productoE);
			return "ADMproductoESP";
		}
		
	@RequestMapping(value="/verUsuarioEspecificoA", method=RequestMethod.GET)
	public String verUsuarioEspecificoA(Model modelo, @RequestParam(name="id") int id, @ModelAttribute Usuario usuario, @SessionAttribute(value="aLogueado") Admin admin){
			Usuario usuarioE = restTemplate.getForObject("http://localhost:8010/obtenerUsuario/{id}", Usuario.class, id);
			modelo.addAttribute("usuario", usuarioE);
			return "ADMusuarioESP";
		}
	
	@RequestMapping(value="/ADMproductos")
	public String verCatalogo(Model modelo, @SessionAttribute(value="aLogueado") Admin admin){
			ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
			Producto[] productos = response.getBody();
			modelo.addAttribute("productos", productos);
			return "ADMproductos";
		}
	
	@RequestMapping(value="/ADMusuarios")
	public String verUsuarios(Model modelo, @SessionAttribute(value="aLogueado") Admin admin){
			ResponseEntity<Usuario[]> response = restTemplate.getForEntity("http://localhost:8010/obtenerUsuarios",Usuario[].class);
			Usuario[] usuarios = response.getBody();
			modelo.addAttribute("usuarios", usuarios);
			return "ADMusuarios";
		}
	
	@RequestMapping(value="/borrarProductoA", method=RequestMethod.GET)
	public String borrarProducto(Model modelo,@RequestParam(name="id") int id,@ModelAttribute Producto producto, @SessionAttribute(value="aLogueado") Admin admin ){
		
		restTemplate.postForObject("http://localhost:8020/eliminarProducto/{id}", producto, Producto.class, id);
		
		// No lo queremos para nada el borrado pero por si acaso luego le veo utilidad
		//modelo.addAttribute("borrado",borrado);
		

		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
		Producto[] productos = response.getBody();
		modelo.addAttribute("productos", productos);
		return "ADMproductos";
	}
	
	@RequestMapping(value="/borrarUsuarioA", method=RequestMethod.GET)
	public String borrarUsuarioA(Model modelo,@RequestParam(name="id") int id,@ModelAttribute Usuario usuario, @SessionAttribute(value="aLogueado") Admin admin ){
		
		restTemplate.delete("http://localhost:8020/eliminarUsuario/{id}", usuario, Usuario.class, id);
		
		// No lo queremos para nada el borrado pero por si acaso luego le veo utilidad
		//modelo.addAttribute("borrado",borrado);
		

		ResponseEntity<Usuario[]> response = restTemplate.getForEntity("http://localhost:8010/obtenerUsuarios",Usuario[].class);
		Usuario[] usuarios = response.getBody();
		modelo.addAttribute("usuarios", usuarios);
		return "ADMusuarios";
	}
	
	
	@RequestMapping(value = "/chattA", method = RequestMethod.GET)
	public String chat (Model modelo, @RequestParam(name="IdReceptor") int IdReceptor, /*@RequestParam(name="IdProducto") int IdProducto,*/ @ModelAttribute Mensaje mensaje,@SessionAttribute(value="aLogueado") Admin admin){
		modelo.addAttribute("IdReceptor", IdReceptor);
		//modelo.addAttribute("IdProducto", IdProducto);
		
		return "ADMchat";
		
	}
	
	
		
	@RequestMapping(value = "/RegistroMensajeA", method = RequestMethod.POST)
	public String enviarMensaje(Model modelo, @RequestParam int IdReceptor, @ModelAttribute Mensaje mensaje, @SessionAttribute(value="aLogueado") Admin admin){
		
		String idEmisor = admin.getEmail();
		String idReceptor = Integer.toString(IdReceptor);
		
		restTemplate.postForObject("http://localhost:8030/guardarMensaje/{idEmisor}/{idReceptor}", mensaje, Mensaje.class, idEmisor,idReceptor);
		
		ResponseEntity<Producto[]> response1 = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);
		Producto[] productos = response1.getBody();
		modelo.addAttribute("productos", productos);
		return "ADMproductos";
	}
	
	@RequestMapping(value = "/bandejaEntradaA", method = RequestMethod.GET)
	public String bandejaEntrada(Model modelo, @SessionAttribute(value="aLogueado") Admin admin){
		
		int id = (int)admin.getId();
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
		
		return "ADMbandejaEntrada";
	
	}
	
	
	@RequestMapping(value="/verParaModificarA", method=RequestMethod.GET)
	public String verParaModificar(Model modelo, @RequestParam(name="id") int id, @ModelAttribute Producto producto,@SessionAttribute(value="aLogueado") Admin admin){
		
		Producto productoE = restTemplate.postForObject("http://localhost:8020/obtenerProducto/{id}", producto, Producto.class, id);
		modelo.addAttribute("producto", productoE);
		int idProductoAct = productoE.getId();
		restTemplate.postForObject("http://localhost:8020/eliminarProducto/{idProductoAc}", productoE, Producto.class, idProductoAct);
		return "ADMproductoEDIT";
	}
	
	@RequestMapping(value="/ModificarProductoA", method=RequestMethod.POST)
	public String modificarProducto(Model modelo, @ModelAttribute Producto producto, @SessionAttribute(value="aLogueado") Admin admin){
		
		int id = (int) admin.getId();
		producto.setUsuario(id);
		Producto productoE = restTemplate.postForObject("http://localhost:8020/ModificarProducto", producto, Producto.class);
		modelo.addAttribute("producto", productoE);
		return "ADMproductoEDIT";
	}
	
}
