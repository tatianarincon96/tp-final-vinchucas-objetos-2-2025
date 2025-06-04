package muestra;

import opiniones.TipoDeOpinion;

public class CualquierOpinion extends EstadoDeMuestra {

	@Override
	public EstadoDeMuestra actualizarSiAplica(Muestra muestra) {
		if (muestra.tieneOpinionesDeExperto()) {
			return new SoloOpinionesExperto();
		} else {
			return this;
		}
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return this.obtenerTipoMasVotado(muestra.getOpinionesBasicas());
	}

}
