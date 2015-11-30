package clases;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Controller {
    @FXML
    Label Titulo;
    @FXML
    ListView listaTiempo;
    @FXML
    Button refrescar;
    @FXML
    Button mostrarInformacion;
    @FXML
    TextArea texto,informacion;
    @FXML
    ImageView imagen;
    @FXML
    ObservableList items =
            FXCollections.observableArrayList();

    public int itemsSelect;

    public static Parser parse = new Parser();


    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        //Muestra el icono del boton refresh.

        refrescar.setGraphic(new ImageView("iconos/refresh.png"));
        //Con el parse. lo que hacemos es llamar a la funcion de rellenar Arrays de la clase parse.
        parse.rellenaArrays();


    }

   public void refrescar()  throws IOException, SAXException, ParserConfigurationException {
        //Limpiamos los items.
       items.clear();

       //Aqui le asignamos al titulo el nombre de la ciudad en la que estamos.
       Titulo.setText(parse.nombreCiudad());

       //En este for recorremos la array List de dias y vamos añadiendole los items.
       for(int i = 0;i < parse.dias.size();i++){
           items.add(parse.dias.get(i));

       }

       listaTiempo.setItems(items);
       //Aqui ponemos el titulo mas grande para que resalte mas.
       Titulo.setFont(Font.font(20));

   }
    public void Informacion() throws IOException, ParserConfigurationException, SAXException {


    }

    public void mostrarInfo()throws IOException, SAXException, ParserConfigurationException{

           //En este listener lo que hacermos es para que cuando le demos al dia nos salga la informacion de ese dia en la Area de al lado.
            listaTiempo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            //Sacamos el numero del �ndice que tenemos seleccionado
            itemsSelect = listaTiempo.getSelectionModel().getSelectedIndex();

                Stage ventanaNueva = new Stage();
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(
                            informacionMostrar.class.getResource("mostrarInformacion.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ventanaNueva.setScene(new Scene(parent));
                ventanaNueva.setTitle("Informacion del Dia");
                ventanaNueva.initModality(Modality.WINDOW_MODAL);
                ventanaNueva.show();

        });

    }
//Aqui tenemos la funcion que al darle al boton de salir nos cierre la aplicacion.
    public void salirAplicacion(ActionEvent actionEvent) {

        Platform.exit();
    }


}
