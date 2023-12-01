
package cuponsmart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminUsuariosController implements Initializable {

    @FXML
    private TableView<?> tvEmpresas;
    @FXML
    private TableColumn<?, ?> tcNombre;
    @FXML
    private TableColumn<?, ?> tcApellidoPat;
    @FXML
    private TableColumn<?, ?> tcApellidoMat;
    @FXML
    private TableColumn<?, ?> tcRepLegal;
    @FXML
    private TableColumn<?, ?> tcTelefono;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private Label lbUsuarioSesion;

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
    private void btnRegistroUsuario(ActionEvent event) {
    }

    @FXML
    private void btnEdicionUsuario(ActionEvent event) {
    }

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
    }
    
}
