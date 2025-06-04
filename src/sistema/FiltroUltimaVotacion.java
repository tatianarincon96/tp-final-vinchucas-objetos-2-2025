package sistema;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroUltimaVotacion implements Filtro {

    //------- Atributos del FiltroUltimaVotacion -------

    //------- Constructores de la clase FiltroUltimaVotacion -------

    /**
     * Constructor de la clase FiltroUltimaVotacion.
     */
    public FiltroUltimaVotacion(LocalDateTime desde, LocalDateTime hasta) {}

    //------- Métodos de clase -------
    /**
     * Metodo que filtra las muestras según el criterio de ultima votacion.
     * @param muestras Lista de muestras a filtrar.
     */
    @Override
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {

    }
}
