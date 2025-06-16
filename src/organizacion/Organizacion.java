package organizacion;

import muestra.Muestra;
import organizacion.plugins.FuncionalidadExterna;
import ubicacion.Ubicacion;
import zonaDeCobertura.ZonaDeCobertura;

import java.util.Observable;
import java.util.Observer;


/**
 * Organizacion es una clase que representa una organización que puede suscribirse a zonas de cobertura para
 * recibir actualizaciones sobre muestras y realizar acciones basadas en estas actualizaciones.
 */
public class Organizacion implements Observer {



    // Atributos de la organización

    /**
     * Ubicación principal de la organización, representada por una instancia de Ubicacion.
     */
    private Ubicacion ubicacionPrincipal;

    /**
     * Cantidad de personal que trabaja en la organización.
     */
    private int cantidadDePersonal;

    /**
     * Plugin que maneja la funcionalidad de nuevas muestras.
     */
    private FuncionalidadExterna pluginMuestraNueva;

    /**
     * Plugin que maneja la funcionalidad de muestras verificadas.
     */
    private FuncionalidadExterna PluginMuestraVerificada;






    // Constructores de la clase Organizacion

    /**
     * Constructor de la clase Organizacion.
     * @param ubicacionPrincipal ubicacion principal de la organización, representada por una instancia de Ubicacion.
     * @param cantidadDePersonal cantidad de personal que trabaja en la organización.
     * @param pluginMuestraNueva plugin que maneja la funcionalidad de nuevas muestras, representada por una instancia de FuncionalidadExterna.
     * @param pluginMuestraVerificada plugin que maneja la funcionalidad de muestras verificadas, representada por una instancia de FuncionalidadExterna.
     */
    public Organizacion(Ubicacion ubicacionPrincipal, int cantidadDePersonal, FuncionalidadExterna pluginMuestraNueva, FuncionalidadExterna pluginMuestraVerificada) {
        this.ubicacionPrincipal = ubicacionPrincipal;
        this.cantidadDePersonal = cantidadDePersonal;
        this.pluginMuestraNueva = pluginMuestraNueva;
        this.PluginMuestraVerificada = pluginMuestraVerificada;
    }

    /**
     * Constructor de la clase Organizacion.
     * @param ubicacionPrincipal ubicacion principal de la organización, representada por una instancia de Ubicacion.
     * @param cantidadDePersonal cantidad de personal que trabaja en la organización.
     * @param pluginMuestra plugin que maneja la funcionalidad de nuevas muestras y muestras verificadas, representada por una instancia de FuncionalidadExterna.
     */
    public Organizacion(Ubicacion ubicacionPrincipal, int cantidadDePersonal, FuncionalidadExterna pluginMuestra) {
        this(ubicacionPrincipal, cantidadDePersonal, pluginMuestra, pluginMuestra);
    }






    // Getters para acceder a los atributos de la organización

    /**
     * Obtiene la ubicación principal de la organización.
     * @return ubicacion principal de la organización, representada por una instancia de Ubicacion.
     */
    public Ubicacion getUbicacionPrincipal() {
        return ubicacionPrincipal;
    }

    /**
     * Obtiene la cantidad de personal que trabaja en la organización.
     * @return cantidad de personal que trabaja en la organización.
     */
    public int getCantidadDePersonal() {
        return cantidadDePersonal;
    }

    /**
     * Obtiene el plugin que maneja la funcionalidad de nuevas muestras.
     * @return plugin que maneja la funcionalidad de nuevas muestras, representada por una instancia de FuncionalidadExterna.
     */
    public FuncionalidadExterna getPluginMuestraNueva() {
        return pluginMuestraNueva;
    }

    /**
     * Obtiene el plugin que maneja la funcionalidad de muestras verificadas.
     * @return plugin que maneja la funcionalidad de muestras verificadas, representada por una instancia de FuncionalidadExterna.
     */
    public FuncionalidadExterna getPluginMuestraVerificada() {
        return PluginMuestraVerificada;
    }

    /**
     * Setea el plugin que maneja la funcionalidad de muestra nueva.
     * @param pluginMuestraNueva plugin que maneja la funcionalidad de nuevas muestras, representada por una instancia de FuncionalidadExterna.
     */
    public void setPluginMuestraNueva(FuncionalidadExterna pluginMuestraNueva) {
        this.pluginMuestraNueva = pluginMuestraNueva;
    }

    /**
     * Setea el plugin que maneja la funcionalidad de muestra verificada.
     * @param pluginMuestraVerificada plugin que maneja la funcionalidad de muestras verificadas, representada por una instancia de FuncionalidadExterna.
     */
    public void setPluginMuestraVerificada(FuncionalidadExterna pluginMuestraVerificada) {
        this.PluginMuestraVerificada = pluginMuestraVerificada;
    }






    // Métodos de la clase Organizacion

    /**
     * Suscribe a la organización a una zona de cobertura para recibir actualizaciones.
     * @param zonaDeCobertura la zona de cobertura a la que se desea suscribir, representada por una instancia de ZonaDeCobertura.
     */
    public void suscribirseALaZona(ZonaDeCobertura zonaDeCobertura) {
        zonaDeCobertura.addObserver(this);
    }



    /**
     * Desuscribe a la organización de una zona de cobertura para dejar de recibir actualizaciones.
     * @param zonaDeCobertura la zona de cobertura de la que se desea desuscribir, representada por una instancia de ZonaDeCobertura.
     */
    public void desuscribirseALaZona(ZonaDeCobertura zonaDeCobertura) {
        zonaDeCobertura.deleteObserver(this);
    }



    /**
     * Metodo que se invoca cuando la zona de cobertura notifica un cambio.
     * Si la muestra está verificada, se invoca el metodo nuevoEvento del plugin de muestra verificada.
     * Si no está verificada, se invoca el metodo nuevoEvento del plugin de nueva muestra.
     * @param o     el objeto observable que ha cambiado, en este caso una instancia de ZonaDeCobertura.
     * @param arg   argumento adicional que contiene la muestra que ha cambiado, en este caso una instancia de Muestra.
     */
    public void update(Observable o, Object arg) {
        Muestra muestra = (Muestra) arg;
        ZonaDeCobertura zonaDeCobertura = (ZonaDeCobertura) o;
        if (muestra.estaVerificada()) {
            this.PluginMuestraVerificada.nuevoEvento(this, zonaDeCobertura, muestra);
        } else {
            this.pluginMuestraNueva.nuevoEvento(this, zonaDeCobertura, muestra);
        }

    }

}
