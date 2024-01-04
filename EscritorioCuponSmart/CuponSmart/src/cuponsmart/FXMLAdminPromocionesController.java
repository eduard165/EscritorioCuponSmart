/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponsmart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import modelo.pojo.Promocion;
import modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author lizet
 */
public class FXMLAdminPromocionesController implements Initializable {

    private ObservableList<Promocion> promocionesDisponibles;
    private FilteredList<Promocion> filteredListPromociones;
    private Usuario usuarioSesion = new Usuario();
    private Promocion nPromocio = new Promocion();

    @FXML
    private TableView<Promocion> tvEmpresas;
    @FXML
    private TextField tfBusquedaEmpresa;
    @FXML
    private ImageView ivUsuarioSesionFoto;
    @FXML
    private Label lbUsuarioSesion;
    @FXML
    private Button btnRegistroPromo;
    @FXML
    private Button btnEdicionPromo;
    @FXML
    private Button btnEliminarPromo;
    @FXML
    private TableColumn<?, ?> clNombre;
    @FXML
    private TableColumn<?, ?> clCategoria;
    @FXML
    private TableColumn<?, ?> clTipoPromocion;
    @FXML
    private TableColumn<?, ?> clNombreSucursal;
    @FXML
    private TableColumn<?, ?> clFechaInicio;
    @FXML
    private TableColumn<?, ?> clFechaTermino;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         promocionesDisponibles = FXCollections.observableArrayList();
        filteredListPromociones = new FilteredList<>(promocionesDisponibles);

        inicializarGraficosAdmin();
        configurarColumnasTabla();
        configurarBusqueda();
    }

    @FXML
    private void btnRegistroPromocion(ActionEvent event) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioPromocion.fxml"));
            Parent vista = vistaLoad.load();
            //Agregar paso de información de la sesión 

            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Registrar promoción");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEdicionPromocion(ActionEvent event) {
        try {
            FXMLLoader vistaLoad = new FXMLLoader(getClass().getResource("FXMLFormularioPromocion.fxml"));
            Parent vista = vistaLoad.load();
            //Agregar paso de información de la sesión 

            Stage stage = new Stage();
            Scene escenaAdmin = new Scene(vista);
            stage.setScene(escenaAdmin);
            stage.setTitle("Editar promoción");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEliminarPromocion(ActionEvent event) {
    }

    public void inicializarGraficosAdmin() {
        Image icAgregar = new Image("recursos/agregar.png");
        ImageView ivAgregar = new ImageView(icAgregar);
        ivAgregar.setFitHeight(50);
        ivAgregar.setPreserveRatio(true);

        btnRegistroPromo.setGraphic(ivAgregar);

        Image icEditar = new Image("recursos/editar.png");
        ImageView ivEditar = new ImageView(icEditar);
        ivEditar.setFitHeight(50);
        ivEditar.setPreserveRatio(true);

        btnEdicionPromo.setGraphic(ivEditar);

        Image icBorrar = new Image("recursos/borrar.png");
        ImageView ivBorrar = new ImageView(icBorrar);
        ivBorrar.setFitHeight(50);
        ivBorrar.setPreserveRatio(true);

        btnEliminarPromo.setGraphic(ivBorrar);
    }
     private void configurarColumnasTabla() {
        clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clNombreSucursal.setCellValueFactory(new PropertyValueFactory<>("apellido_paterno"));
        clCategoria.setCellValueFactory(new PropertyValueFactory<>("apellido_materno"));
        clTipoPromocion.setCellValueFactory(new PropertyValueFactory<>("correo_electronico"));
        cl.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRol.setCellValueFactory(cellData -> cellData.getValue().nombreRolProperty());

    }

}
