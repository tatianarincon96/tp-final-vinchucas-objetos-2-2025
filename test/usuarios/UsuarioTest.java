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
    void registrarMuestra_agregaMuestraYActualizaSistema() throws Exception {
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

        usuarioTest.getNivel().updateNivel(usuarioTest);

        assertEquals(Experto.class, usuarioTest.getNivel().getClass());
        assertTrue(usuarioTest.getNivel().esExperto());
    }

    @Test
    void updateNivel_cambiaANivelExpertoYVolverABasico() {
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

        usuarioTest.getNivel().updateNivel(usuarioTest);

        assertEquals(Experto.class, usuarioTest.getNivel().getClass());
        assertTrue(usuarioTest.getNivel().esExperto());

        // Simular que pasan 31 días
        when(muestraMock.getFechaDeCreacion()).thenReturn(LocalDateTime.now().minusDays(31));
        when(opinionMock.getFechaOpinada()).thenReturn(LocalDateTime.now().minusDays(31));

        usuarioTest.getNivel().updateNivel(usuarioTest);

        assertEquals(Basico.class, usuarioTest.getNivel().getClass());
        assertFalse(usuarioTest.getNivel().esExperto());
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

        usuarioTest.getNivel().updateNivel(usuarioTest);

        assertEquals(Basico.class, usuarioTest.getNivel().getClass());
        assertFalse(usuarioTest.getNivel().esExperto());
    }

    @Test
    void getNivel_devuelveNivelActual() {
        assertEquals(Basico.class, usuario.getNivel().getClass());
    }

    @Test
    void esExperto_trueSiNivelExpertoExtremo() {
        Usuario usuarioTest = new Usuario("Ana", true);
        assertTrue(usuarioTest.esExpertoExterno());
    }

    @Test
    void esExperto_falseSiNoEsExpertoExtremo() {
        Usuario usuarioTest = new Usuario("Ana", false);
        assertFalse(usuarioTest.esExpertoExterno());
    }
}