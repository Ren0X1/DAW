package com.daw.institutorelacional.DTO;

import com.daw.institutorelacional.DTO.Matricula;
import com.daw.institutorelacional.DTO.Profesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-01T17:57:20")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile SingularAttribute<Asignatura, Integer> codasignatura;
    public static volatile SingularAttribute<Asignatura, String> curso;
    public static volatile ListAttribute<Asignatura, Matricula> matriculaList;
    public static volatile SingularAttribute<Asignatura, String> nombreasig;
    public static volatile SingularAttribute<Asignatura, Profesor> dniprofesor;

}