package muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import opiniones.TipoDeOpinion;


public class VerificadaMuestra {
	private Muestra muestra;
	private TipoDeOpinion resultado;
	private EstadoDeMuestra estado;

	@BeforeEach
	public void setUp() throws Exception {
		this.resultado = TipoDeOpinion.CHINCHE_FOLIADA;
		this.estado = new MuestraVerificada(this.resultado);
		this.muestra = mock(Muestra.class);
		
	}
		@Test
		public void siEstaVerificadaNoPuedeCambiarDeEstado() {
			assertNull(this.estado.actualizarSiAplica(muestra));
		}
		
		@Test
		public void resultadoFinalEsElDado() {
			assertEquals(this.estado.resultadoActual(muestra), this.resultado);
		}
		
		
	
		
}
