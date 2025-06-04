package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un filtro para buscar las muestras según la fecha de la última votación de una muestra.
 */
public class FiltroUltimaVotacion implements Consultable {

    // Atributos del FiltroUltimaVotacion

    /**
     * Fecha y hora mínima desde la cual se filtrarán las muestras según su última votación.
     */
    private LocalDateTime fechaMinima;



    // Constructores de la clase FiltroUltimaVotacion

    /**
     * Constructor de la clase FiltroUltimaVotacion.
     * @param fechaMinima Fecha y hora mínima desde la cual se filtrarán las muestras según su última votación.
     */
    public FiltroUltimaVotacion(LocalDateTime fechaMinima) {
        this.fechaMinima = fechaMinima;
    }



    // Métodos de clase

    /**
     * Metodo que filtra las muestras según la fecha de la última votación.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por la fecha de la última votación.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras.stream()
                .filter(m -> m.getFechaUltimaVotacion() != null && m.getFechaUltimaVotacion().isAfter(fechaMinima))
                .toList();
    }


}
