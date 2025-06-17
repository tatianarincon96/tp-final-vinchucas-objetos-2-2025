package especieVinchuca;

public enum EspecieVinchuca {
    VINCHUCA_INFESTANS,
    VINCHUCA_SORDIDA,
    VINCHUCA_GUASAYANA;

    @Override
    public String toString() {
        return switch (this) {
            case VINCHUCA_INFESTANS -> "Vinchuca infestans";
            case VINCHUCA_SORDIDA -> "Vinchuca sordida";
            case VINCHUCA_GUASAYANA -> "Vinchuca guasayana";
        };
    }
}