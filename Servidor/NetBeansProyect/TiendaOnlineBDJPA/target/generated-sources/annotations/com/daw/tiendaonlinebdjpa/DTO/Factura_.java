package com.daw.tiendaonlinebdjpa.DTO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T09:44:13")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Integer> numfactura;
    public static volatile SingularAttribute<Factura, Boolean> pagado;
    public static volatile SingularAttribute<Factura, String> usuario;
    public static volatile SingularAttribute<Factura, Float> importe;

}