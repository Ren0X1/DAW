package com.daw.primerSpringClase.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class primerSpringControlador {

	@GetMapping("/{nombre}")
	public String entrada2() {
		return "index2";
	}
	
	@GetMapping("/entrada")
	public String entrada(@RequestParam(name = "nombre", required = false, defaultValue = "Pepe") String nom, Model modelo) {
		modelo.addAttribute("nombreUsu",nom);
		modelo.addAttribute("saludo", "Este es mi primer intento, Hola ");
		return "index";
	}
	
	@GetMapping("/saludo")
	public String m2() {
		return "saludar";
	}
	
	@GetMapping("/quienes")
	public String m3() {
		return "descripcion";
	}

}
