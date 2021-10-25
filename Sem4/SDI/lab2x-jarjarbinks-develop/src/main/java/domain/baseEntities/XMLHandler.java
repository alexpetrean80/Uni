package domain.baseEntities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLHandler<T> {
    T fromNode(Element e);
    Element intoNode(Document document);
}
