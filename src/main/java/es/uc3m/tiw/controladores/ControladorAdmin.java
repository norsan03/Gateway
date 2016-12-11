package es.uc3m.tiw.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.dominios.Admin;

@Controller
public class ControladorAdmin {

	@Autowired
	private RestTemplate restTemplate;
	

	@RequestMapping(value = "/ADMlogin", method = RequestMethod.POST)
	public String loginAdmin(Model modelo, @ModelAttribute Admin administrador){
		Admin adminValidado = restTemplate.postForObject("http://localhost:8010/loginAdmin", administrador, Admin.class);
		modelo.addAttribute(adminValidado);
		modelo.addAttribute("adminlogueado", true);
		return "ADMproductos";
		
	}
	

	
	
}
