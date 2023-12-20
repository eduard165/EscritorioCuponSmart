
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
public class FXMLFormularioUsuarioController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoMat;
    @FXML
    private TextField tfApellidoPat;
    @FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnCancelar(ActionEvent event) {
    }

    @FXML
    private void btnGuardarUsuario(ActionEvent event) {
    }

    @FXML
    private void cbTipoUsuario(ActionEvent event) {
    }

    @FXML
    private void cbEmpresaAsociada(ActionEvent event) {
    }
    
}
