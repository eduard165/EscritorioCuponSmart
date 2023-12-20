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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioPromocionController implements Initializable {

    @FXML
    private TextField tfNombrePromocion;
    @FXML
    private TextField tfRestricciones;
    @FXML
    private TextArea taDescripción;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaCaducidad;
    @FXML
    private ComboBox<?> cbCategoria;
    @FXML
    private ComboBox<?> cbTipoPromocion;
    @FXML
    private TextField tfDescuento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarPromocion(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
