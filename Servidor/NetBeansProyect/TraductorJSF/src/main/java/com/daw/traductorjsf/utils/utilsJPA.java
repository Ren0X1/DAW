package com.daw.traductorjsf.utils;

import com.daw.traductorjsf.DAO.DiccionarioJpaController;
import com.daw.traductorjsf.DAO.EnglishJpaController;
import com.daw.traductorjsf.DAO.SpanishJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class utilsJPA {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("traductorPU");
    private final DiccionarioJpaController ctrDiccionario = new DiccionarioJpaController(emf);
    private final EnglishJpaController ctrEnglish = new EnglishJpaController(emf);
    private final SpanishJpaController ctrSpanish = new SpanishJpaController(emf);
    
    public utilsJPA() {
    }

    public DiccionarioJpaController getCtrDiccionario() {
        return ctrDiccionario;
    }

    public EnglishJpaController getCtrEnglish() {
        return ctrEnglish;
    }

    public SpanishJpaController getCtrSpanish() {
        return ctrSpanish;
    }
    
}
