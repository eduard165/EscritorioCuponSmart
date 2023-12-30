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

   
     public static MensajeUsuarios cargarUsuarios() {
        MensajeUsuarios respuesta = new MensajeUsuarios();
        String url = Constantes.URL_WS + "usuarios/cargarUsuarios";
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {

            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), MensajeUsuarios.class);
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Hubo un error al obtener la informacion de los pacientes, " + "intentelo nuevamente mas tarde");
        }
        return respuesta;

    }
    
    public static Mensaje registrarUsuario(Usuario usuario) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "usuarios/registrar";
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
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar el usuario");
        }
        return msj;
    }
     public static  List<Empresa> obtenerEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        String url = Constantes.URL_WS + "empresas/buscarEmpresas/a";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson json = new Gson();
            Type tipoListaEmpresa = new TypeToken<List<Empresa>>() {}.getType();
            empresas = json.fromJson(respuesta.getContenido(), tipoListaEmpresa);
           
        }
        return empresas;
    }
}
