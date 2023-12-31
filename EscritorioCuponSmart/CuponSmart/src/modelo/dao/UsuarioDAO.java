package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.Mnemonic;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.MensajeUsuarios;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.Usuario;
import utils.Constantes;

/**
 *
 * @author lizet
 */
public class UsuarioDAO {

    public static MensajeUsuarios cargarUsuarios(Integer id_usuario) {
        MensajeUsuarios respuesta = new MensajeUsuarios();
        String url = Constantes.URL_WS + "usuarios/cargarUsuarios/" + id_usuario;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {

            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), MensajeUsuarios.class);
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Hubo un error al obtener la informacion de los usuarios, " + "intentelo nuevamente mas tarde");
        }
        return respuesta;

    }

    public static Mensaje registrarUsuario(Usuario usuario) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "usuarios/agregar";
        Gson gson = new Gson();
        String parametros = gson.toJson(usuario);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para agregar el usuario");
        }
        return msj;
    }

    public static Mensaje editarUsuario(Usuario usuario) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "usuarios/editar";
        Gson gson = new Gson();
        String parametros = gson.toJson(usuario);
        CodigoHTTP respuesta = ConexionHTTP.peticionPUTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar el usuario");
        }
        return msj;
    }

    public static Mensaje eliminarUsuario(int id_usuario) {
        Mensaje mensaje = new Mensaje();
        try {
            String url = Constantes.URL_WS + "usuarios/eliminar";
            String parametros = "id_usuario=" + id_usuario;

            CodigoHTTP respuestaWS = ConexionHTTP.peticionDELETE(url, parametros);

            if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                mensaje = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("Error al eliminar el usuario. Código de respuesta: " + respuestaWS.getCodigoRespuesta());
            }
        } catch (Exception ex) {
            mensaje.setError(true);
            mensaje.setMensaje("Excepción al eliminar el usuario: " + ex.getMessage());
        }
        return mensaje;
    }

}
