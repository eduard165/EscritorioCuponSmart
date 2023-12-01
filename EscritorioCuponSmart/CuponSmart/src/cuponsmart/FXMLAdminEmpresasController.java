
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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminEmpresasController implements Initializable {

    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private TableView<?> tvEmpresas;
    @FXML
    private TableColumn<?, ?> tcRFC;
    @FXML
    private TableColumn<?, ?> tcNombre;
    @FXML
    private TableColumn<?, ?> tcNombreComercial;
    @FXML
    private TableColumn<?, ?> tcTelefono;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcRepLegal;
    @FXML
    private TextField tfBusquedaEmpresa;
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
    private void btnRegistroEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnEdicionEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
    }

    
}
