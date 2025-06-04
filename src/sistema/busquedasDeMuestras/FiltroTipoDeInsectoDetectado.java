package sistema.busquedasDeMuestras;

import especieVinchuca.EspecieVinchuca;
import muestra.Muestra;
import opiniones.TipoDeOpinion;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un filtro para buscar muestras según el tipo de insecto detectado.
 */
public class FiltroTipoDeInsectoDetectado implements Consultable {

    // Atributos del FiltroTipoDeInsectoDetectado

    /**
     * Tipo de insecto detectado en la muestra.
     */
    private EspecieVinchuca insectoDetectado;



    // Constructores de la clase FiltroTipoDeInsectoDetectado

    /**
     * Constructor de la clase FiltroTipoDeInsectoDetectado.
     * @param insectoDetectado Tipo de insecto detectado en la muestra.
     */
    public FiltroTipoDeInsectoDetectado(EspecieVinchuca insectoDetectado) {
        this.insectoDetectado = insectoDetectado;
    }



    // Métodos de la clase FiltroTipoDeInsectoDetectado

    /**
     * Metodo que filtra las muestras según el tipo de insecto detectado.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por el tipo de insecto detectado.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras.stream()
                .filter(m -> m.getTipoInsecto().equals(insectoDetectado))
                .toList();
    }


}
