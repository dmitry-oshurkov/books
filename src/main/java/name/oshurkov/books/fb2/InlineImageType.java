package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for inlineImageType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="inlineImageType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}type"/&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}href"/&amp;gt;
 * &amp;lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inlineImageType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
public class InlineImageType {

    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    protected String type;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    protected String href;
    @XmlAttribute(name = "alt")
    protected String alt;

    /**
     * Default no-arg constructor
     */
    public InlineImageType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public InlineImageType(final String type, final String href, final String alt) {
        this.type = type;
        this.href = href;
        this.alt = alt;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the alt property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAlt() {
        return alt;
    }

    /**
     * Sets the value of the alt property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAlt(String value) {
        this.alt = value;
    }

}
