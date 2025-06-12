package opiniones;

import especieVinchuca.EspecieVinchuca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuarios.Basico;
import usuarios.Nivel;
import usuarios.NivelState;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OpinionTest {

    private Opinion opinionMock;

    @BeforeEach
    void setUp() {
        // Inicializar el mock de Opinion
        opinionMock = mock(Opinion.class);
    }

    @Test
    void constructorOpinion() {
        TipoDeOpinion tipo = TipoDeOpinion.IMAGEN_POCO_CLARA;

        // Crear una instancia real de Opinion
        Opinion opinion = new Opinion(Nivel.BASICO, tipo);

        // Verificar que los atributos se establecen correctamente
        assertEquals(Nivel.BASICO, opinion.getNivelDeOpinion());
        assertEquals(tipo, opinion.getTipoDeOpinion());
    }

    @Test
    void getTipoDeOpinion() {
        when(opinionMock.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
        assertEquals(TipoDeOpinion.IMAGEN_POCO_CLARA, opinionMock.getTipoDeOpinion());
        verify(opinionMock).getTipoDeOpinion();
    }

    @Test
    void getFechaOpinada() {
        LocalDateTime fecha = LocalDateTime.of(2020, 1, 1, 10, 15);
        when(opinionMock.getFechaOpinada()).thenReturn(fecha);
        assertEquals(fecha, opinionMock.getFechaOpinada());
        verify(opinionMock).getFechaOpinada();
    }

    @Test
    void getNivelDeOpinion() {
        when(opinionMock.getNivelDeOpinion()).thenReturn(Nivel.BASICO);
        assertEquals(Nivel.BASICO, opinionMock.getNivelDeOpinion());
        verify(opinionMock).getNivelDeOpinion();
    }

    @Test
    void esRelacionadaConVinchuca() {
        when(opinionMock.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_INFESTANTS);
        assertTrue(opinionMock.getTipoDeOpinion().esRelacionadaConVinchuca());
        assertEquals(EspecieVinchuca.VINCHUCA_INFESTANS, opinionMock.getTipoDeOpinion().getEspecieVinchuca());
    }

    @Test
    void fechaOpinadaNoEsNulo() {
        Opinion opinion = new Opinion(Nivel.BASICO, TipoDeOpinion.VINCHUCA_INFESTANTS);
        assertNotNull(opinion.getFechaOpinada(), "La fecha de la opinión no debe ser nula");
    }
}