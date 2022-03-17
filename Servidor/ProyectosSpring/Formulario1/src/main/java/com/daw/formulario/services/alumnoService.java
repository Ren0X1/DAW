package com.daw.formulario.services;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.daw.formulario.modelo.Alumno;

@Service
public class alumnoService {
	private Map<Integer, Alumno> listaALumno = new HashMap<Integer, Alumno>();

	public alumnoService() {
		super();
	}

	public Alumno findById(int i) {
		Alumno a = listaALumno.get(i);
		if (a == null) {
			a = new Alumno();
		}
		return a;
	}

	public Alumno addAlumno(Alumno a) {
		listaALumno.put(a.getId(), a);
		return a;
	}

	public Map<Integer, Alumno> finAll() {
		return listaALumno;
	}

	public void setListaALumno(Map<Integer, Alumno> listaALumno) {
		this.listaALumno = listaALumno;
	}

	public Map<Integer, Alumno> getListaALumno() {
		return listaALumno;
	}

	@PostConstruct
	public void init() {
		listaALumno.put(1, new Alumno(1, "pepe", "garcía", 19));
		listaALumno.put(2, new Alumno(2, "María", "Martín", 21));
		listaALumno.put(3, new Alumno(3, "Rosa", "Abaad", 20));
	}
}
