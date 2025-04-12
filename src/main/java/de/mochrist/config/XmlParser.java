package de.mochrist.config;
import de.mochrist.Route;
import de.mochrist.RouteDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();

        try {
            File file = new File("src/main/java/de/mochrist/routing-config.xml")
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList routeNodes = doc.getElementsByTagName("route");

            for (int i = 0; i < routeNodes.getLength(); i++) {

                // Objekte erstellen und in Liste speichern
                Route route = new Route();
                Node node = routeNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    route.setPath(elem.getAttribute("path"));
                    route.setMethod(elem.getAttribute("method"));
                    route.setServlet(elem.getAttribute("servlet"));

                    routes.add(route);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Route r : routes) {
            System.out.println(r.toString());
        }
        return routes;
    }
}

