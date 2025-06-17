package sistema.busquedasDeMuestras;

import especieVinchuca.EspecieVinchuca;
import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ORTest {

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
     * Verifica que se unan correctamente los resultados de ambos filtros.
     */
    @Test
    public void devuelveUnionDeAmbosFiltros() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0));
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2024, 1, 5, 9, 0));

        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        OR orFiltro = new OR(filtroFecha, filtroEspecie);
        List<Muestra> resultado = orFiltro.filtrarLasMuestras(muestras);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra2));
        assertTrue(resultado.contains(muestra3));

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();

        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }




    /**
     * Verifica que si una muestra aparece en ambos filtros, debe aparecer una sola vez.
     */
    @Test
    public void noDuplicaMuestrasRepetidas() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0));
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);

        OR orFiltro = new OR(filtroFecha, filtroEspecie);
        List<Muestra> resultado = orFiltro.filtrarLasMuestras(List.of(muestra1, muestra2));

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra2));
        assertEquals(resultado.indexOf(muestra1), resultado.lastIndexOf(muestra1));

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra2, times(1)).getTipoInsecto();
    }




    /**
     * Verifica que si el primer filtro no devuelve resultados, se devuelven los del segundo filtro.
     */
    @Test
    public void retornaSoloResultadosDelPrimerFiltroSiElSegundoNoDevuelveNada() {
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0));

        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);

        OR orFiltro = new OR(filtroFecha, filtroEspecie);
        List<Muestra> resultado = orFiltro.filtrarLasMuestras(List.of(muestra2));

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra2));

        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra2, times(1)).getTipoInsecto();
    }




    /**
     * Verifica que si el segundo filtro no devuelve resultados, se devuelven los del primer filtro.
     */
    @Test
    public void retornaSoloResultadosDelSegundoFiltroSiElPrimeroNoDevuelveNada() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        OR orFiltro = new OR(filtroFecha, filtroEspecie);
        List<Muestra> resultado = orFiltro.filtrarLasMuestras(List.of(muestra1));

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra1, times(1)).getTipoInsecto();
    }
}