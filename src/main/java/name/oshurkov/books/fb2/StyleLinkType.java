package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Markup
 * <p>
 * &lt;p&gt;Java class for styleLinkType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="styleLinkType"&amp;gt;
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
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "styleLinkType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "content"
})
public class StyleLinkType {

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

    /**
     * Default no-arg constructor
     */
    public StyleLinkType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public StyleLinkType(final List<Serializable> content) {
        this.content = content;
    }

    /**
     * Markup Gets the value of the content property.
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

}
