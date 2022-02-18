package com.daw.peliculas.DTO;

import com.daw.peliculas.DTO.Actor;
import com.daw.peliculas.DTO.Pelicula;
import com.daw.peliculas.DTO.RepartoPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T12:14:42")
@StaticMetamodel(Reparto.class)
public class Reparto_ { 

    public static volatile SingularAttribute<Reparto, Actor> actor;
    public static volatile SingularAttribute<Reparto, Pelicula> pelicula;
    public static volatile SingularAttribute<Reparto, RepartoPK> repartoPK;
    public static volatile SingularAttribute<Reparto, Integer> minutos;

}