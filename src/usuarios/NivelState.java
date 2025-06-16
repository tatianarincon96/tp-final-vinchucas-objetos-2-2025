package usuarios;

import java.time.LocalDateTime;
import java.util.List;

public abstract class NivelState {
    public abstract void updateNivel(Usuario usuario);
    protected final List<Integer> obtainMuestrasYOpiniones(Usuario usuario) {
        LocalDateTime ultimos30Dias = LocalDateTime.now().minusDays(30);
        int cantidadDeMuestrasCargadas = usuario.getMuestrasCreadas().stream()
                .filter(m -> m.getFechaDeCreacion().isAfter(ultimos30Dias))
                .toList().size();
        int cantidadDeOpinionesHechas = usuario.getOpinionesHechas().stream()
                .filter(o -> o.getFechaOpinada().isAfter(ultimos30Dias))
                .toList().size();
        return List.of(
                cantidadDeMuestrasCargadas,
                cantidadDeOpinionesHechas
        );
    }
    public boolean esExperto() {
        return false;
    };
}
