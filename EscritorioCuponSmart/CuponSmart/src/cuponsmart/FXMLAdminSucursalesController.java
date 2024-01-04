package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import modelo.dao.DomicilioDAO;
import modelo.dao.SucursalDAO;
import modelo.pojo.Direccion;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import modelo.pojo.Usuario;
import utils.Utilidades;

public class FXMLAdminSucursalesController implements Initializable {

    private Usuario usuarioSesion = new Usuario();
    private Sucursal nSucursal = new Sucursal();
    private ObservableList<Sucursal> sucursalesDisponibles;
    private FilteredList<Sucursal> filteredListSucursales;

    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Button btnRegistroSuc;
    @FXML
    private Button btnEdicionSuc;
    @FXML
    private Button btnEliminarSuc;
    @FXML
    private TextField tfBusquedaSucursal;
    @FXML
    private TableView<Sucursal> tbSucursales;
    @FXML
    private TableColumn<Sucursal, String> clNombreSucursal;
    @FXML
    private TableColumn<Sucursal, String> clEncargado;
    @FXML
    private TableColumn<Sucursal, String> clEmpresa;
    @FXML
    private TableColumn<Sucursal, String> clLongitud;
    @FXML
    private TableColumn<Sucursal, String> clLatitud;
    @FXML
    private Label lbDireccion;
    @FXML
    private Label lbInformacionDireccion;
    @FXML
    private TableColumn<Sucursal, String> clTelefono;
    @FXML
    private Label lbUsuarioSesion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sucursalesDisponibles = FXCollections.observableArrayList();
        filteredListSucursales = new FilteredList<>(sucursalesDisponibles);
        configurarColumnasTabla();
        inicializarGraficosAdmin();
        configurarBusqueda();
        configurarSeleccionTabla();
        consultarInformacion();

    }

    public void inicializarInformacion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
        consultarInformacion();
        cargarInformacionUsuario();

    }

    @FXML
    private void btnRegistroSucursal(ActionEvent event) {
        abrirFormulario(false);

    }

    @FXML
    private void btnEdicionSucursal(ActionEvent event) {
        nSucursal = tbSucursales.getSelectionModel().getSelectedItem();
        if (nSucursal != null) {
            abrirFormulario(true);
        } else {
            Utilidades.mostrarAlertaSimple("ERROR", "NO SE HA SELECCIONADO UN USUARIO EN LA TABLA ", Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void btnEliminarSucursal(ActionEvent event) {
        nSucursal = tbSucursales.getSelectionModel().getSelectedItem();
        if (nSucursal != null) {
            Mensaje respuesta = SucursalDAO.eliminarSucursal(nSucursal.getId_sucursal());
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

    public void inicializarGraficosAdmin() {
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

    }

    private void configurarColumnasTabla() {
        clEncargado.setCellValueFactory(new PropertyValueFactory<>("nombre_encargado"));
        clEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        clLatitud.setCellValueFactory(new PropertyValueFactory<>("latitud"));
        clLongitud.setCellValueFactory(new PropertyValueFactory<>("longitud"));
        clNombreSucursal.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    private void consultarInformacion() {
        List<Sucursal> respuesta = SucursalDAO.cargarSucursales();
        if (!respuesta.isEmpty()) {
            sucursalesDisponibles.clear();
            sucursalesDisponibles.addAll(respuesta);
            tbSucursales.setItems(sucursalesDisponibles);
            tbSucursales.refresh();
        } else {
            Utilidades.mostrarAlertaSimple("Error", "No se pudieron cargar las empresas", Alert.AlertType.ERROR);
        }
    }

    private void configurarSeleccionTabla() {
        tbSucursales.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarInformacionDireccion(newSelection);
            }
        });
    }

    private void cargarInformacionDireccion(Sucursal sucursal) {
        Direccion direccion = DomicilioDAO.cargarDireccionSucursal(sucursal.getId_sucursal());
        if (direccion != null) {
            lbInformacionDireccion.setText(direccion.toString());
        } else {
            Utilidades.mostrarAlertaSimple("Error", "No se pudo cargar la dirección", Alert.AlertType.ERROR);
        }
    }

    private void cargarInformacionUsuario() {
        lbUsuarioSesion.setText(usuarioSesion.getUsername());

        Image imagenUsuario = new Image("recursos/1.png");
        ivUsuarioSesionFoto.setImage(imagenUsuario);
    }

    public void actualizarTabla() {
        consultarInformacion();
    }

    private void configurarBusqueda() {
        tfBusquedaSucursal.textProperty().addListener((textObservable, oldText, newText) -> {
            if (newText.isEmpty()) {
                filteredListSucursales.setPredicate(null);
                return;
            }
            buscarUsuarios(newText);
        });
    }

    private void buscarUsuarios(String busqueda) {
        Predicate<Sucursal> predicado = usuario
                -> usuario.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                || usuario.getNombre_empresa().toLowerCase().contains(busqueda.toLowerCase())
                || usuario.getTelefono().toLowerCase().contains(busqueda.toLowerCase());

        filteredListSucursales.setPredicate(predicado);
        tbSucursales.setItems(filteredListSucursales);
    }

    private void abrirFormulario(boolean esEdicion) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioSucursal.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormularioSucursalController controlador = vistaLoad.getController();
            controlador.inicializarInformacion(nSucursal, esEdicion);
            controlador.setAdminSucursalesController(this);
            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle(esEdicion ? "Editar un sucursal" : "Registro de sucursal");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
