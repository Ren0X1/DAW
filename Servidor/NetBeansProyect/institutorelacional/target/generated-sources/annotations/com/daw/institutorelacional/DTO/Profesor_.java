package com.daw.institutorelacional.DTO;

import com.daw.institutorelacional.DTO.Asignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-01T17:57:20")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, String> nombreprofesor;
    public static volatile ListAttribute<Profesor, Asignatura> asignaturaList;
    public static volatile SingularAttribute<Profesor, String> dniprofesor;

}