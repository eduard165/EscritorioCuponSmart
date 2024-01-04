
package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.util.List;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import utils.Constantes;

public class PromocionDAO {
    
    public static List<Promocion> cargarPromocionesAsociadas(String parametro) {
        List<Promocion> respuesta = null;
        String url = Constantes.URL_WS + "gestionOfertas/buscarPromociones/" + parametro;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type listaPromociones= new TypeToken<List<Promocion>>() {
            }.getType();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), listaPromociones);
        }
        return respuesta;
    }
     public static List<Promocion> cargarPromociones() {
        List<Promocion> respuesta = null;
        String url = Constantes.URL_WS + "gestionOfertas/cargarPromociones";
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type listaPromociones= new TypeToken<List<Promocion>>() {
            }.getType();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), listaPromociones);
        }
        return respuesta;
    }


    public static Mensaje registrarPromocion(Promocion promocion) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "gestionOfertas/registrarPromocion";
        Gson gson = new Gson();
        String parametros = gson.toJson(promocion);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para agregar la empresa");
        }
        return msj;
    }

    public static Mensaje editarEmpresa(Promocion promocion) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "gestionOfertas/editarPromocion";
        Gson gson = new Gson();
        String parametros = gson.toJson(promocion);
        CodigoHTTP respuesta = ConexionHTTP.peticionPUTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar la empresa");
        }
        return msj;
    }

    public static Mensaje eliminarPromocion(int idPromocion) {
        Mensaje msj = new Mensaje();
        try {
            String url = Constantes.URL_WS + "gestionOfertas/eliminarPromocion";
            String parametros = "idPromocion=" + idPromocion;
            Gson gson = new Gson();
            CodigoHTTP respuesta = ConexionHTTP.peticionDELETE(url, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje("Error en la petición para eliminar la empresa");
            }
        } catch (Exception ex) {
            msj.setError(true);
            msj.setMensaje("Excepción al eliminar la empresa: " + ex.getMessage());
        }
        return msj;
    }

    public static Mensaje subirImagenPromocion(Integer id_Promocion, File logo) {
        Mensaje mensaje = new Mensaje();
        String url = Constantes.URL_WS + "gestionOfertas/registrarFoto/" + id_Promocion;
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
    public static Promocion descargarImagenEmpresa(Integer id_suInteger) {
         Promocion respuesta = new Promocion();
        String url = Constantes.URL_WS + "gestionOfertas/obtenerFoto/" + id_suInteger;
        CodigoHTTP codigoHTTP = ConexionHTTP.peticionGET(url);
        if (codigoHTTP.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoHTTP.getContenido(), Promocion.class);

        } 
        return respuesta;
    }
}
