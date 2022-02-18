package com.daw.interfaces;

import com.daw.DTO.Generos;

public interface InterfacesGenerosDAO {
    public boolean create(Generos v);
    public boolean update(Generos v);
    public boolean delete(Generos v);
    public boolean insert(Generos v);
}
