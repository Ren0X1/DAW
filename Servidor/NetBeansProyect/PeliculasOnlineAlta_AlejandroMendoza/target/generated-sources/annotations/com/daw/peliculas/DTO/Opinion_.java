package com.daw.peliculas.DTO;

import com.daw.peliculas.DTO.Pelicula;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T12:14:42")
@StaticMetamodel(Opinion.class)
public class Opinion_ { 

    public static volatile SingularAttribute<Opinion, Integer> codComen;
    public static volatile SingularAttribute<Opinion, Integer> puntuacion;
    public static volatile SingularAttribute<Opinion, Pelicula> codPelicula;
    public static volatile SingularAttribute<Opinion, String> comentario;

}