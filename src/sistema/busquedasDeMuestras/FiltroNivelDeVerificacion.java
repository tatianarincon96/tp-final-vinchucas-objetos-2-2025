package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un filtro de nivel de verificación de muestras.
 */
public class FiltroNivelDeVerificacion implements Consultable {

    // Atributos de la clase FiltroNivelDeVerificacion

    /**
     * Indica si la muestra está verificada o no.
     */
    private boolean estaVerificada;



    // Constructores de la clase FiltroNivelDeVerificacion

    /**
     * Constructor de la clase FiltroNivelDeVerificacion.
     * @param estaVerificada Indica si la muestra está verificada o no.
     */
    public FiltroNivelDeVerificacion(boolean estaVerificada) {
        this.estaVerificada = estaVerificada;
    }



    // Métodos de la clase FiltroNivelDeVerificacion

    /**
     * Metodo que filtra las muestras según su nivel de verificación.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por su nivel de verificación.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        return muestras.stream()
                .filter(m -> m.estaVerificada() == estaVerificada)
                .toList();
    }


}
