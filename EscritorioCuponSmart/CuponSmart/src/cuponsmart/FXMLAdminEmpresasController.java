package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.dao.EmpresaDAO;
import modelo.dao.UsuarioDAO;
import modelo.pojo.Empresa;
import modelo.pojo.MensajeUsuarios;
import modelo.pojo.Usuario;
import utils.Utilidades;

public class FXMLAdminEmpresasController implements Initializable {

    private ObservableList<Empresa> Empresas;
    private Usuario usuarioSesion = new Usuario();

    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private TableView<Empresa> tbEmpresas;
    @FXML
    private TableColumn<Empresa, String> colRFC;
    @FXML
    private TableColumn<Empresa, String> colNombre;
    @FXML
    private TableColumn<Empresa, String> colNombreComercial;
    @FXML
    private TableColumn<Empresa, String> colTelefono;
    @FXML
    private TableColumn<Empresa, String> colEmail;
    @FXML
    private TableColumn<Empresa, String> colRepLegal;
    @FXML
    private TextField tfBusquedaEmpresa;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnRegistroEmpresa;
    @FXML
    private Button btnEdicionEmpresa;
    @FXML
    private Button btnEliminarEmpresa;
    @FXML
    private Button btnFormularioDom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Empresas = FXCollections.observableArrayList();
        inicializarGraficosAdmin();
    }

    @FXML
    private void btnRegistroEmpresa(ActionEvent event) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioEmpresa.fxml"));
            Parent vista = vistaLoad.load();

            //Agregar paso de información de la sesión 
            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Registro de empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEdicionEmpresa(ActionEvent event) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioEmpresa.fxml"));
            Parent vista = vistaLoad.load();
            //Agregar paso de información de la sesión 

            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Editar empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
    }

    @FXML
    private void btnIrFormularioDomicilio(ActionEvent event) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioDomicilio.fxml"));
            Parent vista = vistaLoad.load();

            //Agregar paso de información de la sesión 
            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Registro de domicilio");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarInformacion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombreComercial.setCellValueFactory(new PropertyValueFactory<>("nombre_comercial"));
        colRFC.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        colRepLegal.setCellValueFactory(new PropertyValueFactory<>("representante_legal"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    private void consultarInformacion() {
        if (usuarioSesion.getId_rol() == 1) {
            List<Empresa> respuesta = EmpresaDAO.cargarEmpresas();
            if(respuesta != null){
                Empresas.addAll(respuesta);
                tbEmpresas.setItems(Empresas);
            }else{
                Utilidades.mostrarAlertaSimple("ERROR", "Hubo un error en carga la tabla", Alert.AlertType.WARNING);
            }
        } else {
            Empresa respuesta = EmpresaDAO.cargarEmpresasAsociadas(usuarioSesion.getEmpresa_rfc());
            if(respuesta != null){
                Empresas.addAll(respuesta);
                tbEmpresas.setItems(Empresas);
            }else{
                Utilidades.mostrarAlertaSimple("ERROR", "Hubo un error en carga la tabla", Alert.AlertType.WARNING);
            }
        }

        
    }

    public void actualizarTabla() {
        Empresas.clear();
        consultarInformacion();
    }

    public void inicializarGraficosAdmin() {
        Image icAgregar = new Image("recursos/agregar.png");
        ImageView ivAgregar = new ImageView(icAgregar);
        ivAgregar.setFitHeight(50);
        ivAgregar.setPreserveRatio(true);

        btnRegistroEmpresa.setGraphic(ivAgregar);

        Image icEditar = new Image("recursos/editar.png");
        ImageView ivEditar = new ImageView(icEditar);
        ivEditar.setFitHeight(50);
        ivEditar.setPreserveRatio(true);

        btnEdicionEmpresa.setGraphic(ivEditar);

        Image icBorrar = new Image("recursos/borrar.png");
        ImageView ivBorrar = new ImageView(icBorrar);
        ivBorrar.setFitHeight(50);
        ivBorrar.setPreserveRatio(true);

        btnEliminarEmpresa.setGraphic(ivBorrar);

        Image icDomicilio = new Image("recursos/direccion.png");
        ImageView ivDomicilio = new ImageView(icDomicilio);
        ivDomicilio.setFitHeight(50);
        ivDomicilio.setPreserveRatio(true);
        btnFormularioDom.setGraphic(ivDomicilio);

    }

    private void abrirFormulario(boolean esEdicion) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioUsuario.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormularioUsuarioController controlador = vistaLoad.getController();
            controlador.inicializarInformacion(nUsuario, esEdicion);

            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle(esEdicion ? "Editar un usuario" : "Registro de usuario");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
