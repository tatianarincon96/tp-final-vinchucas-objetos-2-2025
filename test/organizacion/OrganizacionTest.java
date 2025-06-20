package organizacion;

import muestra.Muestra;
import org.junit.jupiter.api.*;
import organizacion.plugins.FuncionalidadExterna;
import ubicacion.Ubicacion;
import zonaDeCobertura.ZonaDeCobertura;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrganizacionTest {

    private Ubicacion ubicacion1;
    private FuncionalidadExterna plugin1;
    private FuncionalidadExterna plugin2;
    private TipoDeOrganizacion tipoDeOrganizacion;
    private Organizacion organizacion1;
    private ZonaDeCobertura zona1;
    private Muestra muestra1;

    @BeforeEach
    void setUp() {
        ubicacion1 = new Ubicacion(45, 120);

        plugin1 = mock(FuncionalidadExterna.class);
        plugin2 = mock(FuncionalidadExterna.class);

        tipoDeOrganizacion = TipoDeOrganizacion.SALUD;

        organizacion1 = new Organizacion(ubicacion1, 10, tipoDeOrganizacion, plugin1, plugin2);

        zona1 = mock(ZonaDeCobertura.class);
        muestra1 = mock(Muestra.class);
    }


    /**
     * Verifica que el constructor con dos FuncionalidadExterna inicializa todos los atributos correctamente.
     */
    @Test
    public void testConstructorConDosPlugins() {
        Organizacion organizacion = new Organizacion(ubicacion1, 10, tipoDeOrganizacion, plugin1, plugin2);

        assertEquals(ubicacion1, organizacion.getUbicacionPrincipal());
        assertEquals(10, organizacion.getCantidadDePersonal());
        assertEquals(tipoDeOrganizacion, organizacion.getTipoDeOrganizacion());
        assertEquals(plugin1, organizacion.getPluginMuestraNueva());
        assertEquals(plugin2, organizacion.getPluginMuestraVerificada());
    }


    /**
     * Verifica que el constructor con una sola FuncionalidadExterna inicializa ambos plugins con el mismo objeto.
     */
    @Test
    public void testConstructorConUnPluginInicializaCorrectamente() {
        Organizacion organizacion = new Organizacion(ubicacion1, 10, tipoDeOrganizacion, plugin1);

        assertEquals(ubicacion1, organizacion.getUbicacionPrincipal());
        assertEquals(10, organizacion.getCantidadDePersonal());
        assertEquals(tipoDeOrganizacion, organizacion.getTipoDeOrganizacion());
        assertEquals(plugin1, organizacion.getPluginMuestraNueva());
        assertEquals(plugin1, organizacion.getPluginMuestraVerificada());
    }


    /**
     * Verifica que se llama correctamente a zonaDeCobertura.addObserver(this).
     */
    @Test
    public void testSuscribirseALaZona() {
        organizacion1.suscribirseALaZona(zona1);

        verify(zona1).addObserver(organizacion1);
    }


    /**
     * Verifica que se llama correctamente a zonaDeCobertura.deleteObserver(this).
     */
    @Test
    public void testDesuscribirseALaZona() {
        organizacion1.desuscribirseALaZona(zona1);

        verify(zona1).deleteObserver(organizacion1);
    }


    /**
     * Simula una Muestra no verificada y verifica que se llama a nuevoEvento() del pluginMuestraNueva.
     */
    @Test
    public void testUpdateConMuestraNoVerificadaLlamaAlPluginMuestraNueva() {

        // Simula que la zona de cobertura notifica que una muestra se ha agregado
        when(muestra1.estaVerificada()).thenReturn(false);
        organizacion1.update(zona1, muestra1);

        verify(muestra1).estaVerificada();
        verify(plugin1).nuevoEvento(organizacion1, zona1, muestra1);
        verify(plugin2, never()).nuevoEvento(any(Organizacion.class), any(ZonaDeCobertura.class), any(Muestra.class));
    }


    /**
     * Simula una Muestra verificada y verifica que se llama a nuevoEvento() del pluginMuestraVerificada.
     */
    @Test
    public void testUpdateConMuestraVerificadaLlamaAlPluginMuestraVerificada() {

        // Simula que la zona de cobertura notifica que una muestra ha sido verificada
        when(muestra1.estaVerificada()).thenReturn(true);
        organizacion1.update(zona1, muestra1);

        verify(muestra1).estaVerificada();
        verify(plugin2).nuevoEvento(organizacion1, zona1, muestra1);
        verify(plugin1, never()).nuevoEvento(any(Organizacion.class), any(ZonaDeCobertura.class), any(Muestra.class));
    }

    /**
     * Cambia los puglins por otros
     */
    @Test
    public void testCambioDePlugins() {
        FuncionalidadExterna nuevoPlugin1 = mock(FuncionalidadExterna.class);
        FuncionalidadExterna nuevoPlugin2 = mock(FuncionalidadExterna.class);

        organizacion1.setPluginMuestraNueva(nuevoPlugin1);
        organizacion1.setPluginMuestraVerificada(nuevoPlugin2);

        assertEquals(nuevoPlugin1, organizacion1.getPluginMuestraNueva());
        assertEquals(nuevoPlugin2, organizacion1.getPluginMuestraVerificada());
    }


}