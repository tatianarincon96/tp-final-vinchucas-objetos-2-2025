package foto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Foto {
	private byte[] contenido;
	
    public Foto(String url) {
        try {
            this.contenido = Files.readAllBytes(Paths.get(url));
        } catch (IOException e) {
            e.printStackTrace();
            this.contenido = new byte[0];
        }
    }

    public byte[] getData() {
        return contenido;
    }

}	
