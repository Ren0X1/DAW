package com.daw.juegapalabrasjsf_alejandromendoza.beans.subida;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Jugadores;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@ManagedBean(name = "beanFoto")
@SessionScoped
public class beanFoto implements Serializable {
    @ManagedProperty("#{beanLogin.user}")
    private String nifUsu;
    private Part foto;
    private final Utilidades utils_ = new Utilidades();
    
    public beanFoto() {
    }

    public String getNifUsu() {
        return nifUsu;
    }

    public void setNifUsu(String nifUsu) {
        this.nifUsu = nifUsu;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }
        
    public static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
    
    public void ir() {
        redireccionar("jugar.jsp");
    }
    
    public void subir() throws IOException {
        if (foto != null && foto.getSize() > 0) {
            String fileName = nombreArchivo(foto);
            Jugadores v = utils_.getCtrJugadores().findJugadores(nifUsu);
            v.setImgPerfil(fileName);
            File savedFile = new File("/files", fileName);
            try (InputStream input = foto.getInputStream()) {
                Files.copy(input, savedFile.toPath());
            } catch (IOException e) {
            }
        }
    }
    
    public static String nombreArchivo(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
                if (content.trim().startsWith("filename")) {
                    String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                    return fileName;
                }
        }
        return null;
    }
    
}
