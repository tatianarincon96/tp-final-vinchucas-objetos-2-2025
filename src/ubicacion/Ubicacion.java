package ubicacion;

import muestra.Muestra;
import zonaDeCobertura.ZonaDeCobertura;

import java.util.List;


/**
 * Ubicacion es una clase que representa una ubicación geográfica en términos de latitud y longitud.
 */
public class Ubicacion {



    // Atributos de la ubicación

    /**
     * Latitud de la ubicación medida en radianes
     */
    private final double latitud;

    /**
     * Longitud de la ubicación medida en radianes
     */
    private final double longitud;






    // Constructores de la clase Ubicacion

    /**
     * Constructor de la clase Ubicacion.
     * @param latitud latitud en grados, debe estar entre -90 y 90.
     * @param longitud longitud en grados, debe estar entre -180 y 180.
     */
    public Ubicacion(double latitud, double longitud) {

        if (!(-90 < latitud && latitud < 90 && -180 < longitud && longitud < 180)) {
            throw new IllegalArgumentException("La latitud debe ser entre -90 y 90 y longitud entre -180 y 180");
        }

        this.latitud = Math.toRadians(latitud);
        this.longitud = Math.toRadians(longitud);
    }






    // Getters para acceder a las coordenadas de la ubicación

    /**
     * Obtiene la latitud de la ubicación.
     * @return latitud en radianes
     */
    public double getLatitud() { return latitud; }

    /**
     * Obtiene la longitud de la ubicación.
     * @return longitud en radianes
     */
    public double getLongitud() { return longitud; }






    // Métodos de la clase Ubicacion

    /**
     * Calcula la distancia entre esta ubicación y otra ubicación utilizando la fórmula del haversine.
     * @param otraUbicacion otra ubicación a la que se desea calcular la distancia.
     * @return distancia en kilómetros entre las dos ubicaciones.
     */
    public final double calcularDistanciaHasta(Ubicacion otraUbicacion) {
        double radioTierra = 6371; // Radio de la Tierra en kilómetros

        double deltaLat = otraUbicacion.getLatitud() - this.latitud;
        double deltaLon = otraUbicacion.getLongitud() - this.longitud;

        double a = ( Math.sin(deltaLat/2) * Math.sin(deltaLat/2) ) +
                ( Math.cos(this.latitud) * Math.cos(otraUbicacion.latitud) * Math.sin(deltaLon/2) * Math.sin(deltaLon/2) );

        double c = 2 * Math.atan2( Math.sqrt(a) , Math.sqrt(1-a) ) ;

        return radioTierra * c;
    }



    /**
     * Obtiene una lista de ubicaciones que se encuentran dentro de un radio especificado desde esta ubicación.
     * @param ubicaciones lista de ubicaciones a filtrar.
     * @param radio radio en kilómetros dentro del cual se buscarán las ubicaciones.
     * @return lista de ubicaciones que están dentro del radio especificado.
     */
    public List<Ubicacion> getUbicacionesEnUnRadio(List<Ubicacion> ubicaciones, double radio) {
        return ubicaciones.stream().filter(ubicacion -> ubicacion.calcularDistanciaHasta(this) <= radio).toList();
    }



    /**
     * Obtiene una lista de muestras que se encuentran dentro de un radio especificado desde una muestra central.
     * @param muestrasAFiltrar lista de muestras a filtrar.
     * @param radio radio en kilómetros dentro del cual se buscarán las muestras.
     * @param muestraCentral muestra central desde la cual se calculará la distancia.
     * @return lista de muestras que están dentro del radio especificado desde la muestra central.
     */
    public List<Muestra> getMuestrasEnUnRadioALaMuestra(List<Muestra> muestrasAFiltrar, double radio, Muestra muestraCentral) {
        return muestrasAFiltrar.stream().filter(muestra -> muestra.getUbicacion().calcularDistanciaHasta(muestraCentral.getUbicacion()) <= radio).toList() ;
    }



    /**
     * Verifica si esta ubicación se encuentra dentro de una zona de cobertura.
     * @param zonaDeCobertura zona de cobertura a verificar.
     * @return true si la ubicación está dentro de la zona de cobertura, false en caso contrario.
     */
    public boolean enZona(ZonaDeCobertura zonaDeCobertura) {
        return this.calcularDistanciaHasta(zonaDeCobertura.getEpicentro()) <= zonaDeCobertura.getRadio() ;
    }

}
