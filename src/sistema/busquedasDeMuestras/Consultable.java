package sistema.busquedasDeMuestras;

import muestra.Muestra;

import java.util.List;

public interface Consultable {

    List<Muestra> filtrarLasMuestras(List<Muestra> muestrasAFiltrar);
}
