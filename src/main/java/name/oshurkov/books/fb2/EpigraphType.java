package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * An epigraph
 * <p>
 * &lt;p&gt;Java class for epigraphType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="epigraphType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="poem" type="{http://www.gribuser.ru/xml/fictionbook/2.0}poemType"/&amp;gt;
 * &amp;lt;element name="cite" type="{http://www.gribuser.ru/xml/fictionbook/2.0}citeType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;element name="text-author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "epigraphType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "psAndPoemsAndCites",
        "textAuthors"
})
public class EpigraphType {

    @XmlElements({
            @XmlElement(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PType.class),
            @XmlElement(name = "poem", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PoemType.class),
            @XmlElement(name = "cite", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = CiteType.class),
            @XmlElement(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    })
    protected List<Object> psAndPoemsAndCites;
    @XmlElement(name = "text-author", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<PType> textAuthors;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Default no-arg constructor
     */
    public EpigraphType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public EpigraphType(final List<Object> psAndPoemsAndCites, final List<PType> textAuthors, final String id) {
        this.psAndPoemsAndCites = psAndPoemsAndCites;
        this.textAuthors = textAuthors;
        this.id = id;
    }

    /**
     * Gets the value of the psAndPoemsAndCites property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the psAndPoemsAndCites property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getPSAndPoemsAndCites().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     * {@link PoemType }
     * {@link CiteType }
     * {@link Object }
     */
    public List<Object> getPSAndPoemsAndCites() {
        if (psAndPoemsAndCites == null) {
            psAndPoemsAndCites = new ArrayList<Object>();
        }
        return this.psAndPoemsAndCites;
    }

    /**
     * Gets the value of the textAuthors property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the textAuthors property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getTextAuthors().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     */
    public List<PType> getTextAuthors() {
        if (textAuthors == null) {
            textAuthors = new ArrayList<PType>();
        }
        return this.textAuthors;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

}
