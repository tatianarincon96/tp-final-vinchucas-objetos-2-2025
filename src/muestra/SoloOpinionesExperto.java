package muestra;


import opiniones.TipoDeOpinion;
import usuarios.Usuario;

public class SoloOpinionesExperto extends EstadoDeMuestra{

	@Override
	public boolean puedeOpinar(Usuario usuario) {
		return usuario.esExperto();
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return this.obtenerTipoMasVotado(muestra.getOpinionesExpertas());
	}

	@Override
	public EstadoDeMuestra actualizarSiAplica(Muestra muestra) {
		if (muestra.cantidadDeExpertosQueOpinan(muestra.resultadoActual()) == 2) {
			return new MuestraVerificada(muestra);
		}
		else return this;
	}

}
