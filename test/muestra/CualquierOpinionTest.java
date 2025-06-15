package muestra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuarios.Usuario;

public class CualquierOpinionTest {

	private Muestra muestra;
	private EstadoDeMuestra estado;

	@BeforeEach
	public void setUp() throws Exception {
		this.estado = new CualquierOpinion();
		this.muestra = mock(Muestra.class);
		when(muestra.getEstado()).thenReturn(estado);

	}

	@Test
	public void cuandoUsuarioExpertoVotaLaMuestraCambiaDeEstado() throws Exception {

		when(this.muestra.tieneOpinionesDeExperto()).thenReturn(true);
		assertEquals(this.muestra.getEstado().actualizarSiAplica(muestra).getClass(), SoloOpinionesExperto.class);

	}

	@Test
	public void cualquierUsuarioPuedeVotarMuestraEnEstadoBasico() throws Exception {
		assertTrue(this.estado.puedeOpinar(mock(Usuario.class)));
	}

}
