package muestra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import especieVinchuca.EspecieVinchuca;
import opiniones.Opinion;
import ubicacion.Ubicacion;
import usuarios.Nivel;
import usuarios.Usuario;

public class MuestraTest {

	private Muestra muestra;
	private Usuario usuarioExperto;
	private Usuario usuarioBasico;

	@BeforeEach
	public void setUp() {
		Ubicacion ubicacion = mock(Ubicacion.class);
		EspecieVinchuca especie = mock(EspecieVinchuca.class);
//		Usuario dueño
		Usuario usuario = mock(Usuario.class);
//		Usuario experto
		this.usuarioExperto = mock(Usuario.class);
		when(usuarioExperto.getNivel()).thenReturn(Nivel.EXPERTO);

// 		Usuario basico
		this.usuarioBasico = mock(Usuario.class);
		when(usuarioBasico.getNivel()).thenReturn(Nivel.BASICO);
		
//		Muestra
		this.muestra = new Muestra(especie, ubicacion, new ArrayList<>(), usuario);
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
		Usuario creador = mock(Usuario.class);
		Ubicacion ubicacion = mock(Ubicacion.class);
		EspecieVinchuca especie = mock(EspecieVinchuca.class);
		Muestra muestraDelUsuario = new Muestra(especie, ubicacion, new ArrayList<>(), creador);

	}

//	MUESTRA VERIFICADA

}