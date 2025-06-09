package usuarios;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import muestra.Muestra;
import opiniones.Opinion;
import sistema.Sistema;
import ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioTest {

    Usuario usuario;
    Sistema sistemaMock;

    @BeforeEach
    void setUp() {
        sistemaMock = mock(Sistema.class);
        usuario = new Usuario("Juan");
    }

    @Test
    void registrarMuestra_agregaMuestraYActualizaSistema() {
        Ubicacion ubicacion = mock(Ubicacion.class);
        Foto foto = mock(Foto.class);

        usuario.registrarMuestra(EspecieVinchuca.VINCHUCA_GUASAYANA, ubicacion, List.of(foto));

        assertEquals(1, usuario.getMuestrasCreadas().size());
    }

    @Test
    void opinar_agregaOpinionALaListaYALaMuestra() throws Exception {
        Muestra muestraMock = mock(Muestra.class);
        Opinion opinionMock = mock(Opinion.class);

        usuario.opinar(muestraMock, opinionMock);

        assertEquals(1, usuario.getOpinionesHechas().size());
        verify(muestraMock).agregarOpinionDe(usuario, opinionMock);
    }

    @Test
    void updateNivel_cambiaANivelExperto() {
        Usuario usuarioTest = new Usuario("Ana");
        // Mock de muestra con fecha reciente
        Muestra muestraMock = mock(Muestra.class);
        when(muestraMock.getFechaDeCreacion()).thenReturn(LocalDateTime.now().minusDays(1));
        // Mock de opinion con fecha reciente
        Opinion opinionMock = mock(Opinion.class);
        when(opinionMock.getFechaOpinada()).thenReturn(LocalDateTime.now().minusDays(1));

        // Agregar 11 muestras y 21 opiniones recientes
        for (int i = 0; i < 11; i++) usuarioTest.getMuestrasCreadas().add(muestraMock);
        for (int i = 0; i < 21; i++) usuarioTest.getOpinionesHechas().add(opinionMock);

        usuarioTest.updateNivel();

        assertEquals(Nivel.EXPERTO, usuarioTest.getNivel());
    }

    @Test
    void updateNivel_seMantieneBasico() {
        Usuario usuarioTest = new Usuario("Ana");
        Muestra muestraMock = mock(Muestra.class);
        when(muestraMock.getFechaDeCreacion()).thenReturn(LocalDateTime.now().minusDays(31));
        Opinion opinionMock = mock(Opinion.class);
        when(opinionMock.getFechaOpinada()).thenReturn(LocalDateTime.now().minusDays(31));

        usuarioTest.getMuestrasCreadas().add(muestraMock);
        usuarioTest.getOpinionesHechas().add(opinionMock);

        usuarioTest.updateNivel();

        assertEquals(Nivel.BASICO, usuarioTest.getNivel());
    }

    @Test
    void getNivel_devuelveNivelActual() {
        assertEquals(Nivel.BASICO, usuario.getNivel());
    }

    @Test
    void esExperto_trueSiNivelExperto() {
        Usuario usuarioMock = mock(Usuario.class);
        when(usuarioMock.getNivel()).thenReturn(Nivel.EXPERTO);
        assertTrue(usuarioMock.getNivel().esExperto());
    }

    @Test
    void esExperto_falseSiNivelBasico() {
        Usuario usuarioTest = new Usuario("Ana", true);
        assertFalse(usuarioTest.esExperto());
    }
}