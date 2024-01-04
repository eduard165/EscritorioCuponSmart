
package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Direccion;
import modelo.pojo.Estado;
import modelo.pojo.Mensaje;
import modelo.pojo.MensajeUsuarios;
import modelo.pojo.Municipio;
import utils.Constantes;

/**
 *
 * @author eduar
 */
public class DomicilioDAO {
   public static List<Estado> obtenerEstados() {
        List<Estado> estados = new ArrayList<>();
        String url = Constantes.URL_WS + "direccion/obtenerEstados";
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson json = new Gson();
            Type tipoListaEstado = new TypeToken<List<Estado>>() {
            }.getType();
            estados = json.fromJson(respuesta.getContenido(), tipoListaEstado);
           
        }
        return estados;
    }

    public static List<Municipio> obtenerMunicipios(Integer idEstado) {
        List<Municipio> municipios = new ArrayList<>();
        String url = Constantes.URL_WS + "direccion/obtenerMunicipios/" + idEstado;
        CodigoHTTP respuesta = ConexionHTTP.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson json = new Gson();
            Type tipoListaMunicipio = new TypeToken<List<Municipio>>() {}.getType();
            municipios = json.fromJson(respuesta.getContenido(), tipoListaMunicipio);
             System.out.println(municipios);
        }
        System.out.println(municipios);
        return municipios;
    }
     public static Direccion cargarDireccionEmpresa( String empresa_rfc) {
       Direccion respuesta = new Direccion();
        String url = Constantes.URL_WS + "direccion/buscar/empresa/" + empresa_rfc;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), Direccion.class);
        }
        return respuesta;

    }
     
      public static Direccion cargarDireccionSucursal( Integer id_sucursal) {
        Direccion respuesta = new Direccion();
        String url = Constantes.URL_WS + "direccion/buscar/sucursal/" + id_sucursal;
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {

            Gson gson = new Gson();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), Direccion.class);
        } 
        return respuesta;

    }
    
    public static Mensaje registrarDireccion(Direccion direccion) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "direccion/registrarDireccion";
        Gson gson = new Gson();
        String parametros = gson.toJson(direccion);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para agregar la direccion " + parametros);
        }
        return msj;
    }

    public static Mensaje editarDireccion(Direccion direccion) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "direccion/editarDireccion";
        Gson gson = new Gson();
        String parametros = gson.toJson(direccion);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar la direccion");
        }
        return msj;
    }
    

}
