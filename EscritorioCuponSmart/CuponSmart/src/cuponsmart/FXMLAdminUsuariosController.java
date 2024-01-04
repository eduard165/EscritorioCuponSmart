package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

public class FXMLAdminUsuariosController implements Initializable {

    private FXMLAdminUsuariosController adminUsuariosController;

    private ObservableList<Usuario> usuariosDisponibles;
    private FilteredList<Usuario> filteredListUsuarios;

    private Usuario usuarioSesion = new Usuario();
    private Usuario nUsuario = new Usuario();

    @FXML
    private TextField tfBusqueda;
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
    @FXML
    private Label lbUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuariosDisponibles = FXCollections.observableArrayList();
        filteredListUsuarios = new FilteredList<>(usuariosDisponibles);

        configurarColumnasTabla();
        inicializarGraficosAdmin();
        configurarBusqueda();

    }

    public void inicializarInformacion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
        consultarInformacion(usuarioSesion.getId_usuario());
        cargarInformacionUsuario();

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
            Utilidades.mostrarAlertaSimple("ERROR", "NO SE HA SELECCIONADO UN USUARIO EN LA TABLA ", Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnEliminarUsuario(ActionEvent event) {
        nUsuario = tbUsuarios.getSelectionModel().getSelectedItem();
        if (nUsuario != null) {
            Mensaje respuesta = UsuarioDAO.eliminarUsuario(nUsuario.getId_usuario());
            if (!respuesta.getError()) {
                Utilidades.mostrarAlertaSimple("Eliminacion correcta", "Se eliminó con éxito", Alert.AlertType.INFORMATION);
                actualizarTabla();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Selección de usuario", "Para eliminar, debes seleccionar un usuario de la tabla", Alert.AlertType.WARNING);
        }

    }

    public void setAdminUsuariosController(FXMLAdminUsuariosController controller) {
        this.adminUsuariosController = controller;
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

    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPat.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
        colApellidoMat.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
        colCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRol.setCellValueFactory(cellData -> cellData.getValue().nombreRolProperty());

    }

    private void consultarInformacion(Integer id_usuario) {
        MensajeUsuarios respuesta = UsuarioDAO.cargarUsuarios(id_usuario);
        if (!respuesta.isError() && respuesta.getMensaje() != null) {
            usuariosDisponibles.clear();
            usuariosDisponibles.addAll(respuesta.getUsuarios());
            tbUsuarios.setItems(usuariosDisponibles);
            tbUsuarios.refresh();
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
            controlador.setAdminUsuariosController(this);
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

    private void configurarBusqueda() {
        tfBusqueda.textProperty().addListener((textObservable, oldText, newText) -> {
            if (newText.isEmpty()) {
                filteredListUsuarios.setPredicate(null);
                return;
            }
            buscarUsuarios(newText);
        });
    }

    private void buscarUsuarios(String busqueda) {
        Predicate<Usuario> predicado = usuario
                -> usuario.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                || usuario.getUsername().toLowerCase().contains(busqueda.toLowerCase())
                || usuario.nombreRolProperty().getValue().toLowerCase().contains(busqueda.toLowerCase());

        filteredListUsuarios.setPredicate(predicado);
        tbUsuarios.setItems(filteredListUsuarios);
    }

    private void cargarInformacionUsuario() {
        lbUsuario.setText(usuarioSesion.getUsername());

        Image imagenUsuario = new Image("recursos/1.png");
        ivUsuarioSesionFoto.setImage(imagenUsuario);
    }

    public void actualizarTabla() {
        consultarInformacion(usuarioSesion.getId_usuario());
    }
}
