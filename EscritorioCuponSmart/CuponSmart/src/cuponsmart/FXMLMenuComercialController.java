/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponsmart;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.pojo.Usuario;
import utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLMenuComercialController implements Initializable {
    
    Usuario usuarioSesion;
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnIrAdminSucursales;
    @FXML
    private Button btnIrAdminPromociones;
    @FXML
    private Button btnIrAdminCupones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void inicializarMenuComercial(Usuario usuario){
        this.usuarioSesion = usuario;
    }

    @FXML
    private void btnCerrarSesion(ActionEvent event) {
        try {

            Stage stagePrincipal = (Stage) btnIrAdminCupones.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLInicioSesion.fxml"));
            Parent vista = loadVista.load();
            FXMLInicioSesionController controladorMenu = loadVista.getController();

            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();

        } catch (IOException ex) {

            ex.printStackTrace();
            Utilidades.mostrarAlertaSimple("ERROR", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnIrAdminSucursales(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminPromociones(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminCupones(ActionEvent event) {
    }
    
}
