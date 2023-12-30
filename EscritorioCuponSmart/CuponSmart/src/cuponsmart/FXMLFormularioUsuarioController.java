package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    int selectedIndex;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuracionSeleccionEstado();
        ObservableList<String> roles = FXCollections.observableArrayList("Usuario General", "Usuario Comercial");
        cbRol.setItems(roles);
        cbEmpresa.setEditable(false);
        cargarDatosComboBox();

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
        if (camposEstanLlenos()) {
            if (band) {
                registrarUsuario();

            } else {
                editarUsuario();

            }
        } else {
            Utilidades.mostrarAlertaSimple("Campos vacíos", "Por favor, llena todos los campos obligatorios.", Alert.AlertType.WARNING);

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

            Mensaje respuesta = UsuarioDAO.registrarUsuario(usuario);
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

    private void cargarInformacionEmpresas(String rolSeleccionado) {
        if (rolSeleccionado != null) {
            empresa = FXCollections.observableArrayList();
            List<Empresa> inf = UsuarioDAO.obtenerEmpresas();
            empresa.addAll(inf);
            cbEmpresa.setItems(empresa);
        }
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

        if (usuario.getEmpresa_rfc() != null) {
            cEmpresa = cbEmpresa.getSelectionModel().getSelectedItem();
            usuario.setEmpresa_rfc(cEmpresa.getRfc());
        }
        if (usuario.getId_rol() != null) {
            Integer rolSeleccionado = cbRol.getSelectionModel().getSelectedIndex();
            if (rolSeleccionado >= 0) {
                usuario.setId_rol(rolSeleccionado);
            }
        }
    }

    private boolean camposEstanLlenos() {
        boolean rst = tfNombre.getText().isEmpty()
                && !tfApellidoPat.getText().isEmpty()
                && !tfApellidoMat.getText().isEmpty()
                && !tfPassword.getText().isEmpty()
                && !tfUser.getText().isEmpty()
                && cbRol.getSelectionModel().getSelectedItem() != null;

        if (selectedIndex == 1) {
            rst = cbEmpresa.getSelectionModel().getSelectedItem() != null;
        }
        return rst;
    }

    private void configuracionSeleccionEstado() {
        cbRol.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedIndex = cbRol.getSelectionModel().getSelectedIndex();

                if (selectedIndex == 0) {
                    cbEmpresa.setDisable(false);

                } else if (selectedIndex == 1) {
                    cbEmpresa.setDisable(true);
                    cargarInformacionEmpresas(newValue);

                }
            }
        });
        cbRol.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedIndex = newValue.intValue();
            }
        });
    }
    
    private void cargarDatosComboBox() {
    ObservableList<String> roles = FXCollections.observableArrayList("Usuario General", "Usuario Comercial");
    cbRol.setItems(roles);
    cbEmpresa.setEditable(false);
    cargarInformacionEmpresas(null); 
}

}
