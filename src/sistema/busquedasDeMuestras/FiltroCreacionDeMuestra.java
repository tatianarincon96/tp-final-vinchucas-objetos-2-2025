package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroCreacionDeMuestra implements Consultable {

    //------- Atributos del FiltroCreacionDeMuestra -------

    private LocalDateTime desde;
    private LocalDateTime hasta;

    //------- Constructores de la clase FiltroCreacionDeMuestra -------

    /**
     * Constructor de la clase FiltroCreacionDeMuestra.
     * @param desde Fecha y hora desde la cual se filtrarán las muestras.
     * @param hasta Fecha y hora hasta la cual se filtrarán las muestras.
     */
    public FiltroCreacionDeMuestra(LocalDateTime desde, LocalDateTime hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    //------- Métodos de clase -------
    /**
     * Metodo que filtra las muestras según el criterio de creación.
     * @param muestras Lista de muestras a filtrar.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras.stream().filter(m -> m.getFechaDeCreacion().isAfter(desde) && m.getFechaDeCreacion().isBefore(hasta)).toList();
    }
}
