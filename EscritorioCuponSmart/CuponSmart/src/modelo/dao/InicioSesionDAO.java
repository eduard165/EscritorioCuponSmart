/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import modelo.ConexionHTTP;
import modelo.pojo.CodigoHTTP;
import modelo.pojo.RespuestaLoginEscritorio;
import utils.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import modelo.pojo.Usuario;

/**
 *
 * @author lizet
 */
public class InicioSesionDAO {

    public static RespuestaLoginEscritorio validarSesionUsuario(Usuario usuario) {
        RespuestaLoginEscritorio rest = new RespuestaLoginEscritorio();
        String url = Constantes.URL_WS + "login/validacion/usuario";
        Gson gson = new Gson();
        String parametros = gson.toJson(usuario);
        CodigoHTTP respuesta = ConexionHTTP.peticionPOSTJson(url, parametros);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            rest = gson.fromJson(respuesta.getContenido(), RespuestaLoginEscritorio.class);
        } else {
            rest.setError(true);
            rest.setContenido("Hubo un error al validar datos " + respuesta.getCodigoRespuesta());
        }
        return rest;
    }

}
