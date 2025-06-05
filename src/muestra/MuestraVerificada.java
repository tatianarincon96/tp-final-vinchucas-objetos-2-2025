package muestra;

import opiniones.TipoDeOpinion;
import usuarios.Usuario;

public class MuestraVerificada extends EstadoDeMuestra{

	private TipoDeOpinion resultadoFinal;
	
	public MuestraVerificada(Muestra muestra) {
		this.resultadoFinal = this.resultadoActual(muestra);
	}
	@Override
	public boolean puedeOpinar(Usuario usuario) {
		return false;
	}

	@Override
	public boolean estaVerificada() {
		return true;
	}

	@Override
	public EstadoDeMuestra actualizarSiAplica(Muestra muestra) {
		return null;
	}

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		return this.resultadoFinal;
	}

}
