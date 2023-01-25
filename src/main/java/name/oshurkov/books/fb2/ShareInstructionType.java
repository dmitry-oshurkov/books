package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * In-document instruction for generating output free and payed documents
 * <p>
 * &lt;p&gt;Java class for shareInstructionType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="shareInstructionType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="part" type="{http://www.gribuser.ru/xml/fictionbook/2.0}partShareInstructionType"/&amp;gt;
 * &amp;lt;element name="output-document-class" type="{http://www.gribuser.ru/xml/fictionbook/2.0}outPutDocumentType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute name="mode" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0}shareModesType" /&amp;gt;
 * &amp;lt;attribute name="include-all" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0}docGenerationInstructionType" /&amp;gt;
 * &amp;lt;attribute name="price" type="{http://www.w3.org/2001/XMLSchema}float" /&amp;gt;
 * &amp;lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shareInstructionType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "partsAndOutputDocumentClasses"
})
public class ShareInstructionType {

    @XmlElements({
            @XmlElement(name = "part", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PartShareInstructionType.class),
            @XmlElement(name = "output-document-class", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = OutPutDocumentType.class)
    })
    protected List<Object> partsAndOutputDocumentClasses;
    @XmlAttribute(name = "mode", required = true)
    protected ShareModesType mode;
    @XmlAttribute(name = "include-all", required = true)
    protected DocGenerationInstructionType includeAll;
    @XmlAttribute(name = "price")
    protected Float price;
    @XmlAttribute(name = "currency")
    protected String currency;

    /**
     * Default no-arg constructor
     */
    public ShareInstructionType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public ShareInstructionType(final List<Object> partsAndOutputDocumentClasses, final ShareModesType mode, final DocGenerationInstructionType includeAll, final Float price, final String currency) {
        this.partsAndOutputDocumentClasses = partsAndOutputDocumentClasses;
        this.mode = mode;
        this.includeAll = includeAll;
        this.price = price;
        this.currency = currency;
    }

    /**
     * Gets the value of the partsAndOutputDocumentClasses property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the partsAndOutputDocumentClasses property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getPartsAndOutputDocumentClasses().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PartShareInstructionType }
     * {@link OutPutDocumentType }
     */
    public List<Object> getPartsAndOutputDocumentClasses() {
        if (partsAndOutputDocumentClasses == null) {
            partsAndOutputDocumentClasses = new ArrayList<Object>();
        }
        return this.partsAndOutputDocumentClasses;
    }

    /**
     * Gets the value of the mode property.
     *
     * @return possible object is
     * {@link ShareModesType }
     */
    public ShareModesType getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     *
     * @param value allowed object is
     *              {@link ShareModesType }
     */
    public void setMode(ShareModesType value) {
        this.mode = value;
    }

    /**
     * Gets the value of the includeAll property.
     *
     * @return possible object is
     * {@link DocGenerationInstructionType }
     */
    public DocGenerationInstructionType getIncludeAll() {
        return includeAll;
    }

    /**
     * Sets the value of the includeAll property.
     *
     * @param value allowed object is
     *              {@link DocGenerationInstructionType }
     */
    public void setIncludeAll(DocGenerationInstructionType value) {
        this.includeAll = value;
    }

    /**
     * Gets the value of the price property.
     *
     * @return possible object is
     * {@link Float }
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value allowed object is
     *              {@link Float }
     */
    public void setPrice(Float value) {
        this.price = value;
    }

    /**
     * Gets the value of the currency property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

}
