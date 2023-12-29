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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.UsuarioDAO;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import modelo.pojo.MensajeUsuarios;
import modelo.pojo.Usuario;
import utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioUsuarioController implements Initializable {

    private ObservableList<String> id_rol;
    private ObservableList<Empresa> empresa;

    private Empresa cEmpresa = new Empresa();
    private Usuario usuario = new Usuario();
    private boolean band;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoMat;
    @FXML
    private TextField tfApellidoPat;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfUser;
    @FXML
    private ComboBox<String> cbRol;
    @FXML
    private ComboBox<Empresa> cbEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarInformacionEmpresas();
        ObservableList<String> roles = FXCollections.observableArrayList("Usuario General", "Usuario Comercial");
        cbRol.setItems(roles);
    }

    public void inicializarInformacion(Usuario usuario, boolean band) {
        this.usuario = usuario;
        this.band = band;
        if (!band) {
            cargarUsuario();
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        Stage stage = (Stage) tfApellidoMat.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnGuardarUsuario(ActionEvent event) {

        if (band) {
            registrarUsuario();

        } else {
            editarUsuario();

        }

    }

    public void editarUsuario() {
        descargarUsuario();
        if (usuario != null) {
            Mensaje respuesta = UsuarioDAO.editarUsuario(usuario);
            if (!respuesta.getError()) {
                actualizarTablaEnVentanaPrincipal();
                Stage stage = (Stage) tfApellidoMat.getScene().getWindow();
                stage.close();
                cambiarFormularioDireecion();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Selección de usuario", "Para eliminar, debes seleccionar un paciente de la tabla", Alert.AlertType.WARNING);
        }
    }

    public void registrarUsuario() {
        descargarUsuario();
        if (usuario != null) {

            Mensaje respuesta = UsuarioDAO.editarUsuario(usuario);
            if (!respuesta.getError()) {
                actualizarTablaEnVentanaPrincipal();
                Stage stage = (Stage) tfApellidoPat.getScene().getWindow();
                stage.close();
                cambiarFormularioDireecion();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        } else {
            Utilidades.mostrarAlertaSimple("ERROR", "Para guardar debes llenar todos los campos", Alert.AlertType.WARNING);
        }
    }

    private void actualizarTablaEnVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminUsuarios.fxml"));
            Parent root = loader.load();
            FXMLAdminUsuariosController adminUsuariosController = loader.getController();

            adminUsuariosController.actualizarTabla();
        } catch (Exception e) {
        }
    }

    private void cargarInformacionEmpresas() {
        empresa = FXCollections.observableArrayList();
        List<Empresa> inf = UsuarioDAO.obtenerEmpresas();
        empresa.addAll(inf);
        cbEmpresa.setItems(empresa);
    }

    private void cargarUsuario() {
        // FALTAN CAMPOS 
        tfNombre.setText(usuario.getNombre());
        tfApellidoPat.setText(usuario.getApellido_paterno());
        tfApellidoMat.setText(usuario.getApellido_materno());
        tfPassword.setText(usuario.getPassword());
        tfUser.setText(usuario.getUsername());
        cbEmpresa.getSelectionModel().select(buscarIndicePorRFC(usuario.getEmpresa_rfc()));
        cbRol.getSelectionModel().select(usuario.getId_rol());
    }

    private int buscarIndicePorRFC(String rfc) {
        if (empresa != null && !empresa.isEmpty()) {
            for (int i = 0; i < empresa.size(); i++) {
                if (empresa.get(i).getRfc().equals(rfc)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void cambiarFormularioDireecion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SiguienteVentana.fxml"));
            Parent root = loader.load();

            FXMLFormularioDomicilioController controller = loader.getController();
            // controller.inicializarDatos();

            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(root));
            nuevaVentana.setTitle("Registro de domicilio");
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void descargarUsuario() {
        // FALTAN CAMPOS 

        usuario.setNombre(tfNombre.getText());
        usuario.setApellido_paterno(tfApellidoPat.getText());
        usuario.setApellido_materno(tfApellidoMat.getText());
        usuario.setUsername(tfUser.getText());
        usuario.setPassword(tfPassword.getText());

        if (usuario.getEmpresa_rfc() != null || usuario.getEmpresa_rfc().isEmpty()) {
            Empresa empresa = cbEmpresa.getSelectionModel().getSelectedItem();
            usuario.setEmpresa_rfc(empresa.getRfc());
        }
        if (usuario.getId_rol() != null) {
            Integer rolSeleccionado = cbRol.getSelectionModel().getSelectedIndex();
            if (rolSeleccionado >= 0) {
                usuario.setId_rol(rolSeleccionado);
            }
        }
    }
}
