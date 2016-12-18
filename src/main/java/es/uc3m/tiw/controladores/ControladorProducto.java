package es.uc3m.tiw.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.*;

@SessionAttributes({"uLogueado"})
@Controller
public class ControladorProducto {
	
	@Autowired
	RestTemplate restTemplate;
	

	
	@RequestMapping(value="/nuevoProducto", method=RequestMethod.GET)
	public String darAltaProductoPOST(Model modelo, @ModelAttribute Producto producto){
		/*Producto pRegistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute("pRegistrado", pRegistrado);*/
		return "altaProducto";
	}
	
	@RequestMapping(value="/misProductos", method=RequestMethod.GET)
	public String verProductosUsuario(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		int id = (int)usuario.getId();
		//Map<String, String> parametros = new HashMap<>();
		//parametros.put("id",new Integer(id).toString());
		
		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerMisProductos/{id}",Producto[].class, id);

		Producto[] misProductos = response.getBody();
		modelo.addAttribute("misProductos", misProductos);
		
		return "misProductos";
	}
	
	@RequestMapping(value="/home")
	public String verCatalogo(Model modelo, @SessionAttribute(value="uLogueado") Usuario usuario){
		
		//Map<String, String> parametros = new HashMap<>();
		//parametros.put("id",new Integer(id).toString());
		
		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerCatalogo",Producto[].class);

		Producto[] productos = response.getBody();
		modelo.addAttribute("productos", productos);
		
		return "home";
	}
	
	
	@RequestMapping(value="/altaProducto", method=RequestMethod.POST)
	public String registrarProducto(Model modelo, @ModelAttribute Producto producto,@SessionAttribute(value="uLogueado") Usuario usuario){
		producto.setUsuario((int) usuario.getId());
		/*Producto pregistrado = */restTemplate.postForObject("http://localhost:8020/guardarProducto", producto, Producto.class);
		/*modelo.addAttribute(pregistrado);*/
		int id = (int)usuario.getId();
		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerMisProductos/{id}",Producto[].class, id);

		Producto[] misProductos = response.getBody();
		modelo.addAttribute("misProductos", misProductos);

		return "misProductos";
	}
	
	@RequestMapping(value="/producto", method=RequestMethod.GET)
	public String verProductoEspecifico(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "producto";
	}
	
	@RequestMapping(value="/borrarProducto", method=RequestMethod.GET)
	public String borrarProducto(Model modelo,@RequestParam(name="id") int id,@ModelAttribute Producto producto, @SessionAttribute(value="uLogueado") Usuario usuario ){
		
		int idUsuario = (int)usuario.getId();
		restTemplate.postForObject("http://localhost:8020/eliminarProducto/{id}", producto, Producto.class, id);
		
		// No lo queremos para nada el borrado pero por si acaso luego le veo utilidad
		//modelo.addAttribute("borrado",borrado);
		

		ResponseEntity<Producto[]> response = restTemplate.getForEntity("http://localhost:8020/obtenerMisProductos/{idUsuario}",Producto[].class, idUsuario);
		Producto[] misProductos = response.getBody();
		modelo.addAttribute("misProductos", misProductos);
		
		return "misProductos";
	}
	
	@RequestMapping(value="/ModificarProducto/{id}", method=RequestMethod.GET)
	public String modificarProductoGET(Model modelo,@PathVariable(name="id") int id){
		System.out.println(id);
		Producto productoId = restTemplate.getForObject("http://localhost:8020/buscarProducto/{id}", Producto.class, id);
		System.out.println(id);
		modelo.addAttribute(productoId);
		return "ModificarProducto";
	}
	
	
	@RequestMapping(value="/verProductoEspecifico", method=RequestMethod.GET)
	public String verProductoEspecifico(Model modelo, @RequestParam(name="id") int id, @ModelAttribute Producto producto,@SessionAttribute(value="uLogueado") Usuario usuario){
		Producto productoE = restTemplate.postForObject("http://localhost:8020/obtenerProducto/{id}", producto, Producto.class, id);
		modelo.addAttribute("producto", productoE);
		return "producto";
	}
		
	@RequestMapping(value="/ModificarProducto", method=RequestMethod.POST)
	public String modificarProductoPOST(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "ModificarProducto";
	}
	
	@RequestMapping(value = "/busquedaSimple", method = RequestMethod.POST)
	public String catalogoGet(Model modelo, @RequestParam(name="busquedaIntroducida") String texto){
		Producto[] productos = restTemplate.postForObject("http://localhost:8020/busquedaSimple", texto, Producto[].class);
		modelo.addAttribute("productos",productos);
		return "home";
	}

	
}
