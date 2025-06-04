package usuarios;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import muestra.Muestra;
import opiniones.Opinion;
import sistema.Sistema;
import ubicacion.Ubicacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Usuario {

    //------- Atributos del Usuario -------

    private UUID id;
    private String nombre;
    private Nivel nivelDeUsuario;
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
        this.nivelDeUsuario = Nivel.BASICO;
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
        this.nivelDeUsuario = Nivel.BASICO;
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
    public void registrarMuestra(EspecieVinchuca especie, Ubicacion ubicacion, List<Foto> fotos) {
        Muestra nuevaMuestra = new Muestra(especie, ubicacion, fotos, this);
        this.muestrasCreadas.add(nuevaMuestra);
        sistema.agregarNuevaMuestra(nuevaMuestra);
        this.updateNivel();
    }

    /**
     * Registra una nueva opinión sobre una muestra.
     * @param muestra Muestra sobre la cual se emite la opinión.
     * @param opinion Opinión emitida por el usuario.
     */
    public void opinar(Muestra muestra, Opinion opinion) {
        if (!muestrasCreadas.contains(muestra)) {
            muestra.agregarOpinionDe(this, opinion);
            this.opinionesHechas.add(opinion);
            this.updateNivel();
        } else {
            throw new IllegalArgumentException("No se puede opinar sobre una muestra que ha sido creada por el usuario.");
        }
    }

    /**
     * Actualiza el nivel del usuario de básico a experto, de acuerdo a la cantidad de muestras cargadas y opiniones
     * realizadas en los últimos 30 días.
     */
    public void updateNivel() {
        LocalDateTime ultimos30Dias = LocalDateTime.now().minusDays(30);
        int cantidadDeMuestrasCargadas = this.muestrasCreadas.stream()
                .filter(m -> m.getFechaDeCreacion().isAfter(ultimos30Dias))
                .toList().size();
        int cantidadDeOpinionesHechas = this.opinionesHechas.stream()
                .filter(o -> o.getFechaOpinada().isAfter(ultimos30Dias))
                .toList().size();
        this.nivelDeUsuario = cantidadDeMuestrasCargadas > 10 && cantidadDeOpinionesHechas > 20 ?
            Nivel.EXPERTO :
            Nivel.BASICO;
    }

    //------- Getters y Setters -------

    /**
     * Getter que retorna el nivel del usuario.
     * @return Nivel del usuario.
     */
    public Nivel getNivel() {
        return nivelDeUsuario;
    }

	public boolean esExperto() {
		return this.nivelDeUsuario.esExperto();
	}
}
