package usuarios;

import java.time.LocalDateTime;
import java.util.List;

public abstract class NivelState {
    public final void updateNivel(Usuario usuario) {
        if (debeCambiarNivel(usuario)) {
            usuario.setNivelDeUsuario(nuevoNivel());
            hook1();
        }
        hook2();
    }

    protected abstract boolean debeCambiarNivel(Usuario usuario);
    protected abstract NivelState nuevoNivel();
    protected void hook1() {};
    protected void hook2() {};


    protected final List<Integer> obtainMuestrasYOpinionesEnLosUltimosXDias(Usuario usuario, int cantidadDeDias) {
        LocalDateTime fechaHaceXDias = LocalDateTime.now().minusDays(cantidadDeDias);
        int cantidadDeMuestrasCargadas = usuario.getMuestrasCreadas().stream()
                .filter(m -> m.getFechaDeCreacion().isAfter(fechaHaceXDias))
                .toList().size();
        int cantidadDeOpinionesHechas = usuario.getOpinionesHechas().stream()
                .filter(o -> o.getFechaOpinada().isAfter(fechaHaceXDias))
                .toList().size();
        return List.of(
                cantidadDeMuestrasCargadas,
                cantidadDeOpinionesHechas
        );
    }

    public boolean esExperto() { return false; }
}
