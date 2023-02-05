package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * &lt;p&gt;Java class for sectionType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="sectionType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="cite" type="{http://www.gribuser.ru/xml/fictionbook/2.0}citeType"/&amp;gt;
 * &amp;lt;element name="title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}titleType"/&amp;gt;
 * &amp;lt;element name="p"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="strong" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="emphasis" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="a" type="{http://www.gribuser.ru/xml/fictionbook/2.0}linkType" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="section" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sectionType"/&amp;gt;
 * &amp;lt;element name="poem" type="{http://www.gribuser.ru/xml/fictionbook/2.0}poemType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sectionType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "citesAndTitlesAndPS"
})
public class SectionType {

    @XmlElements({
            @XmlElement(name = "cite", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = CiteType.class),
            @XmlElement(name = "title", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = TitleType.class),
            @XmlElement(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = P.class),
            @XmlElement(name = "section", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = SectionType.class),
            @XmlElement(name = "poem", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PoemType.class),
            @XmlElement(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = String.class)
    })
    protected List<Object> citesAndTitlesAndPS;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Default no-arg constructor
     */
    public SectionType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public SectionType(final List<Object> citesAndTitlesAndPS, final String id) {
        this.citesAndTitlesAndPS = citesAndTitlesAndPS;
        this.id = id;
    }

    /**
     * Gets the value of the citesAndTitlesAndPS property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the citesAndTitlesAndPS property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getCitesAndTitlesAndPS().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link CiteType }
     * {@link TitleType }
     * {@link P }
     * {@link SectionType }
     * {@link PoemType }
     * {@link String }
     */
    public List<Object> getCitesAndTitlesAndPS() {
        if (citesAndTitlesAndPS == null) {
            citesAndTitlesAndPS = new ArrayList<Object>();
        }
        return this.citesAndTitlesAndPS;
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
     * &lt;p&gt;Java class for anonymous complex type.
     * <p>
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * <p>
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;complexContent&amp;gt;
     * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     * &amp;lt;sequence&amp;gt;
     * &amp;lt;element name="strong" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="emphasis" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="a" type="{http://www.gribuser.ru/xml/fictionbook/2.0}linkType" minOccurs="0"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "content"
    })
    public static class P {

        @XmlElementRefs({
                @XmlElementRef(name = "strong", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "emphasis", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "a", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false)
        })
        @XmlMixed
        protected List<Serializable> content;

        /**
         * Default no-arg constructor
         */
        public P() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public P(final List<Serializable> content) {
            this.content = content;
        }

        /**
         * Gets the value of the content property.
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
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link LinkType }{@code >}
         * {@link String }
         */
        public List<Serializable> getContent() {
            if (content == null) {
                content = new ArrayList<Serializable>();
            }
            return this.content;
        }

    }

}
