package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Selector for output documents. Defines, which rule to apply to any specific output documents
 * <p>
 * &lt;p&gt;Java class for outPutDocumentType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="outPutDocumentType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="part" type="{http://www.gribuser.ru/xml/fictionbook/2.0}partShareInstructionType"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute name="create" type="{http://www.gribuser.ru/xml/fictionbook/2.0}docGenerationInstructionType" /&amp;gt;
 * &amp;lt;attribute name="price" type="{http://www.w3.org/2001/XMLSchema}float" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "outPutDocumentType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "parts"
})
public class OutPutDocumentType {

    @XmlElement(name = "part", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<PartShareInstructionType> parts;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "create")
    protected DocGenerationInstructionType create;
    @XmlAttribute(name = "price")
    protected Float price;

    /**
     * Default no-arg constructor
     */
    public OutPutDocumentType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public OutPutDocumentType(final List<PartShareInstructionType> parts, final String name, final DocGenerationInstructionType create, final Float price) {
        this.parts = parts;
        this.name = name;
        this.create = create;
        this.price = price;
    }

    /**
     * Gets the value of the parts property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the parts property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getParts().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PartShareInstructionType }
     */
    public List<PartShareInstructionType> getParts() {
        if (parts == null) {
            parts = new ArrayList<PartShareInstructionType>();
        }
        return this.parts;
    }

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

    /**
     * Gets the value of the create property.
     *
     * @return possible object is
     * {@link DocGenerationInstructionType }
     */
    public DocGenerationInstructionType getCreate() {
        return create;
    }

    /**
     * Sets the value of the create property.
     *
     * @param value allowed object is
     *              {@link DocGenerationInstructionType }
     */
    public void setCreate(DocGenerationInstructionType value) {
        this.create = value;
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

}
