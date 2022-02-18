package com.daw.interfaces;

import com.daw.DTO.Pelicula;

public interface InterfacesPeliculasDAO {
    public boolean create(Pelicula v);
    public boolean update(Pelicula v);
    public boolean delete(Pelicula v);
    public boolean insert(Pelicula v);
}
