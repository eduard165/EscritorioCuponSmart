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
import modelo.pojo.Mensaje;
import modelo.pojo.Usuario;
import utils.Utilidades;

public class FXMLAdminEmpresasController implements Initializable {

    private ObservableList<Empresa> Empresas;
    private Usuario usuarioSesion = new Usuario();
    private Empresa empresa = new Empresa();

    @FXML
    private Label lbUsuarioSesion;

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
    private TableView<Empresa> tvEmpresas;
    @FXML
    private TableColumn<Empresa, String> tcRFC;
    @FXML
    private TableColumn<Empresa, String> tcNombre;
    @FXML
    private TableColumn<Empresa, String> tcNombreComercial;
    @FXML
    private TableColumn<Empresa, String> tcTelefono;
    @FXML
    private TableColumn<Empresa, String> tcEmail;
    @FXML
    private TableColumn<Empresa, String> tcRepLegal;
    @FXML
    private TableColumn<Empresa, String> tcStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Empresas = FXCollections.observableArrayList();
        inicializarGraficosAdmin();
        configurarColumnasTabla();
    }

    @FXML
    private void btnRegistroEmpresa(ActionEvent event) {
        abrirFormulario(true);
    }

    @FXML
    private void btnEdicionEmpresa(ActionEvent event) {
        empresa = tvEmpresas.getSelectionModel().getSelectedItem();
        if (empresa != null) {
            abrirFormulario(false);

        } else {
            Utilidades.mostrarAlertaSimple("ERROR", "NO SE HA SELECCIONADO UNA EMPRESA EN LA TABLA", Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnEliminarEmpresa(ActionEvent event) {
        empresa = tvEmpresas.getSelectionModel().getSelectedItem();
        if (empresa != null) {
            Mensaje respuesta = EmpresaDAO.eliminarEmpresa(empresa.getRfc());
            if (!respuesta.getError()) {
                Utilidades.mostrarAlertaSimple("Eliminacion correcta", "Se eliminó con éxito", Alert.AlertType.INFORMATION);
                actualizarTabla();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Seleccion Empresa", "Para eliminar, debes seleccionar una empresa de la tabla", Alert.AlertType.WARNING);
        }
    }

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

    public void inicializarInformacion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
        consultarInformacion();
    }

    private void configurarColumnasTabla() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcNombreComercial.setCellValueFactory(new PropertyValueFactory<>("nombre_comercial"));
        tcRFC.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        tcRepLegal.setCellValueFactory(new PropertyValueFactory<>("representante_legal"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().nombreStatusProperty());
    }

    private void consultarInformacion() {
        if (usuarioSesion.getId_rol() == 1) {
            List<Empresa> respuesta = EmpresaDAO.cargarEmpresas();
            if (respuesta != null) {
                Empresas.addAll(respuesta);
                tvEmpresas.setItems(Empresas);
            } else {
                Utilidades.mostrarAlertaSimple("ERROR", "Hubo un error en carga la tabla", Alert.AlertType.WARNING);
            }
        } else {
            Empresa respuesta = EmpresaDAO.cargarEmpresasAsociadas(usuarioSesion.getEmpresa_rfc());
            if (respuesta != null) {
                Empresas.addAll(respuesta);
                tvEmpresas.setItems(Empresas);
            } else {
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
    }

    private void abrirFormulario(boolean esEdicion) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioEmpresa.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormularioEmpresaController controlador = vistaLoad.getController();
            controlador.inicializarInformacion(empresa, esEdicion);
            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle(esEdicion ? "Editar  empresa" : "Registrar empresa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
