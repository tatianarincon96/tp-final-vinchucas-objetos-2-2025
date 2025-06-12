package usuarios;

import java.time.LocalDateTime;

public class Basico implements NivelState {

    @Override
    public void updateNivel(Usuario usuario) {
        LocalDateTime ultimos30Dias = LocalDateTime.now().minusDays(30);
        int cantidadDeMuestrasCargadas = usuario.getMuestrasCreadas().stream()
                .filter(m -> m.getFechaDeCreacion().isAfter(ultimos30Dias))
                .toList().size();
        int cantidadDeOpinionesHechas = usuario.getOpinionesHechas().stream()
                .filter(o -> o.getFechaOpinada().isAfter(ultimos30Dias))
                .toList().size();
        if (cantidadDeMuestrasCargadas > 10 && cantidadDeOpinionesHechas > 20) {
            usuario.setNivelDeUsuario(new Experto());
        }
    }

    @Override
    public boolean esExperto() {
        return false;
    }
}
