
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author eduar
 */
public class FXMLMenuPrincipalController implements Initializable {
    private Usuario usuarioSesion;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private Button btnIrAdminEmpresas;
    @FXML
    private Button btnIrAdminSucursales;
    @FXML
    private Button btnIrAdminUsuarios;
    @FXML
    private Button btnIrAdminPromociones;
    @FXML
    private Button btnIrAdminCupones;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
       inicializarGraficosMenu();
       
        
    }    
    public void inicializarMenu(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
        lbUsuarioSesion.setText(usuarioSesion.getUsername());
    }

    @FXML
    private void btnIrAdminSucursales(ActionEvent event) {
        try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAdminSucursales.fxml"));
            Parent vista = vistaLoad.load();
            
           // FXMLAdminUsuariosController controlador = vistaLoad.getController();
           // controlador.inicializarInformacion(usuarioSesion.getRolID());
           
           Stage stage = new Stage();
           Scene escena = new Scene(vista);
           stage.setScene(escena);
           stage.setTitle("Administrador de sucursales");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    @FXML
    private void btnIrAdminEmpresas(ActionEvent event) {
        try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAdminEmpresas.fxml"));
            Parent vista = vistaLoad.load();
            
           // FXMLAdminUsuariosController controlador = vistaLoad.getController();
           // controlador.inicializarInformacion(usuarioSesion.getRolID());
           Stage stage = new Stage();
           Scene escena = new Scene(vista);
           stage.setScene(escena);
           stage.setTitle("Administrador de empresas");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    private void btnCerrarSesion(ActionEvent event) {
    }

    @FXML
    private void btnIrAdminUsuarios(ActionEvent event) {
        try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAdminUsuarios.fxml"));
            Parent vista = vistaLoad.load();
            
           // FXMLAdminUsuariosController controlador = vistaLoad.getController();
           // controlador.inicializarInformacion(usuarioSesion.getRolID());
           
           Stage stage = new Stage();
           Scene escena = new Scene(vista);
           stage.setScene(escena);
           stage.setTitle("Administrador de usuarios");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnIrAdminPromociones(ActionEvent event) {
        try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAdminPromociones.fxml"));
            Parent vista = vistaLoad.load();
            
           // FXMLAdminUsuariosController controlador = vistaLoad.getController();
           // controlador.inicializarInformacion(usuarioSesion.getRolID());
           
           Stage stage = new Stage();
           Scene escena = new Scene(vista);
           stage.setScene(escena);
           stage.setTitle("Administrador de promociones");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

    @FXML
    private void btnIrAdminCupones(ActionEvent event) {
        try{
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLAdminCupones.fxml"));
            Parent vista = vistaLoad.load();
            
           // FXMLAdminUsuariosController controlador = vistaLoad.getController();
           // controlador.inicializarInformacion(usuarioSesion.getRolID());
           
           Stage stage = new Stage();
           Scene escena = new Scene(vista);
           stage.setScene(escena);
           stage.setTitle("Administrador de promociones");
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.setResizable(false);
           stage.showAndWait();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
    public void inicializarGraficosMenu(){
    Image icEmpresa = new Image("recursos/empresa.png");
    ImageView imgEmpresa = new ImageView(icEmpresa);
    
    Image icSucursal = new Image("recursos/sucursal.png");
    ImageView imgSucursal  = new ImageView(icSucursal);
    
    Image icPromocion = new Image("recursos/discount.png");
    ImageView imgPromocion = new ImageView(icPromocion);
    
    Image icUsuario = new Image("recursos/usuario.png");
    ImageView imgUsuario = new ImageView(icUsuario);
    
    Image icCupon = new Image("recursos/voucher.png");
    ImageView imgCupon = new ImageView(icCupon);
        
        imgEmpresa.setFitHeight(80);
        imgEmpresa.setPreserveRatio(true);
        
        imgSucursal.setFitHeight(80);
        imgSucursal.setPreserveRatio(true);
        
        imgUsuario.setFitHeight(80);
        imgUsuario.setPreserveRatio(true);
        
        imgPromocion.setFitHeight(80);
        imgPromocion.setPreserveRatio(true);
         imgCupon.setFitHeight(80);
        imgCupon.setPreserveRatio(true);
        
        btnIrAdminEmpresas.setGraphic(imgEmpresa);
        btnIrAdminSucursales.setGraphic(imgSucursal);
        btnIrAdminUsuarios.setGraphic(imgUsuario);
        btnIrAdminPromociones.setGraphic(imgPromocion);
        btnIrAdminCupones.setGraphic(imgCupon);
        
    }
   
    
    
    
}
