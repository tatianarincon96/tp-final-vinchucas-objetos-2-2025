package sistema;

import usuarios.Usuario;

import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private List<Muestra> muestras;
    private List<ZonaDeCobertura> zonasDeCobertura;
    private List<Organizacion> organizaciones;

    public void agregarNuevoUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    public void agregarNuevaMuestra(Muestra muestra) {
        muestras.add(muestra);
    }
    public void agregarNuevaOrganizacion(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }
    private void agregarNuevaMuestraALasZonasDeCobertura() {}
    public void actualizarNivelDeTodosLosUsuarios() {}
    public List<Muestra> filtrarLasMuestrasPor(List<Filtro> filtros) {}

}
