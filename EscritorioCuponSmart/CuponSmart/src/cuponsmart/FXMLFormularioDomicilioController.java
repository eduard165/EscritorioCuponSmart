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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioDomicilioController implements Initializable {

    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfColonia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarDomicilio(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
