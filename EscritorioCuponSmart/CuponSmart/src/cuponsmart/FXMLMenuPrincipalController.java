
package cuponsmart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLMenuPrincipalController implements Initializable {
    private Usuario usuario;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Label lbUsuarioSesion;

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


    @FXML
    private void btnCerrarSesion(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminUsuarios(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminPromociones(ActionEvent event) {
    }
    
}
