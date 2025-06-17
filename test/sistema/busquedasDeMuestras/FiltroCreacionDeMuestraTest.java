package sistema.busquedasDeMuestras;

import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiltroCreacionDeMuestraTest {

    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private List<Muestra> muestras;
    private LocalDateTime desde;
    private LocalDateTime hasta;
    private FiltroCreacionDeMuestra filtro;

    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        muestras = List.of(muestra1, muestra2, muestra3);

        desde = LocalDateTime.of(2023, 1, 1, 0, 0);
        hasta = LocalDateTime.of(2023, 12, 31, 23, 59);

        filtro = new FiltroCreacionDeMuestra(desde, hasta);
    }


    /**
     * Verifica que solo devuelve muestras cuya fechaDeCreacion está entre desde y hasta.
     */
    @Test
    public void filtraMuestrasDentroDelRango() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0)); // Fecha dentro del rango
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 7, 20, 12, 0)); // Fecha dentro del rango
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2024, 1, 5, 9, 0)); // Fecha fuera del rango

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra2));
        assertFalse(resultado.contains(muestra3));

        verify(muestra1, times(2)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();
    }


    /**
     * Verifica que ninguna muestra fuera del rango debe pasar.
     */
    @Test
    public void noFiltraMuestrasFueraDelRango() {
        when(muestra1.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2022, 12, 31, 23, 59)); // Fecha fuera del rango
        when(muestra2.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2024, 1, 1, 0, 0)); // Fecha fuera del rango
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2021, 6, 15, 10, 0)); // Fecha fuera del rango

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertTrue(resultado.isEmpty());
        verify(muestra1, times(1)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(1)).getFechaDeCreacion();
    }


    /**
     * Verifica que las muestras exactamente en desde o hasta no debe incluirse (por .isAfter() y .isBefore()).
     */
    @Test
    public void filtraCorrectamenteEnLimiteSuperiorEInferior() {
        when(muestra1.getFechaDeCreacion()).thenReturn(desde); // Fecha igual a 'desde'
        when(muestra2.getFechaDeCreacion()).thenReturn(hasta); // Fecha igual a 'hasta'
        when(muestra3.getFechaDeCreacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0)); // Fecha dentro del rango

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra1));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(1)).getFechaDeCreacion();
        verify(muestra2, times(2)).getFechaDeCreacion();
        verify(muestra3, times(2)).getFechaDeCreacion();
    }
}