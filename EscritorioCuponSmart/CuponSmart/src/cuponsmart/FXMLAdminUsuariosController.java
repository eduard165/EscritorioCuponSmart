
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminUsuariosController implements Initializable {

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
    @FXML
    private TableView<?> tvUsuarios;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnRegistroUsuario;
    @FXML
    private Button btnEdicionUsuario;
    @FXML
    private Button btnEliminarUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarGraficosAdmin();
    }    

    @FXML
    private void btnMenuPrincipal(ActionEvent event) {
    }

    @FXML
    private void btnRegistroUsuario(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioUsuario.fxml"));
            Parent vista = vistaLoad.load();
            
         //  FXMLFormularioUsuarioController controlador =  vistaLoad.getController();
          //controlador.inicializarInformacion(medicoSesion.getIdMedico());
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Registro de usuario");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    @FXML
    private void btnEdicionUsuario(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioUsuario.fxml"));
            Parent vista = vistaLoad.load();
            
          // FXMLFormularioUsuarioController controlador =  vistaLoad.getController();
          //controlador.inicializarInformacion(medicoSesion.getIdMedico());
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Editar un usuario");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
    }
    
    
    public void inicializarGraficosAdmin(){
        Image icAgregar = new Image("recursos/agregar.png");
        ImageView ivAgregar = new ImageView(icAgregar);
        ivAgregar.setFitHeight(50);
        ivAgregar.setPreserveRatio(true);
        
       btnRegistroUsuario.setGraphic(ivAgregar);
       
       Image icEditar = new Image("recursos/editar.png");
        ImageView ivEditar = new ImageView(icEditar);
        ivEditar.setFitHeight(50);
        ivEditar.setPreserveRatio(true);
        
       btnEdicionUsuario.setGraphic(ivEditar);
       
       Image icBorrar = new Image("recursos/borrar.png");
        ImageView ivBorrar = new ImageView(icBorrar);
        ivBorrar.setFitHeight(50);
        ivBorrar.setPreserveRatio(true);
        
       btnEliminarUsuario.setGraphic(ivBorrar);
       
    }
    
}
