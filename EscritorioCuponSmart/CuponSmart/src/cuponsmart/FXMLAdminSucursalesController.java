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
public class FXMLAdminSucursalesController implements Initializable {

    @FXML
    private TableColumn<?, ?> tcNombreSucursal;
    @FXML
    private TableColumn<?, ?> tcEncargado;
    @FXML
    private TableColumn<?, ?> tcEmpresa;
    @FXML
    private TableColumn<?, ?> tcEmail;
    @FXML
    private TableColumn<?, ?> tcRepLegal;
    @FXML
    private TableView<?> tvSucursales;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnRegistroSuc;
    @FXML
    private Button btnEdicionSuc;
    @FXML
    private Button btnEliminarSuc;
    private Button btnFormularioDom;
    @FXML
    private TextField tfBusquedaSucursal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarGraficosAdmin();
    }    


    @FXML
    private void btnRegistroSucursal(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioSucursal.fxml"));
            Parent vista = vistaLoad.load();
           //Agregar paso de información de la sesión 
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Registrar sucursal");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEdicionSucursal(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioSucursal.fxml"));
            Parent vista = vistaLoad.load();
           //Agregar paso de información de la sesión 
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Editar sucursal");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarSucursal(ActionEvent event) {
    }

    private void btnIrFormularioDomicilio(ActionEvent event) {
         try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioDomicilio.fxml"));
            Parent vista = vistaLoad.load();
           //Agregar paso de información de la sesión 
           
           Stage stage = new Stage();
           Scene escenaAdmin = new Scene(vista);
           stage.setScene(escenaAdmin);
           stage.setTitle("Editar empresa");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void inicializarGraficosAdmin(){
        Image icAgregar = new Image("recursos/agregar.png");
        ImageView ivAgregar = new ImageView(icAgregar);
        ivAgregar.setFitHeight(50);
        ivAgregar.setPreserveRatio(true);
        
       btnRegistroSuc.setGraphic(ivAgregar);
       
       Image icEditar = new Image("recursos/editar.png");
        ImageView ivEditar = new ImageView(icEditar);
        ivEditar.setFitHeight(50);
        ivEditar.setPreserveRatio(true);
        
       btnEdicionSuc.setGraphic(ivEditar);
       
       Image icBorrar = new Image("recursos/borrar.png");
        ImageView ivBorrar = new ImageView(icBorrar);
        ivBorrar.setFitHeight(50);
        ivBorrar.setPreserveRatio(true);
        
        btnEliminarSuc.setGraphic(ivBorrar);
        
        Image icDomicilio = new Image("recursos/direccion.png");
        ImageView ivDomicilio = new ImageView(icDomicilio);
        ivDomicilio.setFitHeight(50);
        ivDomicilio.setPreserveRatio(true);
        
        btnFormularioDom.setGraphic(ivDomicilio);
        
        
       
    }
}
