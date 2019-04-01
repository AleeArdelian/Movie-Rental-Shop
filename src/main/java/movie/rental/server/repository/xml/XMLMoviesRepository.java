package movie.rental.server.repository.xml;

import movie.rental.server.domain.Movie;
import movie.rental.server.domain.validators.Validator;
import movie.rental.server.domain.validators.ValidatorException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import movie.rental.server.repository.Repository;

import java.util.*;

public class XMLMoviesRepository extends XMLRepository implements Repository<Integer, Movie> {

    private Validator<Movie> validator;

    public XMLMoviesRepository(String filepath, Validator<Movie> validator) {
        super(filepath);
        this.validator = validator;
    }

    @Override
    public Optional<Movie> findOne(Integer id) {
        //loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList movies = root.getChildNodes();
        Movie movie = null;
        for (int i = 0; i < movies.getLength(); i++) {
            Node tempNode = movies.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                movie = createMovie((Element) tempNode);
                break;
            }
        }
        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll() {
        //loadXMLDocument();
        Set<Movie> moviesSet = new HashSet<>();
        Node root = XMLDocument.getDocumentElement();
        NodeList movies = root.getChildNodes();
        for (int i = 0; i < movies.getLength(); i++) {
            Node tempNode = movies.item(i);
            if (tempNode instanceof Element)
                moviesSet.add(createMovie((Element) tempNode));
        }
        return moviesSet;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        validator.validate(entity);
        //loadXMLDocument();
        Element root = XMLDocument.getDocumentElement();

        Element entityElement = XMLDocument.createElement("movie");
        entityElement.setAttribute("id", String.valueOf(entity.getId()));
        root.appendChild(entityElement);

        appendChildWithText(XMLDocument, entityElement, "name", entity.getMovieName());
        appendChildWithText(XMLDocument, entityElement, "director", entity.getMovieDirector());
        appendChildWithText(XMLDocument, entityElement, "year", String.valueOf(entity.getYear()));
        appendChildWithText(XMLDocument, entityElement, "rented", String.valueOf(entity.isRented()));

        saveXMLDocument();
        return Optional.empty();
    }

    @Override
    public Optional<Movie> delete(Integer id) {
        //loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList movies = root.getChildNodes();
        Movie movie = null;
        for (int i = 0; i < movies.getLength(); i++) {
            Node tempNode = movies.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(id))) {
                movie = createMovie((Element) tempNode);
                tempNode.getParentNode().removeChild(tempNode);
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        validator.validate(entity);
        //loadXMLDocument();
        Node root = XMLDocument.getDocumentElement();
        NodeList movies = root.getChildNodes();
        Movie movie = null;
        for (int i = 0; i < movies.getLength(); i++) {
            Node tempNode = movies.item(i);
            if (tempNode instanceof Element && ((Element) tempNode).getAttribute("id").equals(String.valueOf(entity.getId()))) {
                movie = createMovie((Element) tempNode);
                NodeList children = tempNode.getChildNodes();
                children.item(0).setTextContent(entity.getMovieName());
                children.item(1).setTextContent(entity.getMovieDirector());
                children.item(2).setTextContent(String.valueOf(entity.getYear()));
                children.item(3).setTextContent(String.valueOf(entity.isRented()));
            }
        }
        saveXMLDocument();
        return Optional.ofNullable(movie);
    }

    private static Movie createMovie(Element entityNode) {
        String id = entityNode.getAttribute("id");
        String name = getTextFromTagName(entityNode, "name");
        String director = getTextFromTagName(entityNode, "director");
        String year = getTextFromTagName(entityNode, "year");
        String rented = getTextFromTagName(entityNode, "rented");

        Movie c = new Movie(name, Integer.valueOf(year), director);
        c.setId(Integer.valueOf(id));
        c.setRented(Boolean.valueOf(rented));
        return c;
    }

}

