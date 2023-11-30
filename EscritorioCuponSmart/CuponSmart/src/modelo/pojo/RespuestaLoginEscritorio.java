
package modelo.pojo;

/**
 *
 * @author lizet
 */
public class RespuestaLoginEscritorio {
    private Boolean error;
    private String contenido;
    private Usuario usuario;

    public RespuestaLoginEscritorio() {
    }

    public RespuestaLoginEscritorio(Boolean error, String contenido, Usuario usuario) {
        this.error = error;
        this.contenido = contenido;
        this.usuario = usuario;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
