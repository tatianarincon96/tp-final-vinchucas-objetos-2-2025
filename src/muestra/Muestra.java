package muestra;

import java.time.LocalDateTime;
import java.util.*;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import ubicacion.Ubicacion;

public class Muestra extends Observable {
    private UUID id;
//    private EspecieVinchuca tipoInsecto;
    private LocalDateTime fechaDeCreacion;
    private boolean estadoVerificacion;
    private Ubicacion ubicacion;
    private List<Foto> fotosAdjuntadas;
    private UUID idDelUsuarioAutor;
    private Map<UUID, Opinion> opiniones;
    private VotoUsuario filtroDeVoto;

    public Muestra(EspecieVinchuca tipoInsecto, Ubicacion ubicacion, List<Foto> fotosAdjuntadas, UUID idDelUsuarioAutor) {
        this.id = UUID.randomUUID();
//        this.tipoInsecto = tipoInsecto;
//        this.ubicacion = ubicacion;
        this.fotosAdjuntadas = fotosAdjuntadas != null ? fotosAdjuntadas : new ArrayList<>();
        this.idDelUsuarioAutor = idDelUsuarioAutor;
        this.fechaDeCreacion = LocalDateTime.now();
        this.estadoVerificacion = false;
        this.opiniones = new HashMap<>();
        this.filtroDeVoto = new DefaultVotoUsuario();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }


    public void agregarOpinionDe(Usuario usuario, Opinion opinion) {
    	if (this.estadoVerificacion.puedeOpinar(usuario)){
    		 this.opiniones.put(usuario, opinion);
    	}else {
    		throw new Exception("El usuario no puede opinar sobre esta muestra");
    	}
       
    }

//    public void notificarZonas() {
//    }

//    public Opinion opinionProminenteActual() {
//        return filtroDeVoto.filtrar(opiniones);
//    }

    public boolean estaVerificada() {
        return estadoVerificacion;
    }
}
