package repo;
import domain.baseEntities.Droid;
import domain.baseEntities.XMLHandler;
import domain.validators.IValidator;
import org.w3c.dom.*;
import domain.baseEntities.BaseEntity;
import exceptions.ValidatorException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class XMLRepository<ID, T extends BaseEntity<ID> & XMLHandler<T>> extends InMemoryRepository<ID, T>{
    private final String filePath;
    private final Class<T> constructor;

    public XMLRepository(String filePath, IValidator<T> validator, Class<T> constructor){
        super(validator);
        this.filePath = filePath;

        this.constructor = constructor;
        this.readFromFile();

    }


    /**
     *
     * @throws RuntimeException if the constructor is not valid
     * @throws RuntimeException if the file operations are not successful
     *
     * loads all the data from the xml file and appends it to the 'entities' list in the correct format
     */
    private void readFromFile(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = builder.parse(this.filePath);
            Element rootElement = root.getDocumentElement();
            NodeList nodes = rootElement.getChildNodes();


            IntStream.range(0, nodes.getLength())
                    .forEach(i -> {
                        Node dataNode = nodes.item(i);
                        Optional.of(dataNode)
                                .filter(node -> node instanceof Element)
                                .map(node -> (Element) node)
                                .ifPresent(element -> {
                                    T object;

                                    try {
                                        object = this.constructor.getDeclaredConstructor().newInstance();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e.getMessage());
                                    }

                                    this.save(object.fromNode(element));
                                });
                    });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     *
     * @throws RuntimeException if the constructor is not valid
     *
     * appends all the entitites in the repository to the corresponding xml file
     */
    public void writeToFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document root = builder.parse(this.filePath);
            root.removeChild(root.getChildNodes().item(0));
            Element data = root.createElement("data");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Iterable<T> objects = findAll();
            StreamSupport.stream(objects.spliterator(), false).forEach(obj -> {
                Node nodes = obj.intoNode(root);
                data.appendChild(nodes);
            });
            root.appendChild(data);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(
                    new DOMSource(root),
                    new StreamResult(new FileOutputStream(this.filePath, false))
            );
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("Error while writing file: %s", e.getMessage()));
        }
    }



    /*
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    @Override
    public Optional<T> findOne(ID id) {
        return super.findOne(id);
    }

    /**
     *
     * @return all objects
     */
    @Override
    public Iterable<T> findAll() {
        return super.findAll();
    }

    /**
     *
     * @param entity
     *            must not be null.
     * @ return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws ValidatorException
     *          if the entity is not valid
     * @throws IllegalArgumentException
     *          if the entity is null
     */
    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        this.writeToFile();
        return Optional.empty();
    }


    /**
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id
     * @throws IllegalArgumentException
     *          if the given element is null
     */
    @Override
    public Optional<T> delete(ID id) {
        super.delete(id);
        this.writeToFile();
        return Optional.empty();
        //return Optional.ofNullable(entities.remove(id));
    }


    /**
     * Updates the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        super.update(entity);
        this.writeToFile();
        return super.update(entity);
    }

}
