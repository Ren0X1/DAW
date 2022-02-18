package com.daw.institutorelacional.DTO;

import com.daw.institutorelacional.DTO.Alumno;
import com.daw.institutorelacional.DTO.Asignatura;
import com.daw.institutorelacional.DTO.MatriculaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T09:43:04")
@StaticMetamodel(Matricula.class)
public class Matricula_ { 

    public static volatile SingularAttribute<Matricula, Integer> nota1;
    public static volatile SingularAttribute<Matricula, Asignatura> asignatura;
    public static volatile SingularAttribute<Matricula, MatriculaPK> matriculaPK;
    public static volatile SingularAttribute<Matricula, Integer> nota2;
    public static volatile SingularAttribute<Matricula, Integer> nota3;
    public static volatile SingularAttribute<Matricula, Integer> notafinal;
    public static volatile SingularAttribute<Matricula, Alumno> alumno;

}