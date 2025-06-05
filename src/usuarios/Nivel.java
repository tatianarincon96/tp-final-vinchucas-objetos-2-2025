package usuarios;

public enum Nivel {
    // Niveles de usuario
    BASICO,
    EXPERTO;

    public boolean esExperto() {
        return this == EXPERTO;
    }
}
