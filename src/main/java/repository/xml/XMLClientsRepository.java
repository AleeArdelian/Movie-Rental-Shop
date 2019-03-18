package repository.xml;

import domain.Client;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.Repository;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class XMLClientsRepository extends XMLRepository implements Repository<Integer, Client> {

    private Validator<Client> validator;

    public XMLClientsRepository(String filepath, Validator<Client> validator) {
        super(filepath);
        this.validator = validator;
    }

    @Override
    public Optional<Client> findOne(Integer id) {
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Client client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                client = createClient((Element) tempNode);
                break;
            }
        }
        return Optional.ofNullable(client);
    }

    @Override
    public Iterable<Client> findAll() {
        loadXMLDocument();
        Set<Client> clientsSet = new HashSet<>();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element)
                clientsSet.add(createClient((Element) tempNode));
        }
        return clientsSet;
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        validator.validate(entity);
        loadXMLDocument();
        Element root = XMLDocument.getDocumentElement();

        Element entityElement = XMLDocument.createElement("client");
        entityElement.setAttribute("id", String.valueOf(entity.getId()));
        root.appendChild(entityElement);

        appendChildWithText(XMLDocument, entityElement, "firstName", entity.getFirstName());
        appendChildWithText(XMLDocument, entityElement, "lastName", entity.getLastName());
        appendChildWithText(XMLDocument, entityElement, "age", String.valueOf(entity.getAge()));

        saveXMLDocument();
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Integer id) {
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Client client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                client = createClient((Element) tempNode);
                tempNode.getParentNode().removeChild(tempNode);
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException {
        validator.validate(entity);
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Client client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(entity.getId()))) {
                client = createClient((Element) tempNode);
                NodeList children = tempNode.getChildNodes();
                children.item(0).setTextContent(entity.getFirstName());
                children.item(1).setTextContent(entity.getLastName());
                children.item(2).setTextContent(String.valueOf(entity.getAge()));
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(client);
    }

    private static Client createClient(Element node) {
        String id = node.getAttribute("id");
        String firstName = getTextFromTagName(node, "firstName");
        String lastName = getTextFromTagName(node, "lastName");
        String age = getTextFromTagName(node, "age");
        Client client = new Client(firstName, lastName, Integer.valueOf(age));
        client.setId(Integer.valueOf(id));
        return client;
    }

}
