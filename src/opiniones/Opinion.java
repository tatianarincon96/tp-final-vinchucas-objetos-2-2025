package opiniones;

import usuarios.NivelState;

import java.time.LocalDateTime;

public class Opinion {

    //------- Atributos del Opinion -------

    private TipoDeOpinion tipoDeOpinion;
    private LocalDateTime fechaOpinada;
    private Class<? extends NivelState> nivelDeOpinion;

    //------- Constructores de la clase Opinion -------

    /**
     * Constructor de la clase Opinion.
     * @param nivelDeOpinion nivel del usuario que emite la opinion, no puede ser nulo.
     * @param tipoDeOpinion tipo de la opinion, no puede ser nulo.
     */
    public Opinion(Class<? extends NivelState> nivelDeOpinion, TipoDeOpinion tipoDeOpinion) {
        this.nivelDeOpinion = nivelDeOpinion;
        this.tipoDeOpinion = tipoDeOpinion;
        this.fechaOpinada = LocalDateTime.now();
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

    public Class<? extends NivelState> getNivelDeOpinion() {
        return nivelDeOpinion;
    }
}
