package com.daw.peliculas.DTO;

import com.daw.peliculas.DTO.Pelicula;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T12:14:42")
@StaticMetamodel(Director.class)
public class Director_ { 

    public static volatile SingularAttribute<Director, String> nombreDirector;
    public static volatile ListAttribute<Director, Pelicula> peliculaList;
    public static volatile SingularAttribute<Director, Integer> codDIrector;
    public static volatile SingularAttribute<Director, String> nacionalidad;

}