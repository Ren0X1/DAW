package com.daw.institutorelacionalv2.DTO;

import com.daw.institutorelacionalv2.DTO.Matricula;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T09:43:49")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, Integer> nmatricula;
    public static volatile ListAttribute<Alumno, Matricula> matriculaList;
    public static volatile SingularAttribute<Alumno, String> nombrealumno;
    public static volatile SingularAttribute<Alumno, String> dni;
    public static volatile SingularAttribute<Alumno, String> email;

}