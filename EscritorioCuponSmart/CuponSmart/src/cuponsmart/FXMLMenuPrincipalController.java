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
import modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLMenuPrincipalController implements Initializable {
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void inicializarMenu(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void btnIrAdminSucursales(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminEmpresas(ActionEvent event) {
    }
    
}
