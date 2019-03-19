package repository.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLRepository {

    private String FILEPATH;
    protected Document XMLDocument = null;
    protected Transformer XMLTransformer = null;

    public XMLRepository(String filepath) {
        FILEPATH = filepath;
        loadXMLDocument();
    }

    protected void loadXMLDocument() {
        try {
            XMLDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(FILEPATH);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveXMLDocument() {
        try {
            XMLTransformer = TransformerFactory.newInstance().newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            XMLTransformer.transform(new DOMSource(XMLDocument.getDocumentElement()), new StreamResult(new FileOutputStream(FILEPATH)));
        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static String getTextFromTagName(Element element, String tagName) {
        NodeList elements = element.getElementsByTagName(tagName);
        Node node = elements.item(0);
        return node.getTextContent();
    }

    protected static void appendChildWithText(Document document, Node parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parent.appendChild(element);
    }

}
