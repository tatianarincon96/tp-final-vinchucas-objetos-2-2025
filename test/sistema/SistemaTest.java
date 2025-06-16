package sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import sistema.busquedasDeMuestras.Consultable;
import ubicacion.Ubicacion;
import usuarios.NivelState;
import usuarios.Usuario;
import zonaDeCobertura.ZonaDeCobertura;

public class SistemaTest {

	Sistema sistema;

	@BeforeEach
	void setUp() {
		sistema = new Sistema();

	}

	@Test
	public void actualizarNivelDeUsuariosLlamaUpdateNivel() {

		Muestra muestra1 = mock(Muestra.class);
		Muestra muestra2 = mock(Muestra.class);

		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		NivelState nivel1 = mock(NivelState.class);
		NivelState nivel2 = mock(NivelState.class);

		when(muestra1.getUsuario()).thenReturn(usuario1);
		when(usuario1.getNivel()).thenReturn(nivel1);

		when(muestra2.getUsuario()).thenReturn(usuario2);
		when(usuario2.getNivel()).thenReturn(nivel2);

		sistema.agregarNuevaMuestra(muestra1);
		sistema.agregarNuevaMuestra(muestra2);

		sistema.actualizarNivelDeTodosLosUsuarios();

		verify(nivel1).updateNivel(usuario1);
		verify(nivel2).updateNivel(usuario2);
	}

	@Test
	public void muestraSeAgregaALasZonasDeCoberturaQueCorresponden() {
		Sistema sistema = new Sistema();

		Muestra muestra = mock(Muestra.class);
		ZonaDeCobertura zona1 = mock(ZonaDeCobertura.class);
		ZonaDeCobertura zona2 = mock(ZonaDeCobertura.class);
		Ubicacion ubicacion = mock(Ubicacion.class);

		when(muestra.getUbicacion()).thenReturn(ubicacion);
		when(ubicacion.enZona(zona1)).thenReturn(true);
		when(ubicacion.enZona(zona2)).thenReturn(false);

		sistema.agregarLaNuevaZonaDeCobertura(zona1);
		sistema.agregarLaNuevaZonaDeCobertura(zona2);

		sistema.agregarNuevaMuestra(muestra);

		verify(zona1).agregarNuevaMuestra(muestra);
		verify(zona2, never()).agregarNuevaMuestra(muestra);
	}

	@Test
	public void filtrarLasMuestrasPorAplicaElFiltroDeConsulta() {

		Muestra muestra1 = mock(Muestra.class);
		Muestra muestra2 = mock(Muestra.class);

		sistema.agregarNuevaMuestra(muestra1);
		sistema.agregarNuevaMuestra(muestra2);

		Consultable consulta = mock(Consultable.class);

		List<Muestra> resultadoEsperado = List.of(muestra1);
		when(consulta.filtrarLasMuestras(anyList())).thenReturn(resultadoEsperado);

		List<Muestra> resultado = sistema.filtrarLasMuestrasPor(consulta);

		assertEquals(resultadoEsperado, resultado);
	}

}
