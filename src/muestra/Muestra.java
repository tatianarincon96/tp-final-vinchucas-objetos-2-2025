package muestra;

import java.time.LocalDateTime;
import java.util.*;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import ubicacion.Ubicacion;
import usuarios.Usuario;

public class Muestra {
//    private UUID id;
	private LocalDateTime fechaDeCreacion;
	private Ubicacion ubicacion;
	private List<Foto> fotosAdjuntadas;
	private Usuario usuarioAutor;
	private EstadoDeMuestra estado;
	private HashMap<Usuario, Opinion> opinionesExpertas;
	private HashMap<Usuario, Opinion> opinionesBasicas;

	public Muestra(EspecieVinchuca tipoInsecto, Ubicacion ubicacion, List<Foto> fotosAdjuntadas, Usuario usuarioAutor) {
//        this.id = UUID.randomUUID();
		this.ubicacion = ubicacion;
		this.setFotosAdjuntadas(fotosAdjuntadas != null ? fotosAdjuntadas : new ArrayList<>());
		this.usuarioAutor = usuarioAutor;
		this.setFechaDeCreacion(LocalDateTime.now());
		this.estado = new CualquierOpinion();

	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public boolean tieneOpinionesDeExperto() {
		return this.opinionesExpertas.size() > 0;
	}
	
	private boolean usuarioYaVoto(Usuario usuario) {
		return this.opinionesBasicas.containsKey(usuario) || this.opinionesExpertas.containsKey(usuario);
	}
	public void agregarOpinion(Usuario usuario, Opinion opinion) throws Exception {
		if (this.estado.puedeOpinar(usuario) && !this.usuarioYaVoto(usuario)) {
			if (usuario.esExperto()) {
				this.opinionesExpertas.put(usuario, opinion);
				this.estado = this.estado.actualizarSiAplica(this);
			} else {
				this.opinionesBasicas.put(usuario, opinion);
			}
		}else {
			throw new Exception("El usuario no puede opinar sobre esta muestra");
		}

	}

	public boolean estaVerificada() {
		return this.estado.estaVerificada();
	}

	public TipoDeOpinion resultadoActual() {
		return this.estado.resultadoActual(this);
	}

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public List<Foto> getFotosAdjuntadas() {
		return fotosAdjuntadas;
	}

	public HashMap<Usuario, Opinion> getOpinionesExpertas() {
		return this.opinionesExpertas;
	}

	public HashMap<Usuario, Opinion> getOpinionesBasicas() {
		return this.opinionesBasicas;
	}

	public void setFotosAdjuntadas(List<Foto> fotosAdjuntadas) {
		this.fotosAdjuntadas = fotosAdjuntadas;
	}

	public int cantidadDeExpertosQueOpinan(TipoDeOpinion resultadoActual) {
		return (int) this.opinionesExpertas.values().stream()
				.filter(op -> op.getTipoDeOpinion().equals(resultadoActual)).count();
	}

	public HashMap<Usuario, Opinion> historialDeOpiniones() {
		HashMap<Usuario, Opinion> historial = new HashMap<>();
		historial.putAll(opinionesBasicas);
		historial.putAll(opinionesExpertas);
		return historial;

	}
}
