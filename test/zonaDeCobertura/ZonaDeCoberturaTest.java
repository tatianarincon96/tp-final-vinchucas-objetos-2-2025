package zonaDeCobertura;


import muestra.Muestra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import ubicacion.Ubicacion;

import java.util.Observer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZonaDeCoberturaTest {

    private Ubicacion ubicacion1;
    private ZonaDeCobertura zona1;
    private Ubicacion ubicacion2;
    private ZonaDeCobertura zona2;
    private Muestra muestra1;

    @BeforeEach
    void setUp() {
        ubicacion1 = mock(Ubicacion.class);
        zona1 = new ZonaDeCobertura("Zona A", ubicacion1, 10.0);

        ubicacion2 = mock(Ubicacion.class);
        zona2 = new ZonaDeCobertura("zona B", ubicacion2, 5.0);

        muestra1 = mock(Muestra.class);
    }




    /**
     * Verifica que nombre, epicentro y radio se asignan correctamente al construir la zona1.
     */
    @Test
    public void testConstructorInicializaCorrectamente() {
        String nombre = "Zona A";
        Ubicacion epicentro = new Ubicacion(45, 120);
        double radio = 10.0;

        ZonaDeCobertura zona = new ZonaDeCobertura(nombre, epicentro, radio);

        assertEquals(nombre, zona.getNombre());
        assertEquals(epicentro, zona.getEpicentro());
        assertEquals(radio, zona.getRadio());
    }


    /**
     * Verifica que getNombre() devuelve el nombre esperado.
     */
    @Test
    public void testGetNombreDevuelveNombreCorrecto() {
        assertEquals("Zona A", zona1.getNombre());
    }


    /**
     * Verifica que getEpicentro() devuelve la instancia de Ubicacion pasada al constructor.
     */
    @Test
    public void testGetEpicentroDevuelveUbicacionCorrecta() {
        assertEquals(ubicacion1, zona1.getEpicentro());
    }


    /**
     * Verifica que getRadio() devuelve el valor correcto.
     */
    @Test
    public void testGetRadioDevuelveValorCorrecto() {
        assertEquals(10.0, zona1.getRadio());
    }


    /**
     * Verifica que getMuestrasEnLaZona() devuelve la lista.
     */
    @Test
    public void testGetMuestrasEnLaZonaDevuelveListaActual() {
        assertNotNull(zona1.getMuestrasEnLaZona());
        assertTrue(zona1.getMuestrasEnLaZona().isEmpty());
    }


    /**
     * Verifica que seSolapaCon() devuelve true si las zonas están lo suficientemente cerca para que se superpongan.
     */
    @Test
    public void testSeSolapaConDevuelveTrueSiZonasSeSolapan() {
        when(ubicacion1.calcularDistanciaHasta(ubicacion2)).thenReturn(8.0);

        assertTrue(zona1.seSolapaCon(zona2));

        verify(ubicacion1).calcularDistanciaHasta(ubicacion2);
    }


    /**
     * Verifica que devuelve false si las zonas están lo suficientemente lejos.
     */
    @Test
    public void testSeSolapaConDevuelveFalseSiZonasNoSeSolapan() {
        when(ubicacion1.calcularDistanciaHasta(ubicacion2)).thenReturn(20.0);

        assertFalse(zona1.seSolapaCon(zona2));

        verify(ubicacion1).calcularDistanciaHasta(ubicacion2);
    }


    /**
     * Verifica que agregarNuevaMuestra() agrega la muestra a la lista y la observa.
     */
    @Test
    public void testAgregarNuevaMuestraLaAgregaYObserva() {
        zona1.agregarNuevaMuestra(muestra1);

        assertTrue(zona1.getMuestrasEnLaZona().contains(muestra1));
        verify(muestra1).addObserver(zona1);
    }


    /**
     * Simula un update(...) desde una muestra observada, y verifica que la zona de cobertura notifica a sus observadores.
     */
    @Test
    public void testUpdateDesdeMuestraNotificaAObservadores() {
        Observer mockObserver = mock(Observer.class);
        zona1.addObserver(mockObserver);
        zona1.update(muestra1, null);

        verify(mockObserver).update(zona1, muestra1);
    }


}