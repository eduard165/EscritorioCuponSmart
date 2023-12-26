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
public class FXMLAdminPromocionesController implements Initializable {

    @FXML
    private TableView<?> tvEmpresas;
    @FXML
    private TableColumn<?, ?> tcRFC;
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
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private Button btnRegistroPromo;
    @FXML
    private Button btnEdicionPromo;
    @FXML
    private Button btnEliminarPromo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarGraficosAdmin();
    }    


    @FXML
    private void btnRegistroPromocion(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioPromocion.fxml"));
            Parent vista = vistaLoad.load();
           //Agregar paso de información de la sesión 
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Registrar promoción");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEdicionPromocion(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioPromocion.fxml"));
            Parent vista = vistaLoad.load();
           //Agregar paso de información de la sesión 
       
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Editar promoción");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarPromocion(ActionEvent event) {
    }
    
    
     public void inicializarGraficosAdmin(){
        Image icAgregar = new Image("recursos/agregar.png");
        ImageView ivAgregar = new ImageView(icAgregar);
        ivAgregar.setFitHeight(50);
        ivAgregar.setPreserveRatio(true);
        
       btnRegistroPromo.setGraphic(ivAgregar);
       
       Image icEditar = new Image("recursos/editar.png");
        ImageView ivEditar = new ImageView(icEditar);
        ivEditar.setFitHeight(50);
        ivEditar.setPreserveRatio(true);
        
       btnEdicionPromo.setGraphic(ivEditar);
       
       Image icBorrar = new Image("recursos/borrar.png");
        ImageView ivBorrar = new ImageView(icBorrar);
        ivBorrar.setFitHeight(50);
        ivBorrar.setPreserveRatio(true);
        
       btnEliminarPromo.setGraphic(ivBorrar);
      
       
    }
}
