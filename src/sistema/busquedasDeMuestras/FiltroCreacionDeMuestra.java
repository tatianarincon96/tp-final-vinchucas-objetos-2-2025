package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un filtro para buscar las muestras según su fecha de creación de muestra.
 */
public class FiltroCreacionDeMuestra implements Consultable {

    // Atributos de la clase FiltroCreacionDeMuestra

    /**
     * Fecha y hora desde la cual se filtrarán las muestras.
     */
    private LocalDateTime desde;

    /**
     * Fecha y hora hasta la cual se filtrarán las muestras.
     */
    private LocalDateTime hasta;



    // Constructores de la clase FiltroCreacionDeMuestra

    /**
     * Constructor de la clase FiltroCreacionDeMuestra.
     * @param desde Fecha y hora desde la cual se filtrarán las muestras.
     * @param hasta Fecha y hora hasta la cual se filtrarán las muestras.
     */
    public FiltroCreacionDeMuestra(LocalDateTime desde, LocalDateTime hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }




    // Métodos de la clase FiltroCreacionDeMuestra

    /**
     * Metodo que filtra las muestras según el rango de fechas de creación.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por el rango de fechas de creación.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras.stream()
                .filter(m -> m.getFechaDeCreacion().isAfter(desde) && m.getFechaDeCreacion().isBefore(hasta))
                .toList();
    }


}
