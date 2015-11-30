package clases;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public  String Ciudad;
    public  ArrayList<String> dias = new ArrayList<>();
    public  ArrayList<String> temperatura = new ArrayList();
    public  ArrayList<String> temperMax = new ArrayList();
    public  ArrayList<String> temperMin = new ArrayList();
    public  ArrayList<String> cielo = new ArrayList();
    public static final File xml = new File("forecast.xml");

//Aqui simplemente lo que hacemos es darle nombre a la ciudad.
    public String nombreCiudad() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);

        Ciudad = doc.getElementsByTagName("name").item(0).getTextContent();
        return Ciudad;
    }

//En este metodo lo que hacemos es recorrer el xml y coger los datos que necesitamos
    public void rellenaArrays() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);

        //Aqui creamos una lista la cual va a recorrer el tag time.
        NodeList listaTiempo = doc.getElementsByTagName("time");
        //En este for recorremos todas y cada una de los tag time y sacamos la informacion siguiente.
        for (int temp = 0; temp < listaTiempo.getLength(); temp++) {
           //creamos un elemento para asignarle el valor que queramos cuando lo encontremos.
            Element elemts = (Element) listaTiempo.item(temp);

            //aqui a la array list de dias le añadimos el dia cuando encuentre el tag dia.
            dias.add(elemts.getAttributes().getNamedItem("day").getNodeValue());

            //aqui a la array list de temperatura le añadimos la temperatura cuando encuentre el tag temperatura dentro de day.
            temperatura.add(elemts.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("day").getNodeValue());

            //aqui a la array list de temperatura minima le añadimos la temperatura minima cuando encuentre el tag min dentro de temperatura.
            temperMin.add(elemts.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());

            //aqui a la array list de temperatura maxima le añadimos la temperatura maxima cuando encuentre el tag max dentro de temperatura.
            temperMax.add(elemts.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());

            //aqui a la array list de cielo le añadimos como esta el cielo cuando encuentre el tag value dentro de clouds.
            cielo.add(elemts.getElementsByTagName("clouds").item(0).getAttributes().getNamedItem("value").getNodeValue());
        }
    }
    public String toString(int pos) {
        return  "Dia: " + dias.get(pos) + "\n" +
                "Temperatura: " + temperatura.get(pos) + "\n" +
                "TemperaturaMax: " + temperMax.get(pos) +"\n" +
                "TemperaturaMin: " + temperMin.get(pos) +"\n" +
                "Cielo: " + cielo.get(pos);
    }
}
