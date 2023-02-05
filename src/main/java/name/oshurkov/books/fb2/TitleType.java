package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A title, used in sections, poems and body elements
 * <p>
 * &lt;p&gt;Java class for titleType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="titleType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "titleType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "psAndEmptyLines"
})
public class TitleType {

    @XmlElements({
            @XmlElement(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PType.class),
            @XmlElement(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    })
    protected List<Object> psAndEmptyLines;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Default no-arg constructor
     */
    public TitleType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public TitleType(final List<Object> psAndEmptyLines, final String lang) {
        this.psAndEmptyLines = psAndEmptyLines;
        this.lang = lang;
    }

    /**
     * Gets the value of the psAndEmptyLines property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the psAndEmptyLines property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getPSAndEmptyLines().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     * {@link Object }
     */
    public List<Object> getPSAndEmptyLines() {
        if (psAndEmptyLines == null) {
            psAndEmptyLines = new ArrayList<Object>();
        }
        return this.psAndEmptyLines;
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
