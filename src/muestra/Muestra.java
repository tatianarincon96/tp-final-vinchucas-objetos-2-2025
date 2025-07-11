package muestra;

import java.time.LocalDateTime;
import java.util.*;

import especieVinchuca.EspecieVinchuca;
import foto.Foto;
import opiniones.Opinion;
import opiniones.TipoDeOpinion;
import ubicacion.Ubicacion;
import usuarios.*;
import usuarios.Usuario;

public class Muestra extends Observable {
        private LocalDateTime fechaDeCreacion;
        private Ubicacion ubicacion;
        private Foto fotoAdjuntada;
        private Usuario usuarioAutor;
        private EstadoDeMuestra estado;
        private HashMap<Class<? extends NivelState>, HashMap<Usuario, Opinion>> opiniones;
        private LocalDateTime fechaUltimaVotacion;
        private EspecieVinchuca tipoInsecto;

        public Muestra(EspecieVinchuca tipoInsecto, Ubicacion ubicacion, Foto fotoAdjuntada, Usuario usuarioAutor)
                throws Exception {
            this.ubicacion = ubicacion;
            this.validarFoto(fotoAdjuntada);
            this.fotoAdjuntada = fotoAdjuntada;
            this.usuarioAutor = usuarioAutor;
            this.estado = new CualquierOpinion();
            this.opiniones = new HashMap<>();
            this.fechaUltimaVotacion = LocalDateTime.now();
            this.fechaDeCreacion = LocalDateTime.now();
            this.tipoInsecto = tipoInsecto;

            TipoDeOpinion tipo = TipoDeOpinion.desdeEspecie(tipoInsecto);
            this.agregarOpinionDe(usuarioAutor, new Opinion(usuarioAutor.getNivel().getClass(), tipo));
        }

        private void validarFoto(Foto foto) throws Exception {
            if (foto == null) {
                throw new Exception("La muestra debe incluir foto");
            }
        }

        public Ubicacion getUbicacion() {
            return ubicacion;
        }

        public Usuario getUsuario() {
            return this.usuarioAutor;
        }

        public LocalDateTime getFechaUltimaVotacion() {
            return this.fechaUltimaVotacion;
        }

        public EspecieVinchuca getTipoInsecto() {
            return this.tipoInsecto;
        }

        public boolean tieneOpinionesDeExperto() {
            return this.opiniones.containsKey(Experto.class);
        }

        private boolean usuarioYaVoto(Usuario usuario) {
            return this.opiniones.values().stream().anyMatch(m -> m.containsKey(usuario));
        }

        boolean puedeOpinar(Usuario usuario) {
            return this.estado.puedeOpinar(usuario) && !this.usuarioYaVoto(usuario);
        }

        public void agregarOpinionDe(Usuario usuario, Opinion opinion) throws Exception {
            if (!this.puedeOpinar(usuario)) {
                throw new Exception("El usuario no puede opinar sobre esta muestra");
            }
            this.opiniones
                    .computeIfAbsent(usuario.getNivel().getClass(), k -> new HashMap<>())
                    .put(usuario, opinion);
            this.estado = this.estado.actualizarSiAplica(this);
        }

        public EstadoDeMuestra getEstado() {
            return this.estado;
        }
        public boolean estaVerificada() {
            return this.estado.estaVerificada();
        }

        public TipoDeOpinion resultadoActual() {
            return this.estado.resultadoActual(this);
        }

        public LocalDateTime getFechaDeCreacion() {
            return fechaDeCreacion;
        }

        public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
            this.fechaDeCreacion = fechaDeCreacion;
        }

        public HashMap<Usuario, Opinion> getOpinionesExpertas() {
            return this.opiniones.get(Experto.class);
        }

        public HashMap<Usuario, Opinion> getOpinionesBasicas() {
            return this.opiniones.get(Basico.class);
        }

        public Foto getFotoAdjuntada() {
            return fotoAdjuntada;
        }

        public int cantidadDeExpertosQueOpinan(TipoDeOpinion resultadoActual) {
            return (int) this.opiniones.get(Experto.class).values().stream()
                    .filter(op -> op.getTipoDeOpinion().equals(resultadoActual)).count();
        }

        public Map<Usuario, Opinion> historialDeOpiniones() {
            Map<Usuario, Opinion> historial = new HashMap<>();
            for (Map<Usuario, Opinion> subMap : opiniones.values()) {
                historial.putAll(subMap);
            }
            return historial;
        }


        public void notificarObservadoresSobreVerificacion() {
            this.setChanged();
            this.notifyObservers();
        }

}
