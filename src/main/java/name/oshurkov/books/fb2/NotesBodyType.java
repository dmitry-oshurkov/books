package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Body for footnotes, content is mostly similar to base type and may (!) be rendered in the pure environment "as is". Advanced reader should treat section[2]/section as endnotes, all other stuff as footnotes
 * <p>
 * &lt;p&gt;Java class for notesBodyType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="notesBodyType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;extension base="{http://www.gribuser.ru/xml/fictionbook/2.0}bodyType"&amp;gt;
 * &amp;lt;attribute name="name"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;pattern value="notes"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notesBodyType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
public class NotesBodyType
        extends BodyType {

    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

}
