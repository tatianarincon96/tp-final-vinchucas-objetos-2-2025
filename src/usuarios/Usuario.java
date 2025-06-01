package usuarios;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import muestra.Muestra;
import opiniones.Opinion;

import java.util.List;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nombre;
    private Nivel nivelDeUsuario;
    private List<Muestra> muestrasCreadas;
    private List<Opinion> opinionesHechas;
    private boolean esExpertoExterno;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(String nombre, boolean esExpertoExterno) {
        this.nombre = nombre;
        this.esExpertoExterno = esExpertoExterno;
    }

    public void registrarMuestra(EspecieVinchuca especie, Ubicacion ubicacion, List<Foto> fotos) {}
    public void opinar(Muestra muestra, Opinion opinion) {}
    public void updateNivel() {}

    public Nivel getNivel() {
        return nivelDeUsuario;
    }
}
