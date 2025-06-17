package sistema.busquedasDeMuestras;

import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiltroUltimaVotacionTest {

    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private List<Muestra> muestras;
    private FiltroUltimaVotacion filtro;


    @BeforeEach
    void setUp() {
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);

        muestras = List.of(muestra1, muestra2, muestra3);

        filtro = new FiltroUltimaVotacion( LocalDateTime.of(2023, 1, 1, 0, 0) );
    }




    /**
     * Verifica que devuelve las que tienen última votación posterior a fechaMinima.
     */
    @Test
    public void filtraMuestrasConUltimaVotacionReciente() {
        when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0)); // Fecha posterior a fechaMinima
        when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2022, 7, 20, 12, 0)); // Fecha anterior a fechaMinima
        when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2023, 8, 5, 9, 0)); // Fecha posterior a fechaMinima

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertTrue(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(2)).getFechaUltimaVotacion();
        verify(muestra2, times(2)).getFechaUltimaVotacion();
        verify(muestra3, times(2)).getFechaUltimaVotacion();
    }




    /**
     * Verifica que ignora las que tienen getFechaUltimaVotacion() == null.
     */
    @Test
    public void noIncluyeMuestrasSinVotacion() {
        when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2023, 6, 15, 10, 0));
        when(muestra2.getFechaUltimaVotacion()).thenReturn(null); // Sin última votación
        when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2022, 8, 5, 9, 0));

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));
        assertFalse(resultado.contains(muestra3));
        assertFalse(resultado.contains(muestra2));

        verify(muestra1, times(2)).getFechaUltimaVotacion();
        verify(muestra2, times(1)).getFechaUltimaVotacion(); // Solo una vez porque es null
        verify(muestra3, times(2)).getFechaUltimaVotacion();
    }




    /**
     * Verifica que devuelve lista vacia ya que ninguna muestra tiene última votación posterior a fechaMinima.
     */
    @Test
    public void noIncluyeVotacionesPreviasALaFechaMinima() {
        when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2022, 6, 15, 10, 0)); // Fecha anterior a fechaMinima
        when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2022, 7, 20, 12, 0)); // Fecha anterior a fechaMinima
        when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDateTime.of(2022, 8, 5, 9, 0)); // Fecha anterior a fechaMinima

        List<Muestra> resultado = filtro.filtrarLasMuestras(muestras);

        assertTrue(resultado.isEmpty());

        verify(muestra1, times(2)).getFechaUltimaVotacion();
        verify(muestra2, times(2)).getFechaUltimaVotacion();
        verify(muestra3, times(2)).getFechaUltimaVotacion();
    }









}