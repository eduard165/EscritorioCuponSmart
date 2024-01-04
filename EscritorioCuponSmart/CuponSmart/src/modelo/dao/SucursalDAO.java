/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.Usuario;
import utils.Constantes;

/**
 *
 * @author eduar
 */
public class SucursalDAO {

    public static List<Sucursal> cargarSucursales() {
        List<Sucursal> respuesta = new ArrayList<>();
        String url = Constantes.URL_WS + "sucursales/cargarSucursales";
        CodigoHTTP codigoRespuesta = ConexionHTTP.peticionGET(url);
        if (codigoRespuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type listaSucursales= new TypeToken<List<Sucursal>>() {
            }.getType();
            respuesta = gson.fromJson(codigoRespuesta.getContenido(), listaSucursales);
        }
        return respuesta;
    }

    public static Mensaje registrarSucursal(Sucursal sucursal) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "sucursales/registrar";
        Gson gson = new Gson();
        String parametros = gson.toJson(sucursal);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para agregar el usuario");
        }
        return msj;
    }

    public static Mensaje editarSucursal(Sucursal sucursal) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "sucursales/editar";
        Gson gson = new Gson();
        String parametros = gson.toJson(sucursal);
        CodigoHTTP respuesta = ConexionHTTP.peticionPUTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la peticion para editar el usuario");
        }
        return msj;
    }

    public static Mensaje eliminarSucursal(int id_sucursal) {
        Mensaje mensaje = new Mensaje();
        try {
            String url = Constantes.URL_WS + "sucursales/eliminar";
            String parametros = "id_sucursal=" + id_sucursal;

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
