package opiniones;

import usuarios.Nivel;
import usuarios.Usuario;

import java.time.LocalDateTime;

public class Opinion {

    //------- Atributos del Opinion -------

    private TipoDeOpinion tipoDeOpinion;
    private LocalDateTime fechaOpinada;
    private Nivel nivelDeOpinion;

    //------- Constructores de la clase Opinion -------

    /**
     * Constructor de la clase Opinion.
     * @param nivelDeOpinion nivel del usuario que emite la opinion, no puede ser nulo.
     * @param tipoDeOpinion tipo de la opinion, no puede ser nulo.
     */
    public Opinion(Nivel nivelDeOpinion, TipoDeOpinion tipoDeOpinion) {
        this.nivelDeOpinion = nivelDeOpinion;
        this.tipoDeOpinion = tipoDeOpinion;
    }

    /**
     * Getters de la clase Opinion
    */
    public TipoDeOpinion getTipoDeOpinion() {
        return tipoDeOpinion;
    }

    public LocalDateTime getFechaOpinada() {
        return fechaOpinada;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }

    public Nivel getNivelDeOpinion() {
        return nivelDeOpinion;
    }
}
