package zonaDeCobertura;

import muestra.Muestra;
import ubicacion.Ubicacion;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * ZonaDeCobertura es una clase que observa la verificación de las muestras ubicadas dentro de su radio y es observada por organizaciones.
 */
public class ZonaDeCobertura extends Observable implements Observer {



    // Atributos de la zona de cobertura

    /**
     * Nombre de la zona de cobertura.
     */
    private String nombre;

    /**
     * Epicentro de la zona de cobertura, representado por una ubicación.
     */
    private Ubicacion epicentro;

    /**
     * Radio de la zona de cobertura, medido en kilómetros.
     */
    private double radio;

    /**
     * Lista de muestras que se encuentran dentro de la zona de cobertura.
     */
    private List<Muestra> muestrasEnLaZona;






    // Constructor de la clase ZonaDeCobertura

    /**
     * Constructor de la clase ZonaDeCobertura.
     * @param nombre nombre de la zona de cobertura.
     * @param epicentro epicentro de la zona de cobertura, representado por una ubicación.
     * @param radio radio de la zona de cobertura, medido en kilómetros.
     */
    public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
        this.nombre = nombre;
        this.epicentro = epicentro;
        this.radio = radio;
    }






    // Getters para acceder a los atributos de la zona de cobertura

    /**
     * Obtiene el nombre de la zona de cobertura.
     * @return nombre de la zona de cobertura.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el epicentro de la zona de cobertura.
     * @return epicentro de la zona de cobertura, representado por una ubicación.
     */
    public Ubicacion getEpicentro() {
        return epicentro;
    }

    /**
     * Obtiene el radio de la zona de cobertura.
     * @return radio de la zona de cobertura, medido en kilómetros.
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Obtiene la lista de muestras que se encuentran dentro de esta zona de cobertura.
     * @return lista de muestras que se encuentran dentro de esta zona de cobertura.
     */
    public List<Muestra> getMuestrasEnLaZona() {
        return muestrasEnLaZona;
    }






    // Métodos de la clase ZonaDeCobertura

    /**
     * Verifica si esta zona de cobertura se solapa con otra zona de cobertura.
     * @param otraZona otra zona de cobertura con la que se desea verificar el solapamiento.
     * @return true si las zonas se solapan, false en caso contrario.
     */
    public boolean seSolapaCon(ZonaDeCobertura otraZona) {
        return this.epicentro.calcularDistanciaHasta(otraZona.getEpicentro()) <= (this.radio + otraZona.getRadio());
    }



    /**
     * Agrega una nueva muestra a la zona de cobertura, la observa y notifica a los observadores de la zona de cobertura.
     * @param muestra muestra que se desea agregar a la zona de cobertura.
     */
    public void agregarNuevaMuestra(Muestra muestra) {
        this.muestrasEnLaZona.add(muestra);
        muestra.addObserver(this);
        notificarObservadoresSobreLaMuestra(muestra); // Notifica a los observadores que se ha agregado una nueva muestra.
    }



    /**
     * Metodo que se invoca cuando una muestra observada a sido verificada.
     * Este metodo notifica a los observadores de la zona de cobertura sobre el cambio en la muestra.
     * @param o objeto observable (en este caso, la muestra que ha cambiado).
     * @param arg argumento adicional (no utilizado en este caso).
     */
    public void update(Observable o, Object arg) {
        notificarObservadoresSobreLaMuestra((Muestra) o); // Notifica a los observadores de la zona de cobertura que una muestra ha sido verificada.
    }



    /**
     * Notifica a los observadores de la zona de cobertura sobre una muestra específica.
     * @param muestra muestra que se desea notificar a los observadores.
     */
    private void notificarObservadoresSobreLaMuestra(Muestra muestra) {
        this.setChanged();
        this.notifyObservers(muestra);
    }

}
