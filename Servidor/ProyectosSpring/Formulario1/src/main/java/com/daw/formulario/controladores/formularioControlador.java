package com.daw.formulario.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.daw.formulario.modelo.Alumno;
import com.daw.formulario.services.alumnoService;

@Controller
public class formularioControlador {

	@Autowired
	public alumnoService alumnosDAW;
	
	@GetMapping({"","/volver"})
	public String metodo1(Model model) {
		model.addAttribute("listaAlumnos",alumnosDAW.finAll());
		model.addAttribute(new Alumno());
		return "index";
	}
	
	@PostMapping("nuevoAlumno")
	public String metodo2(@ModelAttribute() Alumno alumnoNuevo, Model model) {
		model.addAttribute("alumno", alumnoNuevo);
		alumnosDAW.addAlumno(alumnoNuevo);
		return "pintar";
	}
}
