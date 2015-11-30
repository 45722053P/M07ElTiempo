package clases;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by 45722053p on 26/11/15.
 */
public class informacionMostrar {
    @FXML
    TextArea informacion;

    double temperaMediaMax,temperaMediaMin,temperaMedia;
    Parser parse = new Parser();


    public void initialize() throws IOException, SAXException, ParserConfigurationException {

        parse.rellenaArrays();

        Controller controllerprincipal = new Controller();

        ;

        //Muestra la infomacion de la fila seleccionada y luego creamos la imagen para la cual le asignaremos una imagen
        informacion.setText(parse.toString(controllerprincipal.itemsSelect));
       parse.cielo.get(controllerprincipal.itemsSelect).toString();

       /* for (int i = 0; i < parse.temperatura.size(); i++) {
            temperaMedia = temperaMedia + Double.parseDouble(parse.temperatura.get(i));
            temperaMediaMax = temperaMediaMax + Double.parseDouble(parse.temperMax.get(i));
            temperaMediaMin = temperaMediaMin + Double.parseDouble(parse.temperMin.get(i));
        }
        temperaMedia = temperaMedia / parse.temperatura.size();
        temperaMediaMax = temperaMediaMax / parse.temperatura.size();
        temperaMediaMin = temperaMediaMin / parse.temperatura.size();


        informacion.setText(String.valueOf(temperaMedia+ "\n" + temperaMediaMax + "\n" + temperaMediaMin));*/
    }


}
