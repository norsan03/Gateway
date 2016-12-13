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
	public String darAltaProducto(Model modelo, @ModelAttribute Producto producto){
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

	
}
