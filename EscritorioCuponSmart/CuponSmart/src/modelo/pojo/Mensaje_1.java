
package modelo.pojo;

public class Mensaje_1 {
    private boolean error;
    private String mensaje;

    public Mensaje_1() {
    }

    public Mensaje_1(boolean error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
