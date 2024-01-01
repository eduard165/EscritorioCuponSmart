package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import utils.Constantes;

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
        CodigoHTTP respuesta = ConexionHTTP.peticionPUTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar la empresa");
        }
        return msj;
    }

    public static Mensaje eliminarEmpresa(String rfc) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "empresas/eliminar/" + rfc;
        Gson gson = new Gson();
        String parametros = gson.toJson(msj);
        CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar la empresa");
        }
        return msj;
    }

    public static Mensaje subirImagenEmpresa(String rfcEmpresa, File logo) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "empresas/registrarLogo/" + rfcEmpresa;
        try {
            byte[] imagen = Files.readAllBytes(logo.toPath());
            CodigoHTTP respuesta = ConexionHTTP.preticionPUTimagen(url, imagen);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                Gson gson = new Gson();
                mensaje = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                mensaje.setError(true);
                mensaje.setMensaje("Hubo un error al intentar subir el logo asociada a la empresa");
            }
        } catch (Exception e) {
            mensaje.setError(true);
            mensaje.setMensaje("Ocurrió un error al leer el logo o al realizar la petición: " + e.getMessage());
        }
        return mensaje;
    }
}
