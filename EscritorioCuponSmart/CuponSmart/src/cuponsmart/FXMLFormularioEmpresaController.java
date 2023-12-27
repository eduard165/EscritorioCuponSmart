
package cuponsmart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    
}
