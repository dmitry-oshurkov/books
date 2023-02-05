package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Pointer to specific document section, explaining how to deal with it
 * <p>
 * &lt;p&gt;Java class for partShareInstructionType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="partShareInstructionType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}type"/&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}href use="required""/&amp;gt;
 * &amp;lt;attribute name="include" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0}docGenerationInstructionType" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partShareInstructionType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
public class PartShareInstructionType {

    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    protected String type;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
    protected String href;
    @XmlAttribute(name = "include", required = true)
    protected DocGenerationInstructionType include;

    /**
     * Default no-arg constructor
     */
    public PartShareInstructionType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public PartShareInstructionType(final String type, final String href, final DocGenerationInstructionType include) {
        this.type = type;
        this.href = href;
        this.include = include;
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
     * Gets the value of the include property.
     *
     * @return possible object is
     * {@link DocGenerationInstructionType }
     */
    public DocGenerationInstructionType getInclude() {
        return include;
    }

    /**
     * Sets the value of the include property.
     *
     * @param value allowed object is
     *              {@link DocGenerationInstructionType }
     */
    public void setInclude(DocGenerationInstructionType value) {
        this.include = value;
    }

}
