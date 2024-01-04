package cuponsmart;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.tools.Diagnostic;
import modelo.dao.DomicilioDAO;
import modelo.dao.EmpresaDAO;
import modelo.dao.SucursalDAO;
import modelo.pojo.Direccion;
import modelo.pojo.Empresa;
import modelo.pojo.Estado;
import modelo.pojo.Mensaje;
import modelo.pojo.Municipio;
import modelo.pojo.Sucursal;
import utils.Utilidades;

public class FXMLFormularioDomicilioController implements Initializable {

    private FXMLAdminSucursalesController adminSucursalesController;
    private FXMLAdminEmpresasController adminEmpresasController;

    Direccion direccion = new Direccion();
    String empresa_rfc = null;
    boolean esEdicion;
    Integer id_sucursal = 0;
    Empresa empresa = new Empresa();
    Sucursal sucursal = new Sucursal();
    private File fotografia;

    private ObservableList<Estado> estados;
    private ObservableList<Municipio> municipios;

    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private ComboBox<Municipio> cbMunicipio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarInformacionEstados();
        this.configuracionSeleccionEstado();
    }

    public void inicializarInformacionEmpresa(String empresa_rfc, boolean esEdicion, Empresa empresa, File fotografia) {
        this.empresa_rfc = empresa_rfc;
        this.empresa = empresa;
        this.esEdicion = esEdicion;
        this.fotografia = fotografia;
        if (esEdicion) {
            cargarInformacion();
        }
    }

    public void inicializarInformacionSucursal(Integer id_sucursal, boolean esEdicion, Sucursal sucursal) {
        this.esEdicion = esEdicion;
        if (esEdicion) {
            this.id_sucursal = id_sucursal;
            cargarInformacion();
        } else {
            this.id_sucursal = 1;
        }
    }

    @FXML
    private void btnGuardarDomicilio(ActionEvent event) {
        if (camposLlenos()) {
            if (empresa_rfc != null) {

                guardarInformacionEnDireccion();
                guardarOEditarEmpresa();
                subirLogoEmpresa();
            } else if (id_sucursal != 0) {
                guardarInformacionEnDireccion();
                guardarOEditarSucursal();
            }
            cerrarPantalla();
        } else {
            Utilidades.mostrarAlertaSimple("Campos incompletos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarPantalla();
    }

    private void cargarInformacion() {
        Direccion direccion = new Direccion();
        if (empresa_rfc != null) {
            direccion = DomicilioDAO.cargarDireccionEmpresa(empresa_rfc);
            if (direccion != null) {
                tfCalle.setText(direccion.getCalle());
                tfColonia.setText(direccion.getColonia());
                tfNumero.setText(String.valueOf(direccion.getNumero()));
                tfCodigoPostal.setText(direccion.getCodigo_postal());
                
            }
        }
        if (id_sucursal != 0) {

            direccion = DomicilioDAO.cargarDireccionSucursal(id_sucursal);
            if (direccion != null) {

                tfCalle.setText(direccion.getCalle());
                tfColonia.setText(direccion.getColonia());
                tfNumero.setText(String.valueOf(direccion.getNumero()));
                tfCodigoPostal.setText(direccion.getCodigo_postal());
            }
        }
    }

    public void guardarInformacionEnDireccion() {
        if (empresa_rfc != null) {
            direccion.setTipo_direccion(1);
            direccion.setEmpresa_rfc(empresa_rfc);
        }
        if (id_sucursal != 0) {
            direccion.setTipo_direccion(3);
            direccion.setId_sucursal(id_sucursal);
        }
        direccion.setCalle(tfCalle.getText());
        direccion.setColonia(tfColonia.getText());
        direccion.setNumero(Integer.parseInt(tfNumero.getText()));
        direccion.setCodigo_postal(tfCodigoPostal.getText());
        
        Municipio municipioSeleccionado = cbMunicipio.getValue();
        if (municipioSeleccionado != null) {
            direccion.setId_municipio(municipioSeleccionado.getId_municipio());
        }
    }

    private void cargarInformacionEstados() {
        estados = FXCollections.observableArrayList();
        List<Estado> info = DomicilioDAO.obtenerEstados();
        estados.addAll(info);
        cbEstado.setItems(estados);

    }

    private void cargarInformacionMunicipios(int idEstado) {
        municipios = FXCollections.observableArrayList();
        List<Municipio> inf = DomicilioDAO.obtenerMunicipios(idEstado);
        municipios.addAll(inf);
        cbMunicipio.setItems(municipios);
    }

    private void configuracionSeleccionEstado() {
        cbEstado.valueProperty().addListener(new ChangeListener<Estado>() {
            @Override
            public void changed(ObservableValue<? extends Estado> observable, Estado oldValue, Estado newValue) {
                cargarInformacionMunicipios(newValue.getId_estado());
            }
        });
    }

    public void guardarOEditarEmpresa() {
        try {

            Mensaje respuesta;
            Mensaje respuesta2;

            if (!esEdicion) {
                respuesta = EmpresaDAO.editarEmpresa(empresa);
                respuesta2 = DomicilioDAO.editarDireccion(direccion);
            } else {
                respuesta = EmpresaDAO.registrarEmpresa(empresa);
                respuesta2 = DomicilioDAO.registrarDireccion(direccion);

            }

            if (!respuesta.getError()) {
                Utilidades.mostrarAlertaSimple("Operación exitosa", respuesta.getMensaje() + "\n" + respuesta2.getMensaje(), Alert.AlertType.INFORMATION);
                adminEmpresasController.actualizarTabla();
                cerrarPantalla();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje() + "\n" + respuesta2.getMensaje(), Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarOEditarSucursal() {
        try {
            Mensaje respuesta;
            Mensaje respuesta2;

            if (!esEdicion) {
                respuesta = SucursalDAO.editarSucursal(sucursal);
                respuesta2 = DomicilioDAO.editarDireccion(direccion);
            } else {
                respuesta = SucursalDAO.registrarSucursal(sucursal);
                respuesta2 = DomicilioDAO.registrarDireccion(direccion);
            }

            if (!respuesta.getError()) {
                Utilidades.mostrarAlertaSimple("Operación exitosa", respuesta.getMensaje() + "\n" + respuesta2.getMensaje(), Alert.AlertType.INFORMATION);
                adminSucursalesController.actualizarTabla();
                cerrarPantalla();
            } else {
                Utilidades.mostrarAlertaSimple("Error", respuesta.getMensaje() + "\n" + respuesta2.getMensaje(), Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarPantalla() {
        Stage stage = (Stage) tfCalle.getScene().getWindow();
        stage.close();
    }

    private void subirLogoEmpresa() {
        if (fotografia != null) {
            Mensaje rest = EmpresaDAO.subirImagenEmpresa(empresa.getRfc(), fotografia);
            if (!rest.getError()) {
                Utilidades.mostrarAlertaSimple("Fotografia guardada", rest.getMensaje(), Alert.AlertType.INFORMATION);
            } else {
                Utilidades.mostrarAlertaSimple("Error al guardar", rest.getMensaje(), Alert.AlertType.ERROR);
            }
        }
    }

    private boolean camposLlenos() {
        if (tfCalle.getText().isEmpty() || tfColonia.getText().isEmpty()
                || tfNumero.getText().isEmpty() || tfCodigoPostal.getText().isEmpty()) {
            return false;
        }

        if (cbEstado.getValue() == null || cbMunicipio.getValue() == null) {
            return false;
        }

        return true;
    }

    public void setAdminSucursalesController(FXMLAdminSucursalesController controller) {
        this.adminSucursalesController = controller;
    }

    public void setAdminEmpresasController(FXMLAdminEmpresasController controller) {
        this.adminEmpresasController = controller;
    }
}
