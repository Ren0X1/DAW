package com.daw.juegapalabrasjsf_alejandromendoza.utils;

import com.daw.juegapalabrasjsf_alejandromendoza.DAO.DiccionarioJpaController;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.EnglishJpaController;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.JugadoresJpaController;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.SpanishJpaController;
import com.daw.juegapalabrasjsf_alejandromendoza.DAO.VistawordsJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utilidades {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("juegaPalabrasPU");
    private final DiccionarioJpaController ctrDiccionario = new DiccionarioJpaController(emf);
    private final EnglishJpaController ctrEnglish = new EnglishJpaController(emf);
    private final JugadoresJpaController ctrJugadores = new JugadoresJpaController(emf);
    private final SpanishJpaController ctrSpanish = new SpanishJpaController(emf);
    private final VistawordsJpaController ctrVista = new VistawordsJpaController(emf);

    public DiccionarioJpaController getCtrDiccionario() {
        return ctrDiccionario;
    }

    public EnglishJpaController getCtrEnglish() {
        return ctrEnglish;
    }

    public JugadoresJpaController getCtrJugadores() {
        return ctrJugadores;
    }

    public SpanishJpaController getCtrSpanish() {
        return ctrSpanish;
    }

    public VistawordsJpaController getCtrVista() {
        return ctrVista;
    }
        
}

