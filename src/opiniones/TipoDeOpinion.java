package opiniones;

import especieVinchuca.EspecieVinchuca;

public enum TipoDeOpinion {
    VINCHUCA_INFESTANTS(EspecieVinchuca.VINCHUCA_INFESTANS),
    VINCHUCA_SORDIDA(EspecieVinchuca.VINCHUCA_SORDIDA),
    VINCHUCA_GUASAYANA(EspecieVinchuca.VINCHUCA_GUASAYANA),
    CHINCHE_FOLIADA(null),
    PHTIA_CHINCHE(null),
    NINGUNA(null),
    IMAGEN_POCO_CLARA(null);

    private EspecieVinchuca especieVinchuca;

    TipoDeOpinion(EspecieVinchuca especieVinchucas) {
        this.especieVinchuca = especieVinchucas;
    }
    public boolean esRelacionadaConVinchuca() {}

    public EspecieVinchuca getEspecieVinchuca() {
        return especieVinchuca;
    }
}
