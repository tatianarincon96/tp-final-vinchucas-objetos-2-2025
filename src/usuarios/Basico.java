package usuarios;

import java.util.List;

public class Basico extends NivelState {

    @Override
    protected boolean debeCambiarNivel(Usuario usuario) {
        List<Integer> cantidades = obtainMuestrasYOpinionesEnLosUltimosXDias(usuario, 30);
        return cantidades.get(0) > 10 && cantidades.get(1) > 20;
    }

    @Override
    protected NivelState nuevoNivel() {
        return new Experto();
    }
}
