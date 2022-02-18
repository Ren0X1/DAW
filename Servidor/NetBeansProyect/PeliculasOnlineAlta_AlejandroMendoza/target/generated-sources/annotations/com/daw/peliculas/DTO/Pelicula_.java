package com.daw.peliculas.DTO;

import com.daw.peliculas.DTO.Director;
import com.daw.peliculas.DTO.Genero;
import com.daw.peliculas.DTO.Opinion;
import com.daw.peliculas.DTO.Reparto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T12:14:41")
@StaticMetamodel(Pelicula.class)
public class Pelicula_ { 

    public static volatile SingularAttribute<Pelicula, Integer> estreno;
    public static volatile SingularAttribute<Pelicula, Integer> puntuacion;
    public static volatile SingularAttribute<Pelicula, Integer> codPelicula;
    public static volatile ListAttribute<Pelicula, Opinion> opinionList;
    public static volatile SingularAttribute<Pelicula, String> titulo;
    public static volatile SingularAttribute<Pelicula, String> imagen;
    public static volatile SingularAttribute<Pelicula, Genero> codGenero;
    public static volatile ListAttribute<Pelicula, Reparto> repartoList;
    public static volatile SingularAttribute<Pelicula, Director> codDirector;

}