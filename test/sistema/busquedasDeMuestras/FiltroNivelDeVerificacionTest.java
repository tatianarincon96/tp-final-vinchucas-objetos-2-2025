package sistema.busquedasDeMuestras;

import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiltroNivelDeVerificacionTest {

    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private List<Muestra> muestras;
    private FiltroNivelDeVerificacion filtroVerificada;
    private FiltroNivelDeVerificacion filtroNoVerificada;

    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        muestras = List.of(muestra1, muestra2, muestra3);

        filtroVerificada = new FiltroNivelDeVerificacion(true);
        filtroNoVerificada = new FiltroNivelDeVerificacion(false);
    }


    /**
     * Verifica que filtra solo muestras verificadas cuando estaVerificada=true.
     */
    @Test
    public void filtraMuestrasVerificadasCorrectamente() {
        when(muestra1.estaVerificada()).thenReturn(true);
        when(muestra2.estaVerificada()).thenReturn(false);
        when(muestra3.estaVerificada()).thenReturn(true);

        List<Muestra> resultado = filtroVerificada.filtrarLasMuestras(muestras);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(1)).estaVerificada();
        verify(muestra2, times(1)).estaVerificada();
        verify(muestra3, times(1)).estaVerificada();
    }


    /**
     * Verifica que filtra solo no verificadas cuando estaVerificada=false.
     */
    @Test
    public void filtraMuestrasNoVerificadasCorrectamente() {
        when(muestra1.estaVerificada()).thenReturn(true);
        when(muestra2.estaVerificada()).thenReturn(false);
        when(muestra3.estaVerificada()).thenReturn(false);

        List<Muestra> resultado = filtroNoVerificada.filtrarLasMuestras(muestras);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra2));
        assertTrue(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra1));

        verify(muestra1, times(1)).estaVerificada();
        verify(muestra2, times(1)).estaVerificada();
        verify(muestra3, times(1)).estaVerificada();
    }
}