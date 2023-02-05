package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A citation with an optional citation author at the end
 * <p>
 * &lt;p&gt;Java class for citeType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="citeType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="poem" type="{http://www.gribuser.ru/xml/fictionbook/2.0}poemType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="table" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tableType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;element name="text-author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "citeType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "psAndPoemsAndEmptyLines",
        "textAuthors"
})
public class CiteType {

    @XmlElementRefs({
            @XmlElementRef(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "poem", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "subtitle", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "table", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> psAndPoemsAndEmptyLines;
    @XmlElement(name = "text-author", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<PType> textAuthors;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Default no-arg constructor
     */
    public CiteType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public CiteType(final List<JAXBElement<?>> psAndPoemsAndEmptyLines, final List<PType> textAuthors, final String id, final String lang) {
        this.psAndPoemsAndEmptyLines = psAndPoemsAndEmptyLines;
        this.textAuthors = textAuthors;
        this.id = id;
        this.lang = lang;
    }

    /**
     * Gets the value of the psAndPoemsAndEmptyLines property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the psAndPoemsAndEmptyLines property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getPSAndPoemsAndEmptyLines().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PType }{@code >}
     * {@link JAXBElement }{@code <}{@link PoemType }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link PType }{@code >}
     * {@link JAXBElement }{@code <}{@link TableType }{@code >}
     */
    public List<JAXBElement<?>> getPSAndPoemsAndEmptyLines() {
        if (psAndPoemsAndEmptyLines == null) {
            psAndPoemsAndEmptyLines = new ArrayList<JAXBElement<?>>();
        }
        return this.psAndPoemsAndEmptyLines;
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

    /**
     * Gets the value of the lang property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLang(String value) {
        this.lang = value;
    }

}
