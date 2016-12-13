package es.uc3m.tiw.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.*;

@Controller
public class ControladorProducto {
	
	@Autowired
	RestTemplate restTemplate;
	


	
	@RequestMapping(value="/altaProducto", method=RequestMethod.GET)
	public String darAltaProductoGET(Model modelo, @ModelAttribute Producto producto){
	
		return "altaProducto";
	}
	
	@RequestMapping(value="/altaProducto", method=RequestMethod.POST)
	public String darAltaProductoPOST(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "altaProducto";
	}
	
	@RequestMapping(value="/misProductos", method=RequestMethod.GET)
	public String verProductosUsuario(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "misProductos";
	}
	
	@RequestMapping(value="/producto", method=RequestMethod.GET)
	public String verProductoEspecifico(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "producto";
	}
	
	@RequestMapping(value="/borrarProducto", method=RequestMethod.GET)
	public String borrarProducto(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "misProductos";
	}
	
	@RequestMapping(value="/ModificarProducto", method=RequestMethod.GET)
	public String modificarProductoGET(Model modelo, @ModelAttribute Producto producto){

		return "ModificarProducto";
	}
	
	@RequestMapping(value="/ModificarProducto", method=RequestMethod.POST)
	public String modificarProductoPOST(Model modelo, @ModelAttribute Producto producto){
		/*Producto pregistrado = restTemplate.postForObject("http://localhost:8020/altaProducto", producto, Producto.class);
		modelo.addAttribute(pregistrado);*/
		return "ModificarProducto";
	}

	
}
