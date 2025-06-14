package muestra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import ubicacion.Ubicacion;
import usuarios.Basico;
import usuarios.Experto;
import usuarios.Nivel;
import usuarios.Usuario;

public class MuestraTest {

	private Muestra muestra;
	private Usuario usuarioExperto;
	private Usuario usuarioExperto2;
	private Usuario usuarioBasico;
	private EspecieVinchuca especie;

	@BeforeEach
	public void setUp() throws Exception {
		Ubicacion ubicacion = mock(Ubicacion.class);
		this.especie = mock(EspecieVinchuca.class);
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

//		Muestra
		Foto fotoMock = mock(Foto.class);
		List<Foto> fotos = new ArrayList<>();
		fotos.add(fotoMock);
		this.muestra = new Muestra(especie, ubicacion, fotos, usuario);
		}

	@Test
	public void usuarioPuedeOpinarSobreMuestraEnEstadoBasico() {
		Opinion opinion = mock(Opinion.class);
		assertDoesNotThrow(() -> muestra.agregarOpinionDe(this.usuarioBasico, opinion));
		assertEquals(opinion, muestra.historialDeOpiniones().get(this.usuarioBasico));
	}

	@Test
	public void unUsuarioNoPuedeVotarDosVeces() {

		Opinion opinion = mock(Opinion.class);
		assertDoesNotThrow(() -> muestra.agregarOpinionDe(this.usuarioBasico, opinion));
		assertThrows(Exception.class, () -> muestra.agregarOpinionDe(this.usuarioBasico, opinion));

	}

	@Test
	public void unUsuarioNoPuedeVotarSuPropiaMuestra() {

		Opinion opinion = mock(Opinion.class);

		assertThrows(Exception.class, () -> {
			this.muestra.agregarOpinionDe(this.muestra.getUsuario(), opinion);
		});
	}

// 
	@Test
	public void cualquierUsuarioPuedeVotarMuestraEnEstadoBasico() throws Exception {
		Opinion opinion1 = mock(Opinion.class);
		Opinion opinion2 = mock(Opinion.class);

		assertDoesNotThrow(() -> this.muestra.agregarOpinionDe(this.usuarioBasico, opinion1));
		assertDoesNotThrow(() -> this.muestra.agregarOpinionDe(this.usuarioExperto, opinion2));

		Map<Usuario, Opinion> historial = this.muestra.historialDeOpiniones();
		assertEquals(opinion1, historial.get(this.usuarioBasico));
		assertEquals(opinion2, historial.get(this.usuarioExperto));
	}

	@Test
	public void cuandoUsuarioExpertoVotaLaMuestraCambiaDeEstado() throws Exception {
		Opinion opinion1 = mock(Opinion.class);
		when(opinion1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		Opinion opinion2 = mock(Opinion.class);
		when(opinion2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);

		muestra.agregarOpinionDe(usuarioExperto, opinion1);
		assertTrue(this.muestra.getEstado().puedeOpinar(usuarioExperto));
		assertThrows(Exception.class, () -> this.muestra.agregarOpinionDe(usuarioBasico, opinion2));
		assertFalse(muestra.estaVerificada());

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
	public void opinionesBasicasYExpertasSeRegistranCorrectamente() throws Exception {
		Opinion opinionBasico = mock(Opinion.class);
		Opinion opinionExperto = mock(Opinion.class);

		muestra.agregarOpinionDe(usuarioBasico, opinionBasico);
		muestra.agregarOpinionDe(usuarioExperto, opinionExperto);

		assertEquals(opinionBasico, muestra.getOpinionesBasicas().get(usuarioBasico));
		assertEquals(opinionExperto, muestra.getOpinionesExpertas().get(usuarioExperto));
	}

	@Test
	public void muestraTieneUbicacionYFechaDeUltimaVotacion() {
		Ubicacion ubicacion = muestra.getUbicacion();
		LocalDateTime fecha = muestra.getFechaUltimaVotacion();

		assertEquals(ubicacion, muestra.getUbicacion());
		assertTrue(fecha != null && fecha.isBefore(LocalDateTime.now().plusSeconds(1)));
	}

	@Test
	public void muestraTieneFechaDeCreacionYPuedeSerModificada() {
		LocalDateTime fechaOriginal = muestra.getFechaDeCreacion();
		assertTrue(fechaOriginal != null && fechaOriginal.isBefore(LocalDateTime.now().plusSeconds(1)));

		LocalDateTime nuevaFecha = LocalDateTime.of(2020, 1, 1, 0, 0);
		muestra.setFechaDeCreacion(nuevaFecha);
		assertEquals(nuevaFecha, muestra.getFechaDeCreacion());
	}
	
	@Test
	public void imaganesDeMuestraTest() {
		Foto foto = mock(Foto.class);
		assertEquals(muestra.getFotosAdjuntadas().size(), 1);
		muestra.agregarFoto(foto);
		assertTrue(muestra.getFotosAdjuntadas().contains(foto));
		assertEquals(muestra.getFotosAdjuntadas().size(), 2);
		muestra.borrarFoto(foto);
		assertFalse(muestra.getFotosAdjuntadas().contains(foto));
	}
	
	@Test
	public void unaMuestraNoPuedeCrearseSinImagenes() throws Exception {
		Ubicacion ubicacion = mock(Ubicacion.class);
		EspecieVinchuca especie = mock(EspecieVinchuca.class);
		assertThrows(Exception.class,() -> new Muestra(especie, ubicacion, new ArrayList<Foto>(), this.usuarioBasico));
		assertThrows(Exception.class,() -> new Muestra(especie, ubicacion, null, this.usuarioBasico));

	}
	
	@Test
	public void unaMuestraConoceLaEspecieConLaQueSeCreo() {
		assertEquals(this.especie, this.muestra.getTipoInsecto());
	}

}