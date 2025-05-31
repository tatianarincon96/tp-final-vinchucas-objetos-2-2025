package especieVinchuca;

public enum EspecieVinchuca {
    VINCHUCA_INFESTANS,
    VINCHUCA_SORDIDA,
    VINCHUCA_GUASAYANA;

    @Override
    public String toString() {
        switch (this) {
            case VINCHUCA_INFESTANS: return "Vinchuca infestans";
            case VINCHUCA_SORDIDA: return "Vinchuca sordida";
            case VINCHUCA_GUASAYANA: return "Vinchuca guasayana";
            default: return super.toString();
        }
    }
}