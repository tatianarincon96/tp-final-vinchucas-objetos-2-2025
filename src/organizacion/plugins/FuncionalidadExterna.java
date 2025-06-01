package organizacion.plugins;

import muestra.Muestra;
import zonaDeCobertura.ZonaDeCobertura;
import organizacion.Organizacion;


/**
 * FuncionalidadExterna es una interfaz que define un metodo para manejar eventos.
 */
public interface FuncionalidadExterna {
    public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zonaDeCobertura, Muestra muestra);
}
