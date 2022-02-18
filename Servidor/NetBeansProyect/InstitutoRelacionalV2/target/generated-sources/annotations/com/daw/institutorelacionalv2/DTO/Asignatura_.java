package com.daw.institutorelacionalv2.DTO;

import com.daw.institutorelacionalv2.DTO.Matricula;
import com.daw.institutorelacionalv2.DTO.Profesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T09:43:49")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile SingularAttribute<Asignatura, Integer> codasignatura;
    public static volatile SingularAttribute<Asignatura, String> nombreasignatura;
    public static volatile SingularAttribute<Asignatura, String> curso;
    public static volatile ListAttribute<Asignatura, Matricula> matriculaList;
    public static volatile SingularAttribute<Asignatura, Profesor> dniprofesor;

}