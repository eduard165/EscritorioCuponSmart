package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
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

    public static Empresa cargarEmpresasAsociadas(String parametro) {
        Empresa respuesta = null;
        String url = Constantes.URL_WS + "empresas/buscar/" + parametro;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), Empresa.class);
        }
        return respuesta;
    }

    public static List<Empresa> cargarEmpresas() {
        List<Empresa> respuesta = new ArrayList<>();
        String url = Constantes.URL_WS + "empresas/cargarEmpresas";
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);

        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type listaEmpresas = new TypeToken<List<Empresa>>() {
            }.getType();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), listaEmpresas);
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
            msj.setMensaje("Error en la peticion para agregar la empresa");
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
            msj.setMensaje("Error en la peticion para editar la empresa");
        }
        return msj;
    }
}
