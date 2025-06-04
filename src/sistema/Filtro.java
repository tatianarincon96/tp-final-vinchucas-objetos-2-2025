package sistema;

import muestra.Muestra;

import java.util.List;

public interface Filtro {
    List<Muestra> filtrarLasMuestras(List<Muestra> muestras);
}
