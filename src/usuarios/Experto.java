package usuarios;

import java.time.LocalDateTime;
import java.util.List;

public class Experto extends NivelState {
    private Nivel nivel;

    public Experto() {
        this.nivel = Nivel.EXPERTO;
    }

    public Nivel getNivel() {
        return nivel;
    }

    @Override
    public void updateNivel(Usuario usuario) {
        List<Integer> cantidadMuestrasYOpiniones = obtainMuestrasYOpiniones(usuario);
        if (cantidadMuestrasYOpiniones.get(0) < 10 && cantidadMuestrasYOpiniones.get(1) < 20) {
            usuario.setNivelDeUsuario(new Basico());
        }
    }

    @Override
    public boolean esExperto() {
        return true;
    }
}
