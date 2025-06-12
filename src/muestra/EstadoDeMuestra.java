package muestra;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import usuarios.Usuario;

public abstract class EstadoDeMuestra {

	public boolean puedeOpinar(Usuario usuario) {
		return true;
	}

	public boolean estaVerificada() {
		return false;
	}

	public abstract TipoDeOpinion resultadoActual(Muestra muestra);

	public abstract EstadoDeMuestra actualizarSiAplica(Muestra muestra);

	public TipoDeOpinion obtenerTipoMasVotado(HashMap<Usuario, Opinion> opinionesAFiltrar) {
		Map<TipoDeOpinion, Integer> opiniones = new HashMap<>();

		for (Opinion opinion : opinionesAFiltrar.values()) {
			TipoDeOpinion tipo = opinion.getTipoDeOpinion();
			opiniones.put(tipo, opiniones.getOrDefault(tipo, 0) + 1);
		}

		if (opiniones.isEmpty()) {
			return TipoDeOpinion.NINGUNA;
		}

		int maxCantidad = Collections.max(opiniones.values());
		List<TipoDeOpinion> empatadas = opiniones.entrySet().stream().filter(e -> e.getValue() == maxCantidad)
				.map(Map.Entry::getKey).toList();

		if (empatadas.size() > 1) {
			return TipoDeOpinion.NINGUNA;
		} else {
			return empatadas.get(0);
		}
	}
}
