package es.uc3m.tiw.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Admin;
import es.uc3m.tiw.dominios.Producto;

@Controller
public class ControladorAdmin {

	@Autowired
	RestTemplate restTemplate;
	

	@RequestMapping(value = "/ADMlogin")
	public String loginAdmin(Model modelo, @ModelAttribute Admin administrador){
		return "ADMlogin";
	}
	
	@RequestMapping(value = "/ADMlogin", method = RequestMethod.POST)
	public String loginAdminPOST(Model modelo, @ModelAttribute Admin administrador){
		Admin adminValidado = restTemplate.postForObject("http://localhost:8010/loginAdmin", administrador, Admin.class);
		modelo.addAttribute(adminValidado);
		modelo.addAttribute("adminlogueado", true);
		return "ADMproductos";
	}
	
	@RequestMapping(value = "/ADMproductoEDIT")
	public String edProductoAdmin(Model modelo, @ModelAttribute Producto producto){
		return "ADMproductoEDIT";
	}
	
	@RequestMapping(value = "/ADMproductoEDIT", method = RequestMethod.POST)
	public String edProductoAdminPOST(Model modelo, @ModelAttribute Producto producto){
		Producto productoEditado = restTemplate.postForObject("http://localhost:8010/loginAdmin", producto, Producto.class);
		modelo.addAttribute(productoEditado);
		return "ADMproductoESP";
	}

	@RequestMapping(value = "/ADMproductoESP")
	public String ProductoEspAdmin(Model modelo, @ModelAttribute Producto producto){
		return "ADMproductoESP";
	}
	
	@RequestMapping(value = "/ADMproductos")
	public String Productos(Model modelo, @ModelAttribute Producto producto){
		return "ADMproductos";
	}
	
}
