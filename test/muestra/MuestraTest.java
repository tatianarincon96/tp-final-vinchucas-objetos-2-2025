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
	
	@BeforeEach
	public void setUp() {
		Ubicacion ubicacion = mock(Ubicacion.class);
		EspecieVinchuca especie = mock(EspecieVinchuca.class);
		Usuario usuario = mock(Usuario.class);
		
		this.muestra = new Muestra(especie, ubicacion, new ArrayList<>(), usuario);
	}
	@Test 
	public void usuarioPuedeOpinarSobreMuestraEnEstadoBasico(){
			Usuario usuario = mock(Usuario.class);
			when(usuario.getNivel()).thenReturn(Nivel.BASICO);
			Opinion opinion = mock(Opinion.class);
			assertDoesNotThrow(() -> muestra.agregarOpinionDe(usuario, opinion));
		 	assertEquals(opinion, muestra.historialDeOpiniones().get(usuario));
	}
	
	@Test
	public void unUsuarioNoPuedeVotarDosVeces(){
		Usuario usuario = mock(Usuario.class);
		when(usuario.getNivel()).thenReturn(Nivel.BASICO);
		Opinion opinion = mock(Opinion.class);
		assertDoesNotThrow(() -> muestra.agregarOpinionDe(usuario, opinion));
		assertThrows(Exception.class, () -> muestra.agregarOpinionDe(usuario, opinion));
	 	
	}
	@Test
	public void unUsuarioNoPuedeVotarSuPropiaMuestra(){
	    Usuario creador = mock(Usuario.class);

	    Ubicacion ubicacion = mock(Ubicacion.class);
	    EspecieVinchuca especie = mock(EspecieVinchuca.class);
	    Muestra muestraDelUsuario = new Muestra(especie, ubicacion, new ArrayList<>(), creador);

	    Opinion opinion = mock(Opinion.class);

	    assertThrows(Exception.class, () -> {
	        muestraDelUsuario.agregarOpinionDe(creador, opinion);
	    });
	}
	
	
}