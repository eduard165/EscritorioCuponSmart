/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.dao.EmpresaDAO;
import modelo.pojo.Empresa;
import modelo.pojo.Sucursal;
import modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLFormularioSucursalController implements Initializable {

    private FXMLAdminSucursalesController adminSucursalController;
    private Sucursal sucursal = new Sucursal();
    boolean esEdicion;
    private ObservableList<Empresa> empresa;

    @FXML
    private TextField tfNombreSucursal;
    @FXML
    private TextField tfNombreEncargado;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    @FXML
    private ComboBox<Empresa> cbEmpresaSucursal;
    @FXML
    private TextField tfTelefono;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnGuardarPromocion(ActionEvent event) {
        irFormularioDomicilio();
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
         Stage stage = (Stage) tfLatitud.getScene().getWindow();
        stage.close();
    }

    public void inicializarInformacion(Sucursal sucursal, boolean esEdicion) {
        this.sucursal = sucursal;
        this.esEdicion = esEdicion;
        if (esEdicion) {
            cargarInformacionSucursal();
            
        }

    }

    private void irFormularioDomicilio() {
        try {
            Stage stagePrincipal = (Stage) tfNombreSucursal.getScene().getWindow();
            FXMLLoader loadVista = new FXMLLoader(getClass().getResource("FXMLFormularioDomicilio.fxml"));
            Parent vista = loadVista.load();

            Scene scene = new Scene(vista);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setAdminSucursalesController(FXMLAdminSucursalesController controller) {
        this.adminSucursalController = controller;
    }

    private void cargarInformacionSucursal() {
        tfNombreSucursal.setText(sucursal.getNombre());
        tfTelefono.setText(sucursal.getTelefono());
        tfNombreEncargado.setText(sucursal.getNombre_encargado());
        tfLatitud.setText(sucursal.getLatitud().toString());
        tfLongitud.setText(sucursal.getLongitud().toString());
        cargarEmpresasEnComboBox();
        String rfcUsuario = sucursal.getEmpresa_rfc();
        int indiceEmpresa = buscarIndicePorRFC(rfcUsuario);
        if (indiceEmpresa != -1) {
            cbEmpresaSucursal.getSelectionModel().select(indiceEmpresa);
        }
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
    private void cargarEmpresasEnComboBox() {
        List<Empresa> inf = EmpresaDAO.cargarEmpresas();
        empresa.addAll(inf);
        cbEmpresaSucursal.setItems(empresa);
    }

    private boolean camposEstanVacios() {
        return tfNombreSucursal.getText().isEmpty()
                && tfTelefono.getText().isEmpty()
                && tfNombreEncargado.getText().isEmpty()
                && tfLatitud.getText().isEmpty()
                && tfLongitud.getText().isEmpty()
                && cbEmpresaSucursal.getValue() == null;
    }

    private void abrirFormulario(boolean esEdicion) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioSucursal.fxml"));
            Parent vista = vistaLoad.load();

            FXMLFormularioDomicilioController controlador = vistaLoad.getController();
            controlador.inicializarInformacionSucursal(sucursal.getId_sucursal(), esEdicion, sucursal);
            controlador.setAdminSucursalesController(adminSucursalController);
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
