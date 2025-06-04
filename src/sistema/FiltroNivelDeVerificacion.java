package sistema;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroNivelDeVerificacion implements Filtro {

    //------- Constructores de la clase FiltroNivelDeVerificacion -------

    /**
     * Constructor de la clase FiltroNivelDeVerificacion.
     */
    public FiltroNivelDeVerificacion(LocalDateTime desde, LocalDateTime hasta) {}

    //------- Métodos de clase -------
    /**
     * Metodo que filtra las muestras según el criterio de nivel de verificación
     * @param muestras Lista de muestras a filtrar.
     */
    @Override
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {}
}
