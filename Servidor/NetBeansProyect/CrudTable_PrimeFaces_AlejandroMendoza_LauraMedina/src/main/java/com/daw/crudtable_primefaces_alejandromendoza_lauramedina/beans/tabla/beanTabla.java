package com.daw.crudtable_primefaces_alejandromendoza_lauramedina.beans.tabla;

import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.exceptions.NonexistentEntityException;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO.Articulo;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO.Proveedor;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.utils.Utilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;

public class beanTabla {
    private final Utilidades utils_ = new Utilidades();
    private List<Articulo> listaArticulos;
    private List<Articulo> listaSelect;
    private Articulo articuloSelect;
    private ArrayList listaProveedores;
    private boolean creando = false;
    
    public beanTabla() {
    }

    public List<Articulo> getListaArticulos() {
        if (listaArticulos==null || listaArticulos.isEmpty()) {
            listaArticulos = cargarLista();
        }
        return listaArticulos;
    }

    public List<Articulo> getListaSelect() {
        return listaSelect;
    }

    public boolean isCreando() {
        return creando;
    }

    public void setCreando(boolean creando) {
        this.creando = creando;
    }
    
    public void openNew() {
        this.articuloSelect = new Articulo();
        creando = true;
    }

    public void setListaSelect(List<Articulo> listaSelect) {
        this.listaSelect = listaSelect;
    }

    public void setListaArticulos(List<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    private List<Articulo> cargarLista() {
        return utils_.getCtrArticulo().findArticuloEntities();
    }

    public ArrayList getListaProveedores() {
        listaProveedores = new ArrayList();
        if (listaProveedores.isEmpty()) {
            for (Proveedor o:utils_.getCtrProveedor().findProveedorEntities()) {
                listaProveedores.add(new SelectItem(o.getCodProveedor(),o.getNombreProveedor()));
            }
        }
        return listaProveedores;
    }

    public void setListaProveedores(ArrayList ListaProveedores) {
        this.listaProveedores = ListaProveedores;
    }
    
    public Articulo getArticuloSelect() {
        return articuloSelect;
    }

    public void setArticuloSelect(Articulo articuloSelect) {
        this.articuloSelect = articuloSelect;
    }
    
    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.listaSelect.size();
            return size > 1 ? size + " articulos seleccionados" : "1 articulo seleccionado";
        }

        return "Borrar";
    }

    public boolean hasSelectedProducts() {
        return this.listaSelect != null && !this.listaSelect.isEmpty();
    }

    public void deleteSelectedProducts() {
        for (Articulo o:listaSelect) {
            try {
                utils_.getCtrArticulo().destroy(o.getCodArticulo());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(beanTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.listaSelect = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articulos Borrados!"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }
    
    
    public void saveProduct() {
        if (creando) {
            utils_.getCtrArticulo().create(articuloSelect);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articulo añadido"));
            creando = false;
        }
        else {
            try {
                utils_.getCtrArticulo().edit(articuloSelect);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articulo modificado"));
            } catch (Exception ex) {
                Logger.getLogger(beanTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        try {
            utils_.getCtrArticulo().destroy(articuloSelect.getCodArticulo());
            this.articuloSelect = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articulo borrado"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(beanTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarVista() {
        listaArticulos = cargarLista();
    }
    
}
