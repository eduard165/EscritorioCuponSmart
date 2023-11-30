/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponsmart;

import modelo.dao.InicioSesionDAO;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.Usuario;
import utils.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author lizet
 */
public class FXMLInicioSesionController implements Initializable {

    Usuario usuario = new Usuario();
    @FXML
    private Label label;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label lbErrorUsername;
    @FXML
    private Label lbErrorPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void btnInicioSesion(ActionEvent event) {
        if (validarDatos()) {
            verificarSesion(usuario);
        }
    }
    private void verificarSesion(Usuario usuario) {
        RespuestaLoginEscritorio respuestaValidacionLogin = InicioSesionDAO.validarSesionUsuario(usuario);
        if (!respuestaValidacionLogin.getError()) {
            Utilidades.mostrarAlertaSimple("Credenciales correctas", respuestaValidacionLogin.getContenido(), Alert.AlertType.INFORMATION);
            this.usuario = respuestaValidacionLogin.getUsuario();
            irPantallaPrincipal();
        } else {
            Utilidades.mostrarAlertaSimple("Error", respuestaValidacionLogin.getContenido(), Alert.AlertType.ERROR);
        }
    }

    private void irPantallaPrincipal() {
        try {
            Stage stagePrincipal = (Stage) tfUsuario.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Parent vista = loadVista.load();
            FXMLMenuPrincipalController controladorMenu = loadVista.getController();
            controladorMenu.inicializarMenu(usuario);
            
            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("ERROR", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Boolean validarDatos() {
        lbErrorUsername.setText("");
        lbErrorPassword.setText("");

        if (tfUsuario.getText().isEmpty()) {
            lbErrorUsername.setText("Nombre de usuario obligatorio.");
            return false;
        }
        if (tfPassword.getText().isEmpty()) {
            lbErrorPassword.setText("Contraseña es obligatoria.");
            return false;
        }
        asignarDatos();
        return true;
    }

    private void asignarDatos() {
        this.usuario.setPassword(tfPassword.getText());
        this.usuario.setUsername(tfUsuario.getText());
    }
}
