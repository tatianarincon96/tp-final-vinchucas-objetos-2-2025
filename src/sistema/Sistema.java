package sistema;

import muestra.Muestra;
import sistema.busquedasDeMuestras.Consultable;
import usuarios.Usuario;
import zonaDeCobertura.ZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    //------- Atributos del Sistema -------

    private List<Muestra> muestras;
    private List<ZonaDeCobertura> zonasDeCobertura;

    //------- Constructores de la clase Sistema -------

    /**
     * Constructor de la clase Sistema.
     * Inicializa las listas de muestras y zonas de cobertura.
     */
    public Sistema() {
        this.muestras = new ArrayList<>();
        this.zonasDeCobertura = new ArrayList<>();
    }

    //------- Métodos de clase -------

    /**
     * Agrega una nueva muestra al sistema en la zona de cobertura correspondiente
     * @param muestra Muestra nueva que se va a agregar al sistema.
     */
    public void agregarNuevaMuestra(Muestra muestra) {
        muestras.add(muestra);
        agregarNuevaMuestraALasZonasDeCobertura(muestra);
    }

    /**
     * Agrega una nueva muestra al sistema en cada una de las zonas de cobertura que corresponda
     * @param muestra Muestra nueva que se va a agregar a cada una de las zonas de cobertura que corresponda.
     */
    private void agregarNuevaMuestraALasZonasDeCobertura(Muestra muestra) {
        // Chequear en que zona deberia agregar la muestra segun distancia al epicentro
        // y agregarla a la lista de muestras de esa zona
        for (ZonaDeCobertura zona : zonasDeCobertura) {
            if (muestra.getUbicacion().enZona(zona)) {
                zona.agregarNuevaMuestra(muestra);
            }
        }
    }

    /**
     * Actualiza el nivel de todos los usuarios del sistema que hayan creado muestras
     */
    public void actualizarNivelDeTodosLosUsuarios() {
        for (Muestra muestra : muestras) {
            Usuario usuario = muestra.getUsuario();
            if (usuario != null) {
                usuario.getNivel().updateNivel(usuario);
            }
        }
    }

    /**
     * Filtra las muestras del sistema según el criterio de búsqueda proporcionado.
     * @param consulta Criterio de búsqueda que implementa la interfaz Consultable.
     * @return Lista de muestras filtradas según el criterio de búsqueda.
     */
    public List<Muestra> filtrarLasMuestrasPor(Consultable consulta) {
        return consulta.filtrarLasMuestras(this.muestras);
    }


    /**
     *
     */
    public void agregarLaNuevaZonaDeCobertura(ZonaDeCobertura zona) {
        zonasDeCobertura.add(zona);
        for (Muestra muestra : muestras) {
            if (muestra.getUbicacion().enZona(zona)) {
                zona.agregarNuevaMuestra(muestra);
            }
        }
    }


}
