package repository.xml;

import domain.Rental;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import repository.Repository;

import java.util.*;

public class XMLRentalsRepository extends XMLRepository implements Repository<Integer, Rental> {

    private Validator<Rental> validator;

    public XMLRentalsRepository(String filepath, Validator<Rental> validator) {
        super(filepath);
        this.validator = validator;
    }

    @Override
    public Optional<Rental> findOne(Integer id) {
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Rental client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                client = createRental((Element) tempNode);
                break;
            }
        }
        return Optional.ofNullable(client);
    }

    @Override
    public Iterable<Rental> findAll() {
        loadXMLDocument();
        Set<Rental> rentalsSet = new HashSet<>();
        Node root = XMLDocument.getDocumentElement();
        NodeList rentals = root.getChildNodes();
        for (int i = 0; i < rentals.getLength(); i++) {
            Node tempNode = rentals.item(i);
            if (tempNode instanceof Element)
                rentalsSet.add(createRental((Element) tempNode));
        }
        return rentalsSet;
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        validator.validate(entity);
        loadXMLDocument();
        Element root = XMLDocument.getDocumentElement();

        Element entityElement = XMLDocument.createElement("rental");
        entityElement.setAttribute("id", String.valueOf(entity.getId()));
        root.appendChild(entityElement);

        appendChildWithText(XMLDocument, entityElement, "clientId", String.valueOf(entity.getClientId()));
        appendChildWithText(XMLDocument, entityElement, "movieId", String.valueOf(entity.getMovieId()));
        appendChildWithText(XMLDocument, entityElement, "rentalDate", entity.getRentalDate());
        appendChildWithText(XMLDocument, entityElement, "returnDate", entity.getReturnDate());

        saveXMLDocument();
        return Optional.empty();
    }

    @Override
    public Optional<Rental> delete(Integer id) {
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Rental client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                client = createRental((Element) tempNode);
                tempNode.getParentNode().removeChild(tempNode);
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Rental> update(Rental entity) throws ValidatorException {
        validator.validate(entity);
        loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList clients = root.getChildNodes();
        Rental client = null;
        for (int i = 0; i < clients.getLength(); i++) {
            Node tempNode = clients.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(entity.getId()))) {
                client = createRental((Element) tempNode);
                NodeList children = tempNode.getChildNodes();
                children.item(0).setTextContent(String.valueOf(entity.getClientId()));
                children.item(1).setTextContent(String.valueOf(entity.getMovieId()));
                children.item(2).setTextContent(entity.getRentalDate());
                children.item(3).setTextContent(entity.getReturnDate());
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(client);
    }

    private static Rental createRental(Element entityNode) {
        String id = entityNode.getAttribute("id");
        String clientId = getTextFromTagName(entityNode, "clientId");
        String movieId = getTextFromTagName(entityNode, "movieId");
        String rentalDate = getTextFromTagName(entityNode, "rentalDate");
        String returnDate = getTextFromTagName(entityNode, "returnDate");

        Rental c = new Rental(Integer.valueOf(clientId), Integer.valueOf(movieId), rentalDate, returnDate);
        c.setId(Integer.valueOf(id));
        return c;
    }
}
