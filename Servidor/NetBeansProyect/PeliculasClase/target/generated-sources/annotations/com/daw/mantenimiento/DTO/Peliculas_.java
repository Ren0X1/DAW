package com.daw.mantenimiento.DTO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-02T09:43:54")
@StaticMetamodel(Peliculas.class)
public class Peliculas_ { 

    public static volatile SingularAttribute<Peliculas, Boolean> estreno;
    public static volatile SingularAttribute<Peliculas, Integer> codigoPelicula;
    public static volatile SingularAttribute<Peliculas, String> director;
    public static volatile SingularAttribute<Peliculas, String> genero;
    public static volatile SingularAttribute<Peliculas, String> imagen;
    public static volatile SingularAttribute<Peliculas, Date> fechaInsercion;
    public static volatile SingularAttribute<Peliculas, String> nombre;
    public static volatile SingularAttribute<Peliculas, Boolean> publico;

}