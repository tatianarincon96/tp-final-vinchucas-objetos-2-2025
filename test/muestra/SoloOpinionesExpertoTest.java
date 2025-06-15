package muestra;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import ubicacion.Ubicacion;
import usuarios.Basico;
import usuarios.Experto;
import usuarios.Usuario;

public class SoloOpinionesExpertoTest {
	private Muestra muestra;
	private Usuario usuarioExperto;
	private Usuario usuarioExperto2;
	private Usuario usuarioBasico;;
	private EspecieVinchuca especie;

	@BeforeEach
	public void setUp() throws Exception {
		Ubicacion ubicacion = mock(Ubicacion.class);
		this.especie = EspecieVinchuca.VINCHUCA_SORDIDA;
//		Usuario dueño
		Usuario usuario = mock(Usuario.class);
		when(usuario.getNivel()).thenReturn(new Basico());
		when(usuario.esExperto()).thenReturn(false);
//		Usuario experto
		this.usuarioExperto = mock(Usuario.class);
		when(usuarioExperto.getNivel()).thenReturn(new Experto());
		when(usuarioExperto.esExperto()).thenReturn(true);

//		Usuario experto 2
		this.usuarioExperto2 = mock(Usuario.class);
		when(usuarioExperto2.getNivel()).thenReturn(new Experto());
		when(usuarioExperto2.esExperto()).thenReturn(true);
// 		Usuario basico
		this.usuarioBasico = mock(Usuario.class);
		when(usuarioBasico.getNivel()).thenReturn(new Basico());
		when(usuarioBasico.esExperto()).thenReturn(false);
		
// 		Usuario basico 2
//		Muestra
		Foto fotoMock = mock(Foto.class);
		List<Foto> fotos = new ArrayList<>();
		fotos.add(fotoMock);
		this.muestra = new Muestra(especie, ubicacion, fotos, usuario);
		}
	@Test
	public void cuandoDosExpertosCoincidenLaMuestraSeVerifica() throws Exception {
		Opinion opinion1 = mock(Opinion.class);
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		Opinion opinion2 = mock(Opinion.class);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);

		muestra.agregarOpinionDe(usuarioExperto, opinion1);
		muestra.agregarOpinionDe(usuarioExperto2, opinion2);
		assertThrows(Exception.class, () -> this.muestra.agregarOpinionDe(usuarioBasico, opinion2));
		assertTrue(muestra.estaVerificada());

	}
	@Test
	public void cuandoDosExpertosnoCoincidenLaMuestranoSeVerifica() throws Exception {
		Opinion opinion1 = mock(Opinion.class);
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		Opinion opinion2 = mock(Opinion.class);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);

		muestra.agregarOpinionDe(usuarioExperto, opinion1);
		muestra.agregarOpinionDe(usuarioExperto2, opinion2);
		assertThrows(Exception.class, () -> this.muestra.agregarOpinionDe(usuarioBasico, opinion2));
		assertFalse(muestra.estaVerificada());

	}
}
