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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioSucursalController implements Initializable {

    @FXML
    private TextField tfNombreSucursal;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfNombreEncargado;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    @FXML
    private TextField tfEmailSucursal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarPromocion(ActionEvent event) {
        
        irFormularioDomicilio();
        
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    private void irFormularioDomicilio() {
        try {
             Stage stagePrincipal = (Stage)tfNombreSucursal.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLFormularioDomicilio.fxml"));
            Parent vista = loadVista.load();
            
            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    }
    

