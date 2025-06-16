package sistema.busquedasDeMuestras;

import especieVinchuca.EspecieVinchuca;
import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ANDTest {

    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private List<Muestra> muestras;
    private FiltroCreacionDeMuestra filtroFecha;
    private FiltroTipoDeInsectoDetectado filtroEspecie;

    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        muestras = List.of(muestra1, muestra2, muestra3);

        filtroFecha = new FiltroCreacionDeMuestra(
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59)
        );
        filtroEspecie = new FiltroTipoDeInsectoDetectado(EspecieVinchuca.VINCHUCA_SORDIDA);
    }




    /**
     * Verifica que solo pasan las que cumplen ambos.
     */
    @Test
    public void filtraConAmbosFiltros() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0));
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2024, 1, 5, 9, 0));

        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        AND andFiltro = new AND(filtroFecha, filtroEspecie);
        List<Muestra> resultado = andFiltro.filtrarLasMuestras(muestras);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertFalse(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();
        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, never()).getTipoInsecto();
    }




    /**
     * Verifica que si el primer filtro ya no pasa nada, el segundo no debería importar.
     */
    @Test
    public void retornaListaVaciaSiPrimerFiltroNoDevuelveNada() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2022, 6, 15, 10, 0));
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2022, 7, 20, 12, 0));
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2024, 1, 5, 9, 0));

        AND andFiltro = new AND(filtroFecha, filtroEspecie);
        List<Muestra> resultado = andFiltro.filtrarLasMuestras(muestras);

        assertTrue(resultado.isEmpty());

        verify(muestra1, times(1)).getFechaDeCreacion();
        verify(muestra2, times(1)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();
        verify(muestra1, never()).getTipoInsecto();
        verify(muestra2, never()).getTipoInsecto();
        verify(muestra3, never()).getTipoInsecto();
    }




    /**
     * Verifica que si el segundo filtra todos, también debería dar lista vacía.
     */
    @Test
    public void retornaListaVaciaSiSegundoFiltroFiltraTodo() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0));
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 1, 5, 9, 0));

        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);

        AND andFiltro = new AND(filtroFecha, filtroEspecie);
        List<Muestra> resultado = andFiltro.filtrarLasMuestras(muestras);

        assertTrue(resultado.isEmpty());

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();
        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }
}