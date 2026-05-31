package gestionmobiles;

import classes.javabeans.Mobile;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML private ComboBox<String> cbMarque;
    @FXML private TextField tfModele;
    @FXML private TextField tfPrix;
    @FXML private TextField tfQuantite;
    @FXML private CheckBox cbDispo;
@FXML private TextField tfRecherche;
    @FXML private TableView<Mobile> tableMobiles;
    @FXML private TableColumn<Mobile, Integer> colId;
    @FXML private TableColumn<Mobile, String> colMarque;
    @FXML private TableColumn<Mobile, String> colModele;
    @FXML private TableColumn<Mobile, Double> colPrix;
    @FXML private TableColumn<Mobile, Integer> colQuantite;
    @FXML private TableColumn<Mobile, Boolean> colDispo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MobileService.openSession();

        cbMarque.setItems(FXCollections.observableArrayList(
            "Samsung", "Apple", "Xiaomi", "Huawei", "Oppo", "OnePlus"
        ));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colDispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

        actualiser();

        tableMobiles.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> {
                if (newVal != null) {
                    cbMarque.setValue(newVal.getMarque());
                    tfModele.setText(newVal.getModele());
                    tfPrix.setText(String.valueOf(newVal.getPrix()));
                    tfQuantite.setText(String.valueOf(newVal.getQuantite()));
                    cbDispo.setSelected(newVal.isDisponibilite());
                }
            }
        );
    }

    @FXML
    public void ajouterMobile() {
        Mobile p = new Mobile(
            cbMarque.getValue(),
            tfModele.getText(),
            Double.parseDouble(tfPrix.getText()),
            Integer.parseInt(tfQuantite.getText()),
            cbDispo.isSelected()
        );
        MobileService.create(p);
        actualiser();
        viderFormulaire();
    }

    @FXML
    public void modifierMobile() {
        Mobile p = tableMobiles.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setMarque(cbMarque.getValue());
            p.setModele(tfModele.getText());
            p.setPrix(Double.parseDouble(tfPrix.getText()));
            p.setQuantite(Integer.parseInt(tfQuantite.getText()));
            p.setDisponibilite(cbDispo.isSelected());
            MobileService.update(p);
            actualiser();
            viderFormulaire();
        }
    }

    @FXML
    public void supprimerMobile() {
        Mobile p = tableMobiles.getSelectionModel().getSelectedItem();
        if (p != null) {
            MobileService.delete(p);
            actualiser();
            viderFormulaire();
        }
    }

    @FXML
    public void actualiser() {
        List<Mobile> liste = MobileService.listMobile();
        ObservableList<Mobile> data = FXCollections.observableArrayList(liste);
        tableMobiles.setItems(data);
    }
@FXML
public void rechercher() {
    String motCle = tfRecherche.getText().toLowerCase();
    List<Mobile> liste = MobileService.listMobile();
    ObservableList<Mobile> data = FXCollections.observableArrayList();
    for (Mobile m : liste) {
        if (m.getMarque().toLowerCase().contains(motCle) ||
            m.getModele().toLowerCase().contains(motCle)) {
            data.add(m);
        }
    }
    tableMobiles.setItems(data);
}
    private void viderFormulaire() {
        cbMarque.setValue(null);
        tfModele.clear();
        tfPrix.clear();
        tfQuantite.clear();
        cbDispo.setSelected(false);
    }
}