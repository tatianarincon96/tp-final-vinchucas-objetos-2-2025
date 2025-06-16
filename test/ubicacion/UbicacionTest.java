package ubicacion;

import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import zonaDeCobertura.ZonaDeCobertura;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UbicacionTest {

    private Ubicacion ubicacion1;
    private Ubicacion ubicacion2;
    private Ubicacion ubicacion3;
    private Ubicacion ubicacion4;
    private Muestra muestra1;
    private Muestra muestra2;
    private Muestra muestra3;
    private Muestra muestra4;
    private ZonaDeCobertura zona;


    @BeforeEach
    void setUp() {
        ubicacion1 = new Ubicacion(45, 120);
        ubicacion2 = new Ubicacion(45.1, 120.1);
        ubicacion3 = new Ubicacion(46, 121);
        ubicacion4 = new Ubicacion(44, 119);

        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);
        muestra4 = mock(Muestra.class);

        zona = mock(ZonaDeCobertura.class);

    }


    /**
     * Verifica que se puede crear una Ubicacion válida sin lanzar excepción.
     */
    @Test
    public void testConstructorConLatitudYLongitudValidas() {
        assertEquals(Math.toRadians(45), ubicacion1.getLatitud());
        assertEquals(Math.toRadians(120), ubicacion1.getLongitud());
    }


    /**
     * Verifica que si se pasa una latitud fuera de rango, lanza IllegalArgumentException.
     */
    @Test
    public void testConstructorConLatitudInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Ubicacion(-100, 120));
        assertThrows(IllegalArgumentException.class, () -> new Ubicacion(110, 120));
    }


    /**
     * Verifica que si se pasa una longitud fuera de rango, lanza IllegalArgumentException.
     */
    @Test
    public void testConstructorConLongitudInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Ubicacion(45, -200));
        assertThrows(IllegalArgumentException.class, () -> new Ubicacion(45, 200));
    }


    /**
     * Verifica que getLatitud() devuelve correctamente el valor en radianes para una latitud conocida.
     */
    @Test
    public void testGetLatitudDevuelveValorCorrectoEnRadianes() {
        assertEquals(Math.toRadians(45), ubicacion1.getLatitud());
    }


    /**
     * Verifica que getLongitud() devuelve correctamente el valor en radianes para una longitud conocida.
     */
    @Test
    public void testGetLongitudDevuelveValorCorrectoEnRadianes() {
        assertEquals(Math.toRadians(120), ubicacion1.getLongitud());
    }


    /**
     * Verifica que solo las ubicaciones dentro del radio se incluyen en el resultado.
     */
    @Test
    public void testGetUbicacionesEnUnRadioIncluyeSoloUbicacionesDentroDelRadio() {
        List<Ubicacion> ubicaciones = ubicacion1.getUbicacionesEnUnRadio(List.of(ubicacion2, ubicacion3, ubicacion4), 100);

        assertTrue(ubicaciones.contains(ubicacion2));
        assertFalse(ubicaciones.contains(ubicacion3));
        assertFalse(ubicaciones.contains(ubicacion4));
    }


    /**
     * Verifica que devuelve lista vacía si ninguna ubicación está dentro del radio.
     */
    @Test
    public void testGetUbicacionesEnUnRadioDevuelveListaVaciaSiNingunaEstaEnElRadio() {
        List<Ubicacion> ubicaciones = ubicacion1.getUbicacionesEnUnRadio(List.of(ubicacion3, ubicacion4), 10);
        assertTrue(ubicaciones.isEmpty());
    }


    /**
     * Verifica que el metodo filtra correctamente las muestras dentro del radio respecto a la muestra central.
     */
    @Test
    public void testGetMuestrasEnUnRadioALaMuestraIncluyeSoloLasDentroDelRadio() {
        when(muestra1.getUbicacion()).thenReturn(ubicacion1);
        when(muestra2.getUbicacion()).thenReturn(ubicacion2);
        when(muestra3.getUbicacion()).thenReturn(ubicacion3);
        when(muestra4.getUbicacion()).thenReturn(ubicacion4);

        List<Muestra> muestras = ubicacion1.getMuestrasEnUnRadioALaMuestra(List.of(muestra2, muestra3, muestra4), 100, muestra1);

        assertTrue(muestras.contains(muestra2));
        assertFalse(muestras.contains(muestra3));
        assertFalse(muestras.contains(muestra4));

        verify(muestra1, times(3) ).getUbicacion();
        verify(muestra2).getUbicacion();
        verify(muestra3).getUbicacion();
        verify(muestra4).getUbicacion();
    }


    /**
     * Verifica que se devuelve una lista vacía si ninguna muestra está dentro del radio.
     */
    @Test
    public void testGetMuestrasEnUnRadioALaMuestraDevuelveListaVaciaSiNingunaEstaDentroDelRadio() {
        when(muestra1.getUbicacion()).thenReturn(ubicacion1);
        when(muestra2.getUbicacion()).thenReturn(ubicacion2);
        when(muestra3.getUbicacion()).thenReturn(ubicacion3);
        when(muestra4.getUbicacion()).thenReturn(ubicacion4);

        List<Muestra> muestras = ubicacion1.getMuestrasEnUnRadioALaMuestra(List.of(muestra3, muestra4), 10, muestra1);

        assertTrue(muestras.isEmpty());

        verify(muestra1, times(2)).getUbicacion();
        verify(muestra3).getUbicacion();
        verify(muestra4).getUbicacion();
    }


    /**
     * Verifica que enZona devuelve true si la ubicación está dentro del radio de la ZonaDeCobertura.
     */
    @Test
    public void testEnZonaDevuelveTrueSiEstaDentroDelRadioDeLaZona() {
        when(zona.getEpicentro()).thenReturn(ubicacion1);
        when(zona.getRadio()).thenReturn(100d);

        assertTrue(ubicacion1.enZona(zona));
        assertTrue(ubicacion2.enZona(zona));

        verify(zona, times(2)).getEpicentro();
        verify(zona, times(2)).getRadio();
    }


    /**
     * Verifica que enZona devuelve false si está fuera del radio.
     */
    @Test
    public void testEnZonaDevuelveFalseSiEstaFueraDelRadioDeLaZona() {
        when(zona.getEpicentro()).thenReturn(ubicacion1);
        when(zona.getRadio()).thenReturn(100d);

        assertFalse(ubicacion3.enZona(zona));
        assertFalse(ubicacion4.enZona(zona));

        verify(zona, times(2)).getEpicentro();
        verify(zona, times(2)).getRadio();
    }







}