package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import modelo.dao.UsuarioDAO;
import modelo.pojo.Mensaje;
import modelo.pojo.MensajeUsuarios;
import modelo.pojo.Usuario;
import utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminUsuariosController implements Initializable {

    private ObservableList<Usuario> usuariosDisponibles;
    private Usuario usuarioSesion = new Usuario();
    private Usuario nUsuario = new Usuario();

    @FXML
    private TextField tfBusqueda;
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnRegistroUsuario;
    @FXML
    private Button btnEdicionUsuario;
    @FXML
    private Button btnEliminarUsuario;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colApellidoPat;
    @FXML
    private TableColumn<Usuario, String> colApellidoMat;
    @FXML
    private TableColumn<Usuario, String> colUser;
    @FXML
    private TableColumn<Usuario, String> colCorreoElectronico;
    @FXML
    private TableColumn<Usuario, String> colRol;
    @FXML
    private TableView<Usuario> tbUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        usuariosDisponibles = FXCollections.observableArrayList();
        configurarColumnasTabla();
        inicializarGraficosAdmin();
    }

    public void inicializarInformacion(Usuario usuarioSesion) {

        this.usuarioSesion = usuarioSesion;
        consultarInformacion();
    }

    @FXML
    private void btnMenuPrincipal(ActionEvent event) {
    }

    @FXML
    private void btnRegistroUsuario(ActionEvent event) {
        abrirFormulario(true);
    }

    @FXML
    private void btnEdicionUsuario(ActionEvent event) {
        nUsuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (nUsuario != null) {
            abrirFormulario(false);
        } else {
            Utilidades.mostrarAlertaSimple("ERROR", "NO SE HA SELECCIONADO UN USUARIO EN LA TABLA PRIMERO", Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
        nUsuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (nUsuario != null) {

            Mensaje respuesta = UsuarioDAO.editarUsuario(nUsuario);
            if (!respuesta.getError()) {
                Utilidades.mostrarAlertaSimple("Registro correcto", "Se eliminó con éxito", Alert.AlertType.INFORMATION);
                actualizarTabla();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Selección de usuario", "Para eliminar, debes seleccionar un paciente de la tabla", Alert.AlertType.WARNING);
        }
        
    }

    public void inicializarGraficosAdmin() {
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

    public void actualizarTabla() {
        usuariosDisponibles.clear();
        consultarInformacion();
    }

    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPat.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
        colApellidoMat.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
        colCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRol.setCellValueFactory(cellData -> cellData.getValue().nombreRolProperty());

    }

    private void consultarInformacion() {
        MensajeUsuarios respuesta = UsuarioDAO.cargarUsuarios();
        if (!respuesta.isError() && respuesta.getMensaje() != null) {
            usuariosDisponibles.addAll(respuesta.getUsuarios());
            tbUsuarios.setItems(usuariosDisponibles);
        } else {
            Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
        }
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
