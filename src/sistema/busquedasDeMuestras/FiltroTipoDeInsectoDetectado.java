package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroTipoDeInsectoDetectado implements Consultable {

    //------- Atributos del FiltroTipoDeInsectoDetectado -------

    //------- Constructores de la clase FiltroTipoDeInsectoDetectado -------

    /**
     * Constructor de la clase FiltroTipoDeInsectoDetectado.
     */
    public FiltroTipoDeInsectoDetectado(LocalDateTime desde, LocalDateTime hasta) {}

    //------- Métodos de clase -------
    /**
     * Metodo que filtra las muestras según el criterio de tipo de insecto detectado.
     * @param muestras Lista de muestras a filtrar.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras;// todo
    }
}
