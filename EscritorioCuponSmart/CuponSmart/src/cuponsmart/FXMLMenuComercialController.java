/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponsmart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLMenuComercialController implements Initializable {

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

    @FXML
    private void btnCerrarSesion(ActionEvent event) {
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