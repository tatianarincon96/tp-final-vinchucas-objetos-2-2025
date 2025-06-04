package opiniones;

import especieVinchuca.EspecieVinchuca;

public enum TipoDeOpinion {
    /**
     * Tipos de opiniones posibles, pueden ser relacionadas con vinchucas o no.
     */
    VINCHUCA_INFESTANTS(EspecieVinchuca.VINCHUCA_INFESTANS),
    VINCHUCA_SORDIDA(EspecieVinchuca.VINCHUCA_SORDIDA),
    VINCHUCA_GUASAYANA(EspecieVinchuca.VINCHUCA_GUASAYANA),
    CHINCHE_FOLIADA(null),
    PHTIA_CHINCHE(null),
    NINGUNA(null),
    IMAGEN_POCO_CLARA(null);

    private EspecieVinchuca especieVinchuca;

    /**
     * Constructor del enum TipoDeOpinion.
     * @param especieVinchucas Especie de vinchuca asociada a la opinión, puede ser null si no está relacionada con vinchucas.
     */
    TipoDeOpinion(EspecieVinchuca especieVinchucas) {
        this.especieVinchuca = especieVinchucas;
    }

    /**
     * Verifica si la opinión está relacionada con una especie de vinchuca.
     * @return true si la opinión está relacionada con una especie de vinchuca, false en caso contrario.
     */
    public boolean esRelacionadaConVinchuca() {
        return this.especieVinchuca != null;
    }

    /**
     * Obtiene la especie de vinchuca asociada a la opinión.
     * @return Especie de vinchuca asociada, puede ser null si no está relacionada con vinchucas.
     */
    public EspecieVinchuca getEspecieVinchuca() {
        return especieVinchuca;
    }
}
