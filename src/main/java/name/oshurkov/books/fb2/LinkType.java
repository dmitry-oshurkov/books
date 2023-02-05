package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Generic hyperlinks. Cannot be nested. Footnotes should be implemented by links referring to additional bodies in the same document
 * <p>
 * &lt;p&gt;Java class for linkType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="linkType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="strong" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="emphasis" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="style" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="strikethrough" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="sub" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="sup" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="code" type="{http://www.gribuser.ru/xml/fictionbook/2.0}styleLinkType"/&amp;gt;
 * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}inlineImageType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}type"/&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/1999/xlink}href use="required""/&amp;gt;
 * &amp;lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}token" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linkType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "content"
})
public class LinkType {

    @XmlElementRefs({
            @XmlElementRef(name = "strong", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "emphasis", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "style", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "strikethrough", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sub", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sup", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "code", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "image", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    protected String type;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink", required = true)
    protected String href;
    @XmlAttribute(name = "type")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String typeAttribute;

    /**
     * Default no-arg constructor
     */
    public LinkType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public LinkType(final List<Serializable> content, final String type, final String href, final String typeAttribute) {
        this.content = content;
        this.type = type;
        this.href = href;
        this.typeAttribute = typeAttribute;
    }

    /**
     * Generic hyperlinks. Cannot be nested. Footnotes should be implemented by links referring to additional bodies in the same document Gets the value of the content property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the content property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getContent().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link StyleLinkType }{@code >}
     * {@link JAXBElement }{@code <}{@link InlineImageType }{@code >}
     * {@link String }
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
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
     * Gets the value of the typeAttribute property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTypeAttribute() {
        return typeAttribute;
    }

    /**
     * Sets the value of the typeAttribute property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTypeAttribute(String value) {
        this.typeAttribute = value;
    }

}
