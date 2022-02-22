package com.daw.subidaarchivosjsf.BEANS;

import com.daw.subidaarchivosjsf.DAO.CarritoJpaController;
import com.daw.subidaarchivosjsf.DAO.FloresJpaController;
import com.daw.subidaarchivosjsf.DAO.LuzJpaController;
import com.daw.subidaarchivosjsf.DAO.UsuariosJpaController;
import com.daw.subidaarchivosjsf.DAO.VistacarritoJpaController;
import com.daw.subidaarchivosjsf.DAO.exceptions.NonexistentEntityException;
import com.daw.subidaarchivosjsf.DTO.Carrito;
import com.daw.subidaarchivosjsf.DTO.CarritoPK;
import com.daw.subidaarchivosjsf.DTO.Flores;
import com.daw.subidaarchivosjsf.DTO.Luz;
import com.daw.subidaarchivosjsf.DTO.Usuarios;
import com.daw.subidaarchivosjsf.DTO.Vistacarrito;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanFloristeria {

    private EntityManagerFactory emf;

    private LuzJpaController ctrLuz;
    private FloresJpaController ctrFlores;
    private UsuariosJpaController ctrUsuario;
    private CarritoJpaController ctrCarrito;
    private VistacarritoJpaController ctrVista;

    private ArrayList listadoCaracteristicas;
    private ArrayList listaFloresFiltrado;
    private ArrayList cestaCliente;

    private Flores florActual;

    private ListIterator<Flores> itr;

    private boolean siguiente;
    private boolean anterior;

    private HtmlCommandButton btnReducir;
    private HtmlCommandButton btnAumentar;
    private HtmlCommandButton btnrestaurar;
    private HtmlGraphicImage respaldoImagen;
    private HtmlDataTable respaldoTabla;
    private HtmlDataTable respaldoTablaCesta;

    private String dni;
    private String username;
    private String password;

    private String nombreComunAlta;
    private String nombreCientificoAlta;
    private int caracteristicasAlta;
    private int precioAlta;
    private String imagenAlta;

    private String categoriaActual;
    private int ejemplares;

    public BeanFloristeria() {
        this.emf = Persistence.createEntityManagerFactory("subidaArchivosJSFPU");

        this.ctrLuz = new LuzJpaController(this.emf);
        this.ctrFlores = new FloresJpaController(this.emf);
        this.ctrUsuario = new UsuariosJpaController(this.emf);
        this.ctrCarrito = new CarritoJpaController(this.emf);
        this.ctrVista = new VistacarritoJpaController(emf);

        this.listaFloresFiltrado = new ArrayList();
        this.cestaCliente = new ArrayList();
        this.florActual = new Flores();
    }

    public ArrayList getListadoCaracteristicas() {
        List<Luz> listadoCaracteristicasAux = this.ctrLuz.findLuzEntities();
        this.listadoCaracteristicas = new ArrayList();

        for (Luz luz : listadoCaracteristicasAux) {
            this.listadoCaracteristicas.add(new SelectItem(luz.getCodigoLuz(), luz.getTipoLuz() + " - " + luz.getAgua()));
        }
        return listadoCaracteristicas;
    }

    public void setListadoCaracteristicas(ArrayList listadoCaracteristicas) {
        this.listadoCaracteristicas = listadoCaracteristicas;
    }

    public ArrayList getListaFloresFiltrado() {
        return listaFloresFiltrado;
    }

    public void setListaFloresFiltrado(ArrayList listaFloresFiltrado) {
        this.listaFloresFiltrado = listaFloresFiltrado;
    }

    public Flores getFlorActual() {
        return florActual;
    }

    public void setFlorActual(Flores florActual) {
        this.florActual = florActual;
    }

    public void setCategoriaActual(String categoriaActual) {
        this.categoriaActual = categoriaActual;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public HtmlGraphicImage getRespaldoImagen() {
        return respaldoImagen;
    }

    public void setRespaldoImagen(HtmlGraphicImage respaldoImagen) {
        this.respaldoImagen = respaldoImagen;
    }

    public HtmlDataTable getRespaldoTabla() {
        return respaldoTabla;
    }

    public void setRespaldoTabla(HtmlDataTable respaldoTabla) {
        this.respaldoTabla = respaldoTabla;
    }

    public HtmlCommandButton getBtnReducir() {
        return btnReducir;
    }

    public void setBtnReducir(HtmlCommandButton btnReducir) {
        this.btnReducir = btnReducir;
    }

    public HtmlCommandButton getBtnAumentar() {
        return btnAumentar;
    }

    public void setBtnAumentar(HtmlCommandButton btnAumentar) {
        this.btnAumentar = btnAumentar;
    }

    public HtmlCommandButton getBtnrestaurar() {
        return btnrestaurar;
    }

    public void setBtnrestaurar(HtmlCommandButton btnrestaurar) {
        this.btnrestaurar = btnrestaurar;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreComunAlta() {
        return nombreComunAlta;
    }

    public void setNombreComunAlta(String nombreComunAlta) {
        this.nombreComunAlta = nombreComunAlta;
    }

    public String getNombreCientificoAlta() {
        return nombreCientificoAlta;
    }

    public void setNombreCientificoAlta(String nombreCientificoAlta) {
        this.nombreCientificoAlta = nombreCientificoAlta;
    }

    public int getCaracteristicasAlta() {
        return caracteristicasAlta;
    }

    public void setCaracteristicasAlta(int caracteristicasAlta) {
        this.caracteristicasAlta = caracteristicasAlta;
    }

    public int getPrecioAlta() {
        return precioAlta;
    }

    public void setPrecioAlta(int precioAlta) {
        this.precioAlta = precioAlta;
    }

    public String getImagenAlta() {
        return imagenAlta;
    }

    public void setImagenAlta(String imagenAlta) {
        this.imagenAlta = imagenAlta;
    }

    public String getCategoriaActual() {
        return categoriaActual;
    }

    public ArrayList getCestaCliente() {
        return cestaCliente;
    }

    public void setCestaCliente(ArrayList cestaCliente) {
        this.cestaCliente = cestaCliente;
    }

    public HtmlDataTable getRespaldoTablaCesta() {
        return respaldoTablaCesta;
    }

    public void setRespaldoTablaCesta(HtmlDataTable respaldoTablaCesta) {
        this.respaldoTablaCesta = respaldoTablaCesta;
    }

    public void filtradoFlores(ValueChangeEvent e) {
        Object value = e.getNewValue();
        String codCategoria = (String) value;

        Luz luz = this.ctrLuz.findLuz(Integer.parseInt(codCategoria));

        List<Flores> filtrado = this.ctrFlores.palabrasFiltro(Integer.parseInt(codCategoria));
        this.ejemplares = filtrado.size();
        this.categoriaActual = luz.getTipoLuz() + " - " + luz.getAgua();
        this.listaFloresFiltrado = new ArrayList();
        this.listaFloresFiltrado.addAll(filtrado);
        this.itr = this.listaFloresFiltrado.listIterator();

        this.florActual = this.itr.next();
    }

    public void siguienteFlor() {
        this.siguiente = true;
        if (this.anterior) {
            this.anterior = false;
            this.itr.next();
        }
        this.florActual = this.itr.next();
    }

    public void florAnterior() {
        this.anterior = true;
        if (this.siguiente) {
            this.siguiente = false;
            this.itr.previous();
        }
        this.florActual = this.itr.previous();
    }

    public void reducirImagen() {
        this.respaldoImagen.setStyle("width:200px;");
        this.btnReducir.setStyle("display:none;");
        this.btnAumentar.setStyle("display:block;");
    }

    public void restaurarImagen() {
        this.respaldoImagen.setStyle("width:400px;");
        this.btnReducir.setStyle("display:block;");
        this.btnAumentar.setStyle("display:block;");
    }

    public void aumentarImagen() {
        this.respaldoImagen.setStyle("width:600px;");
        this.btnReducir.setStyle("display:block;");
        this.btnAumentar.setStyle("display:none;");
    }

    public void borrarImagen() {
        this.itr.remove();
        this.florActual = this.itr.next();
        this.ejemplares--;
    }

    public void infoImagen() {
        this.respaldoTabla.setStyle("display:table;");
    }

    public void cerrarInfo() {
        this.respaldoTabla.setStyle("display:none;");
    }

    public String login() {
        List<Usuarios> usuarioBusqueda = this.ctrUsuario.comprobarUser(this.dni, this.password);
        if (!usuarioBusqueda.isEmpty()) {
            return "logueado";
        } else {
            return "noLogueado";
        }
    }

    public void comprar() {
        Flores florActual = this.florActual;
        CarritoPK carritoKey = new CarritoPK(this.dni, florActual.getCodigoFlor());
        Carrito carrito = new Carrito(carritoKey, 1, florActual.getPrecio());

        try {
            this.ctrCarrito.create(carrito);
            florActual.aumentarVentas();
            this.ctrFlores.edit(florActual);
        } catch (Exception ex) {
            Logger.getLogger(BeanFloristeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String alta() {
        Flores florNueva = new Flores(null, this.nombreComunAlta, this.nombreCientificoAlta,
                this.caracteristicasAlta, this.precioAlta, 0, this.imagenAlta + ".jpg");
        this.ctrFlores.create(florNueva);
        return "altaRealizada";
    }

    public String irCarrito() {
        List<Vistacarrito> cestaAux = this.ctrVista.carritoUsuario(this.dni);
        this.cestaCliente.clear();
        this.cestaCliente.addAll(cestaAux);
        return "carrito";
    }

    public void aumentarCompra() {
        Vistacarrito vCarrito = (Vistacarrito) this.respaldoTablaCesta.getRowData();
        CarritoPK carritoPK = new CarritoPK(vCarrito.getDniusuario(), vCarrito.getCodigoFlor());
        
        Carrito carrito = this.ctrCarrito.findCarrito(carritoPK);
        carrito.aumentarUnds();
        
        carrito.establecerPrecio(vCarrito.getPrecio());
        
        try {
            this.ctrCarrito.edit(carrito);
            irCarrito();
        } catch (Exception ex) {
            Logger.getLogger(BeanFloristeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reducirCompra() {
        Vistacarrito vCarrito = (Vistacarrito) this.respaldoTablaCesta.getRowData();
        CarritoPK carritoPK = new CarritoPK(vCarrito.getDniusuario(), vCarrito.getCodigoFlor());
        
        Carrito carrito = this.ctrCarrito.findCarrito(carritoPK);
        carrito.disminuirUnds();
        
        carrito.establecerPrecio(vCarrito.getPrecio());
        
        try {
            this.ctrCarrito.edit(carrito);
            irCarrito();
        } catch (Exception ex) {
            Logger.getLogger(BeanFloristeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarCompra() {
        Vistacarrito vCarrito = (Vistacarrito) this.respaldoTablaCesta.getRowData();
        CarritoPK carritoPK = new CarritoPK(vCarrito.getDniusuario(), vCarrito.getCodigoFlor());
        
        try {
            this.ctrCarrito.destroy(carritoPK);
            irCarrito();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanFloristeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}