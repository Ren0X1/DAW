package com.daw.peliculas.DTO;

import com.daw.peliculas.DTO.Reparto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T12:14:41")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile SingularAttribute<Actor, Integer> codActor;
    public static volatile SingularAttribute<Actor, String> nombreActor;
    public static volatile ListAttribute<Actor, Reparto> repartoList;
    public static volatile SingularAttribute<Actor, String> sexo;
    public static volatile SingularAttribute<Actor, String> nacionalidad;

}