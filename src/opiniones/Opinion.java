package opiniones;

import usuarios.Nivel;

import java.time.LocalDateTime;

public class Opinion {
    private TipoDeOpinion tipoDeOpinion;
    private LocalDateTime fechaOpinada;
    private Nivel nivelDeOpinion;

    public Opinion(Nivel nivelDeOpinion, TipoDeOpinion tipoDeOpinion) {
        this.nivelDeOpinion = nivelDeOpinion;
        this.tipoDeOpinion = tipoDeOpinion;
    }

    public TipoDeOpinion getTipoDeOpinion() {
        return tipoDeOpinion;
    }

    public LocalDateTime getFechaOpinada() {
        return fechaOpinada;
    }

    public Nivel getNivelDeOpinion() {
        return nivelDeOpinion;
    }
}
