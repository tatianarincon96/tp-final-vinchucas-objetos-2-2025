package muestra;

import opiniones.TipoDeOpinion;
import usuarios.Usuario;

public class MuestraVerificada extends EstadoDeMuestra{

	private TipoDeOpinion resultadoFinal;
	
	public MuestraVerificada(TipoDeOpinion resultadoActual) {
		this.resultadoFinal = resultadoActual;
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
