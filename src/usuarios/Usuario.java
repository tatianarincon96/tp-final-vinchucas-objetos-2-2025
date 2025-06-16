package usuarios;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import muestra.Muestra;
import opiniones.Opinion;
import sistema.Sistema;
import ubicacion.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {

    //------- Atributos del Usuario -------

    private UUID id;
    private String nombre;
    private NivelState nivelDeUsuario;
    private List<Muestra> muestrasCreadas;
    private List<Opinion> opinionesHechas;
    private boolean esExpertoExterno;
    private Sistema sistema;

    //------- Constructores de la clase Usuario -------

    /**
     * Constructor de la clase Usuario.
     * @param nombre nombre del usuario, no puede ser nulo ni vacío.
     */
    public Usuario(String nombre) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.nivelDeUsuario = new Basico();
        this.muestrasCreadas = new ArrayList<>();
        this.opinionesHechas = new ArrayList<>();
        this.esExpertoExterno = false;
        this.sistema = new Sistema();
    }

    /**
     * Constructor de la clase Ubicacion.
     * @param nombre nombre del usuario, no puede ser nulo ni vacío.
     * @param esExpertoExterno indica si el usuario es un experto externo (true) o no (false).
     */
    public Usuario(String nombre, boolean esExpertoExterno) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.nivelDeUsuario = esExpertoExterno ? new Experto() : new Basico();
        this.muestrasCreadas = new ArrayList<>();
        this.opinionesHechas = new ArrayList<>();
        this.esExpertoExterno = esExpertoExterno;
        this.sistema = new Sistema();
    }

    //------- Métodos de clase -------

    /**
     * Registra una nueva muestra creada por el usuario.
     * @param especie Especie de la vinchuca observada.
     * @param ubicacion Ubicación geográfica donde se tomó la muestra.
     * @param fotos Lista de fotos asociadas a la muestra.
     */
    public void registrarMuestra(EspecieVinchuca especie, Ubicacion ubicacion, List<Foto> fotos) throws Exception {
        if (!esExpertoExterno) {
            nivelDeUsuario.updateNivel(this);
        }
        Muestra nuevaMuestra = new Muestra(especie, ubicacion, fotos, this);
        this.muestrasCreadas.add(nuevaMuestra);
        sistema.agregarNuevaMuestra(nuevaMuestra);
    }

    /**
     * Registra una nueva opinión sobre una muestra.
     *
     * @param muestra Muestra sobre la cual se emite la opinión.
     * @param opinion Opinión emitida por el usuario.
     */
    public void opinar(Muestra muestra, Opinion opinion) throws Exception {
        if (!esExpertoExterno) {
            nivelDeUsuario.updateNivel(this);
        }
        muestra.agregarOpinionDe(this, opinion);
        this.opinionesHechas.add(opinion);
    }

    //------- Getters y Setters -------

    /**
     * Getter que retorna el nivel del usuario.
     * @return Nivel del usuario.
     */
    public NivelState getNivel() {
        return nivelDeUsuario;
    }

    /**
     * Getter que retorna la lista de muestras creadas por el usuario.
     * @return Lista de muestra creadas por el usuario.
     */
    public List<Muestra> getMuestrasCreadas() {
        return muestrasCreadas;
    }

    /**
     * Getter que retorna la lista de opiniones hechas por el usuario.
     * @return Lista de opiniones hechas por el usuario.
     */
    public List<Opinion> getOpinionesHechas() {
        return opinionesHechas;
    }

    /**
     * Setter que establece el nivel de usuario.
     * @param nivelDeUsuario Nuevo nivel de usuario, no puede ser nulo.
     */
    public void setNivelDeUsuario(NivelState nivelDeUsuario) {
        this.nivelDeUsuario = nivelDeUsuario;
    }

    /**
     * Getter que retorna si un usuario es experto externo o no
     * @return Booleano que indica si el usuario es experto (true) o no (false).
     */
	public boolean esExpertoExterno() {
		return this.esExpertoExterno;
	}

    /**
     * Getter que retorna si un usuario tiene nivel Experto
     * @return Booleano que indica si el usuario es experto (true) o no (false).
     */
    public boolean esExperto() {
        return this.nivelDeUsuario.esExperto();
    }
}
