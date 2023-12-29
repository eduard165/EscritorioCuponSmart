
package modelo.dao;

import com.google.gson.Gson;
import java.net.HttpURLConnection;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.MensajeUsuarios;
import utils.Constantes;

/**
 *
 * @author lizet
 */
public class EmpresaDAO {
    
     public static MensajeUsuarios cargarEmpresasAsociadas(String parametro) {
        MensajeUsuarios respuesta = new MensajeUsuarios();
        String url = Constantes.URL_WS + "empresas/buscarEmpresas/" + parametro;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {

            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), MensajeUsuarios.class);
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Hubo un error al obtener la informacion de las empresas, " + "intentelo nuevamente mas tarde");
        }
        return respuesta;

    }
        public static MensajeUsuarios cargarEmpresas(String parametro) {
        MensajeUsuarios respuesta = new MensajeUsuarios();
        String url = Constantes.URL_WS + "empresas/buscarEmpresas/a" ;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {

            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), MensajeUsuarios.class);
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Hubo un error al obtener la informacion de las empresas, " + "intentelo nuevamente mas tarde");
        }
        return respuesta;

    }
    
    public static Mensaje registrarEmpresa(Empresa empresa) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "empresas/agregar";
        Gson gson = new Gson();
        String parametros = gson.toJson(empresa);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para agregar el usuario");
        }
        return msj;
    }

    public static Mensaje editarEmpresa(Empresa empresa) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "empresas/editar";
        Gson gson = new Gson();
        String parametros = gson.toJson(empresa);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar el usuario");
        }
        return msj;
    }
}
