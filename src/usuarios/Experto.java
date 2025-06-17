package usuarios;
import java.util.List;

public class Experto extends NivelState {

    @Override
    public void updateNivel(Usuario usuario) {
        List<Integer> cantidadMuestrasYOpiniones = obtainMuestrasYOpiniones(usuario);
        if (cantidadMuestrasYOpiniones.get(0) < 10 || cantidadMuestrasYOpiniones.get(1) < 20) {
            usuario.setNivelDeUsuario(new Basico());
        }
    }

    @Override
    public boolean esExperto() {
        return true;
    }
}
