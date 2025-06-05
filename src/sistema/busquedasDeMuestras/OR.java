package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.util.List;

/**
 * Clase que representa una operación AND entre dos consultas.
 */
public class OR implements Consultable {

    // Atributos de la clase OR

    /**
     * Primera consulta a combinar en la operación OR.
     */
    private Consultable primeraConsulta;

    /**
     * Segunda consulta a combinar en la operación OR.
     */
    private Consultable segundaConsulta;



    // Constructores de la clase OR

    /**
     * Constructor de la clase OR.
     * @param primeraConsulta Primera consulta a combinar.
     * @param segundaConsulta Segunda consulta a combinar.
     */
    public OR(Consultable primeraConsulta, Consultable segundaConsulta) {
        this.primeraConsulta = primeraConsulta;
        this.segundaConsulta = segundaConsulta;
    }



    // Métodos de la clase OR

    /**
     * Metodo que filtra las muestras según las dos consultas OR.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por ambas consultas.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        List<Muestra> resultadoFiltro1 = primeraConsulta.filtrarLasMuestras(muestras);
        List<Muestra> resultadoFiltro2 = segundaConsulta.filtrarLasMuestras(muestras);

        resultadoFiltro1.addAll(resultadoFiltro2);
        return resultadoFiltro1;
    }
}
