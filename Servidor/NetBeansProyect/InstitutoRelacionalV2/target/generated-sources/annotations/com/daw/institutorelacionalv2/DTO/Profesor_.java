package com.daw.institutorelacionalv2.DTO;

import com.daw.institutorelacionalv2.DTO.Asignatura;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T09:43:49")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, String> nombreprofesor;
    public static volatile ListAttribute<Profesor, Asignatura> asignaturaList;
    public static volatile SingularAttribute<Profesor, String> dniprofesor;

}