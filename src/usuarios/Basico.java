package usuarios;

import java.util.List;

public class Basico extends NivelState {

    @Override
    public void updateNivel(Usuario usuario) {
        List<Integer> cantidadMuestrasYOpiniones = obtainMuestrasYOpiniones(usuario);
        if (cantidadMuestrasYOpiniones.get(0) > 10 && cantidadMuestrasYOpiniones.get(1) > 20) {
            usuario.setNivelDeUsuario(new Experto());
        }
    }
}
