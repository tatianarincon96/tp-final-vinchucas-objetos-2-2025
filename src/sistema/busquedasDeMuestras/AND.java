package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.util.List;

/**
 * Clase que representa una operación AND entre dos consultas.
 */
public class AND implements Consultable{

    // Atributos de la clase AND

    /**
     * Primer consulta a combinar en la operación AND.
     */
    private Consultable primeraConsulta;

    /**
     * Segunda consulta a combinar en la operación AND.
     */
    private Consultable segundaConsulta;





    // Constructores de la clase AND

    /**
     * Constructor de la clase AND.
     * @param primeraConsulta Primer consulta a combinar.
     * @param segundaConsulta Segunda consulta a combinar.
     */
    public AND(Consultable primeraConsulta, Consultable segundaConsulta) {
        this.primeraConsulta = primeraConsulta;
        this.segundaConsulta = segundaConsulta;
    }




    // Metodos de la clase AND

    /**
     * Metodo que filtra las muestras según las dos consultas AND.
     * @param muestras Lista de muestras a filtrar.
     * @return Lista de muestras filtradas por ambas consultas.
     */
    public List<Muestra> filtrarLasMuestras(List<Muestra> muestras) {
        List<Muestra> resultadoFiltro1 = primeraConsulta.filtrarLasMuestras(muestras);
        return segundaConsulta.filtrarLasMuestras(resultadoFiltro1);
    }

}
