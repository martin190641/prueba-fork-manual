package jmriosp.com.tarea01.service;

import jmriosp.com.tarea01.model.Venta;

public interface VentaService extends GenericCRUD<Venta, Integer>{
    Venta registrarTransaccional(Venta venta)  throws Exception;
}
