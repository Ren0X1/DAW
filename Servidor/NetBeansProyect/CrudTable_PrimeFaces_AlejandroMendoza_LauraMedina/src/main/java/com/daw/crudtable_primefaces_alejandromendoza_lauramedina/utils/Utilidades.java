package com.daw.crudtable_primefaces_alejandromendoza_lauramedina.utils;

import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.ArticuloJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.ClienteJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.DetalleJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.DetallepedidosJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.FacturaJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.ProveedorJpaController;
import com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DAO.VistadetalleJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utilidades {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("primefacesPU");
    private final ArticuloJpaController ctrArticulo = new ArticuloJpaController(emf);
    private final ClienteJpaController ctrCliente = new ClienteJpaController(emf);
    private final DetalleJpaController ctrDetalle = new DetalleJpaController(emf);
    private final DetallepedidosJpaController ctrDetallePedido = new DetallepedidosJpaController(emf);
    private final FacturaJpaController ctrFactura = new FacturaJpaController(emf);
    private final ProveedorJpaController ctrProveedor = new ProveedorJpaController(emf);
    private final VistadetalleJpaController ctrVistaDetalle = new VistadetalleJpaController(emf);

    public ArticuloJpaController getCtrArticulo() {
        return ctrArticulo;
    }

    public ClienteJpaController getCtrCliente() {
        return ctrCliente;
    }

    public DetalleJpaController getCtrDetalle() {
        return ctrDetalle;
    }

    public DetallepedidosJpaController getCtrDetallePedido() {
        return ctrDetallePedido;
    }

    public FacturaJpaController getCtrFactura() {
        return ctrFactura;
    }

    public ProveedorJpaController getCtrProveedor() {
        return ctrProveedor;
    }

    public VistadetalleJpaController getCtrVistaDetalle() {
        return ctrVistaDetalle;
    }
    
    
}
