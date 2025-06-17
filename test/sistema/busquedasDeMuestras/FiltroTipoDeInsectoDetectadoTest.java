package sistema.busquedasDeMuestras;

import especieVinchuca.EspecieVinchuca;
import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiltroTipoDeInsectoDetectadoTest {

    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private List<Muestra> muestras;
    private FiltroTipoDeInsectoDetectado filtro1;
    private FiltroTipoDeInsectoDetectado filtro2;
    private FiltroTipoDeInsectoDetectado filtro3;



    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        muestras = List.of(muestra1, muestra2, muestra3);

        filtro1 = new FiltroTipoDeInsectoDetectado(EspecieVinchuca.VINCHUCA_SORDIDA);
        filtro2 = new FiltroTipoDeInsectoDetectado(EspecieVinchuca.VINCHUCA_GUASAYANA);
        filtro3 = new FiltroTipoDeInsectoDetectado(EspecieVinchuca.VINCHUCA_INFESTANS);
    }




    /**
     * Verifica que solo se devuelven las muestras que tienen el tipo de insecto buscado.
     */
    @Test
    public void filtraMuestrasConTipoVinchucaSordida() {
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        List<Muestra> resultado = filtro1.filtrarLasMuestras(muestras);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }




    /**
     * Verifica que solo se devuelven las muestras que tienen el tipo de insecto buscado.
     */
    @Test
    public void filtraMuestrasConTipoVinchucaGuasayana() {
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        List<Muestra> resultado = filtro2.filtrarLasMuestras(muestras);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra2));
        assertFalse(resultado.contains(muestra1));
        assertFalse(resultado.contains(muestra3));

        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }




    /**
     * Verifica que solo se devuelven las muestras que tienen el tipo de insecto buscado.
     */
    @Test
    public void filtraMuestrasConTipoVinchucaInfestans() {
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_INFESTANS);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_SORDIDA);

        List<Muestra> resultado = filtro3.filtrarLasMuestras(muestras);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertFalse(resultado.contains(muestra2));
        assertFalse(resultado.contains(muestra3));

        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }





    /**
     * Verifica que se omiten insectos de otro tipo.
     */
    @Test
    public void noFiltraMuestrasConTipoDistinto() {
        when(muestra1.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);
        when(muestra2.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_INFESTANS);
        when(muestra3.getTipoInsecto()).thenReturn(EspecieVinchuca.VINCHUCA_GUASAYANA);

        List<Muestra> resultado = filtro1.filtrarLasMuestras(muestras);

        assertTrue(resultado.isEmpty());

        verify(muestra1, times(1)).getTipoInsecto();
        verify(muestra2, times(1)).getTipoInsecto();
        verify(muestra3, times(1)).getTipoInsecto();
    }





    /**
     * Verifica que el filtro se comporta correctamente con lista vacía.
     */
    @Test
    public void noFallaCuandoListaEsVacia() {
        List<Muestra> resultado = filtro1.filtrarLasMuestras(List.of());

        assertTrue(resultado.isEmpty());

        verify(muestra1, never()).getTipoInsecto();
        verify(muestra2, never()).getTipoInsecto();
        verify(muestra3, never()).getTipoInsecto();
    }
}