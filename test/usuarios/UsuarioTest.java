package usuarios;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import muestra.Muestra;
import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import sistema.Sistema;
import ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioTest {

    @Mock
    Sistema sistemaMock;
    @Mock
    NivelState nivelMock;
    @InjectMocks
    Usuario usuario;

    @BeforeEach
    void setUp() {
        sistemaMock = mock(Sistema.class);
        nivelMock = mock(NivelState.class);
        usuario = new Usuario("Juan", sistemaMock);
    }

    @Test
    void registrarMuestra_agregaMuestraYActualizaSistema() throws Exception {
        Ubicacion ubicacion = mock(Ubicacion.class);
        Foto foto = mock(Foto.class);

        usuario.registrarMuestra(EspecieVinchuca.VINCHUCA_GUASAYANA, ubicacion, foto);

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
        Usuario usuarioTest = new Usuario("Ana", sistemaMock);
        // Mock de muestra con fecha reciente
        Muestra muestraMock = mock(Muestra.class);
        when(muestraMock.getFechaDeCreacion()).thenReturn(LocalDateTime.now().minusDays(1));
        // Mock de opinion con fecha reciente
        Opinion opinionMock = mock(Opinion.class);
        when(opinionMock.getFechaOpinada()).thenReturn(LocalDateTime.now().minusDays(1));

        // Agregar 11 muestras recientes
        for (int i = 0; i < 11; i++) usuarioTest.getMuestrasCreadas().add(muestraMock);

        assertFalse(usuarioTest.getNivel().esExperto());
        assertEquals(Basico.class, usuarioTest.getNivel().getClass());

        // Agregar 21 opiniones recientes
        for (int i = 0; i < 21; i++) usuarioTest.getOpinionesHechas().add(opinionMock);

        usuarioTest.getNivel().updateNivel(usuarioTest);
        assertEquals(Experto.class, usuarioTest.getNivel().getClass());
        assertTrue(usuarioTest.getNivel().esExperto());
    }

    @Test
    void updateNivel_cambiarNivelAExpertoYVolverABasico() {
        Usuario usuario = new Usuario("Ana", sistemaMock);
        assertEquals(Basico.class, usuario.getNivel().getClass());
        // Mock de muestra con fecha reciente
        Muestra muestraMock = mock(Muestra.class);
        when(muestraMock.getFechaDeCreacion()).thenReturn(LocalDateTime.now().minusDays(1));
        // Mock de opinion con fecha reciente
        Opinion opinionMock = mock(Opinion.class);
        when(opinionMock.getFechaOpinada()).thenReturn(LocalDateTime.now().minusDays(1));

        // Agregar 11 muestras y 21 opiniones recientes y paso a Experto
        for (int i = 0; i < 11; i++) usuario.getMuestrasCreadas().add(muestraMock);
        for (int i = 0; i < 21; i++) usuario.getOpinionesHechas().add(opinionMock);
        usuario.getNivel().updateNivel(usuario);
        assertEquals(Experto.class, usuario.getNivel().getClass());
        assertTrue(usuario.esExperto());

        // Remuevo 21 opiniones recientes y vuelvo a Básico
        usuario.getOpinionesHechas().clear();
        usuario.getNivel().updateNivel(usuario);
        assertEquals(Basico.class, usuario.getNivel().getClass());
        assertFalse(usuario.esExperto());

        // Testeo que siga siendo porque no hay opiniones recientes
        usuario.getNivel().updateNivel(usuario);
        assertEquals(Basico.class, usuario.getNivel().getClass());

        // Agrego nuevamente 21 opiniones y vuelvo a Experto
        for (int i = 0; i < 21; i++) usuario.getOpinionesHechas().add(opinionMock);
        usuario.getNivel().updateNivel(usuario);

        // Verifico que siga siendo experto cuando se mantiene el número de muestras y opiniones
        usuario.getNivel().updateNivel(usuario);
        assertEquals(Experto.class, usuario.getNivel().getClass());

        // Remuevo las muestras y vuelvo a Básico
        usuario.getMuestrasCreadas().clear();
        usuario.getNivel().updateNivel(usuario);
        assertEquals(Basico.class, usuario.getNivel().getClass());
    }

    @Test
    void updateNivel_seMantieneBasico() {
        Usuario usuarioTest = new Usuario("Ana", sistemaMock);
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
        Usuario usuarioTest = new Usuario("Ana", sistemaMock, true);
        assertTrue(usuarioTest.esExpertoExterno());
    }

    @Test
    void esExperto_falseSiNoEsExpertoExtremo() {
        Usuario usuarioTest = new Usuario("Ana", sistemaMock, false);
        assertFalse(usuarioTest.esExpertoExterno());
    }

    @Test
    void registrarMuestra_agregaMuestraYEsExpertoExterno() throws Exception {
        Usuario usuarioTest = new Usuario("Ana", sistemaMock, true);
        Ubicacion ubicacion = mock(Ubicacion.class);
        Foto foto = mock(Foto.class);
        usuarioTest.registrarMuestra(EspecieVinchuca.VINCHUCA_GUASAYANA, ubicacion, foto);
        assertFalse(usuarioTest.getMuestrasCreadas().isEmpty());
    }

    @Test
    void opinaSobreMuestra_esExpertoExterno() throws Exception {
        Usuario usuarioTest = new Usuario("Ana", sistemaMock, true);
        Muestra muestraMock = mock(Muestra.class);
        usuarioTest.opinar(muestraMock, new Opinion(usuarioTest.getNivel().getClass(), TipoDeOpinion.IMAGEN_POCO_CLARA));
        assertFalse(usuarioTest.getOpinionesHechas().isEmpty());
    }
}