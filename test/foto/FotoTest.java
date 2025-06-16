package foto;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FotoTest {

    @Test
    public void cargaFotoDesdeArchivo() throws Exception {
        Path path = Path.of("src/assets/vinchuca.png");
        assertTrue(Files.exists(path));

        byte[] contenidoEsperado = Files.readAllBytes(path);

        Foto foto = new Foto("src/assets/vinchuca.png");

        assertArrayEquals(contenidoEsperado, foto.getData());
        assertTrue(foto.getData().length > 0);
    }

    @Test
    public void cuandoRutaEsInvalidaContenidoEsVacio() {

        Foto foto = new Foto("ruta/que/no/existe/archivo.png");

        assertNotNull(foto.getData());
        assertEquals(0, foto.getData().length);
    }
}
