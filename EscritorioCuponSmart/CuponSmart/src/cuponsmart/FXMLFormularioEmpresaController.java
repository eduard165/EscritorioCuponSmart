package cuponsmart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import modelo.dao.EmpresaDAO;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import utils.Utilidades;

public class FXMLFormularioEmpresaController implements Initializable {

    boolean esEdicion;
    Empresa empresa = new Empresa();
    private File fotografia;

    @FXML
    private TextField tfRepLegal;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfNombreEmpresa;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private ImageView ivLogoEmpresa;
    @FXML
    private RadioButton rbActivo;
    @FXML
    private ToggleGroup groupEstatus;
    @FXML
    private RadioButton rbInactivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void inicializarInformacion(Empresa empresa, boolean esEdicion) {
        this.empresa = empresa;
        this.esEdicion = esEdicion;
        if (!esEdicion) {
            cargarEmpresa();
        }
    }

    @FXML
    private void btnGuardarEmpresa(ActionEvent event) {
        if (verificarCamposLlenos()) {
            guardarOEditarEmpresa();
            subirLogo();
            irFormularioDomicilio();
            cerrarPantalla();
        }

    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarPantalla();
    }

    @FXML
    private void btnBuscarLogo(ActionEvent event) {
        fotografia = mostrarDialogoSeleccionado();
        if (fotografia != null) {
            mostrarImagenSeleccionada(fotografia);
        }
    }

    public void validarCamposLlenos() {

    }

    private void irFormularioDomicilio() {
        try {
            Stage stagePrincipal = (Stage) tfNombreEmpresa.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLFormularioDomicilio.fxml"));
            Parent vista = loadVista.load();

            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cargarEmpresa() {
        tfRepLegal.setText(empresa.getRepresentante_legal());
        tfRFC.setText(empresa.getRfc());
        tfNombreEmpresa.setText(empresa.getNombre());
        tfNombreComercial.setText(empresa.getNombre_comercial());
        tfTelefono.setText(empresa.getTelefono());
        tfEmail.setText(empresa.getEmail());
        tfPaginaWeb.setText(empresa.getPagina_web());

        if (empresa.getId_estatus() == 1) {
            rbActivo.setSelected(true);
        } else {
            rbInactivo.setSelected(true);
        }

    }

    private File mostrarDialogoSeleccionado() {
        FileChooser dialogoSeleccionImg = new FileChooser();
        dialogoSeleccionImg.setTitle("Selecciona una imagen");
        
        FileChooser.ExtensionFilter filtroArchivos = new FileChooser.ExtensionFilter("Archivos PNG(*.png,*.jpg,*.jpeg)", "*.PNG", "*.JPG", "*.JPEG");
        dialogoSeleccionImg.getExtensionFilters().add(filtroArchivos);

        Stage escena = (Stage) tfPaginaWeb.getScene().getWindow();
        return dialogoSeleccionImg.showOpenDialog(escena);
    }

    private void mostrarImagenSeleccionada(File foto) {
        try {
            BufferedImage buffer = ImageIO.read(foto);
            Image image = SwingFXUtils.toFXImage(buffer, null);
            ivLogoEmpresa.setImage(image);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("ERROR", "Hubo un error al cargar la imagen", Alert.AlertType.ERROR);
        }
    }

    public void guardarOEditarEmpresa() {
        try {
            if (empresa != null) {
                guardarInformacionEnEmpresa();

                Mensaje respuesta;
                if (!esEdicion) {
                    respuesta = EmpresaDAO.editarEmpresa(empresa);
                } else {
                    respuesta = EmpresaDAO.registrarEmpresa(empresa);
                }

                if (!respuesta.getError()) {
                    Utilidades.mostrarAlertaSimple("Operación exitosa", respuesta.getMensaje(), Alert.AlertType.INFORMATION);
                    actualizarTablaEnVentanaPrincipal();
                    cerrarPantalla();
                } else {
                    Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje(), Alert.AlertType.ERROR);
                }
            } else {
                Utilidades.mostrarAlertaSimple("Selección de empresa", "Debe seleccionar una empresa", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarTablaEnVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAdminEmpresa.fxml"));
            Parent root = loader.load();
            FXMLAdminEmpresasController adminUsuariosController = loader.getController();
            adminUsuariosController.actualizarTabla();
        } catch (Exception e) {
        }
    }

    private void cerrarPantalla() {
        Stage stage = (Stage) tfEmail.getScene().getWindow();
        stage.close();
    }

    public void guardarInformacionEnEmpresa() {
        empresa.setRepresentante_legal(tfRepLegal.getText());
        empresa.setRfc(tfRFC.getText());
        empresa.setNombre(tfNombreEmpresa.getText());
        empresa.setNombre_comercial(tfNombreComercial.getText());
        empresa.setTelefono(tfTelefono.getText());
        empresa.setEmail(tfEmail.getText());
        empresa.setPagina_web(tfPaginaWeb.getText());

        if (rbActivo.isSelected()) {
            empresa.setId_estatus(1);
        } else {
            empresa.setId_estatus(2);
        }
    }

    public boolean verificarCamposLlenos() {
        boolean camposLlenos = true;

        if (tfRepLegal.getText().isEmpty() || tfRFC.getText().isEmpty() || tfNombreEmpresa.getText().isEmpty()
                || tfNombreComercial.getText().isEmpty() || tfTelefono.getText().isEmpty()
                || tfEmail.getText().isEmpty() || tfPaginaWeb.getText().isEmpty()
                || (!rbActivo.isSelected() && !rbInactivo.isSelected()) || ivLogoEmpresa.getImage() == null) {
            camposLlenos = false;
        }

        if (!camposLlenos) {
            Utilidades.mostrarAlertaSimple("Campos incompletos", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
        }

        return camposLlenos;
    }

    private void subirLogo() {
        if (fotografia != null) {
            Mensaje rest = EmpresaDAO.subirImagenEmpresa(empresa.getRfc(), fotografia);
            if (!rest.getError()) {
                Utilidades.mostrarAlertaSimple("Fotografia guardada", rest.getMensaje(), Alert.AlertType.INFORMATION);
            } else {
                Utilidades.mostrarAlertaSimple("Error al guardar", rest.getMensaje(), Alert.AlertType.ERROR);
            }
        }
    }

}
