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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminSucursalesController implements Initializable {

    @FXML
    private TableColumn<?, ?> tcNombreSucursal;
    @FXML
    private TableColumn<?, ?> tcEncargado;
    @FXML
    private TableColumn<?, ?> tcEmpresa;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcRepLegal;
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private TableView<?> tvSucursales;
    @FXML
    private ImageView ivUsuarioSesionFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMenuPrincipal(ActionEvent event) {
    }

    @FXML
    private void btnRegistroSucursal(ActionEvent event) {
    }

    @FXML
    private void btnEdicionSucursal(ActionEvent event) {
    }

    @FXML
    private void btnEliminarSucursal(ActionEvent event) {
    }

    @FXML
    private void btnIrFormularioDomicilio(ActionEvent event) {
    }
    
}
