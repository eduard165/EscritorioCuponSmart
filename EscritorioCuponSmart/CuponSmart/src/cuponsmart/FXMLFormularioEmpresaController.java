
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioEmpresaController implements Initializable {

    @FXML
    private TextField tfRepLegal;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private ImageView ivLogoEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarEmpresa(ActionEvent event) {
        
        irFormularioDomicilio();
         
    }
    

    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnBuscarLogo(ActionEvent event) {
    }

    @FXML
    private void btnSubirLogo(ActionEvent event) {
    }
    
    
    public void validarCamposLlenos(){
        
        
    }

    private void irFormularioDomicilio() {
        try {
             Stage stagePrincipal = (Stage)tfNombreEmpresa.getScene().getWindow();
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
