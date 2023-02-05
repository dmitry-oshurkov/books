package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Main content of the book, multiple bodies are used for additional information, like footnotes, that do not appear in the main book flow (extended from this class). The first body is presented to the reader by default, and content in the other bodies should be accessible by hyperlinks.
 * <p>
 * &lt;p&gt;Java class for bodyType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="bodyType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}imageType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}titleType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="epigraph" type="{http://www.gribuser.ru/xml/fictionbook/2.0}epigraphType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="section" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sectionType" maxOccurs="unbounded"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bodyType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "image",
        "title",
        "epigraphs",
        "sections"
})
public class BodyType {

    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected ImageType image;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TitleType title;
    @XmlElement(name = "epigraph", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<EpigraphType> epigraphs;
    @XmlElement(name = "section", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected List<SectionType> sections;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Default no-arg constructor
     */
    public BodyType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public BodyType(final ImageType image, final TitleType title, final List<EpigraphType> epigraphs, final List<SectionType> sections, final String name, final String lang) {
        this.image = image;
        this.title = title;
        this.epigraphs = epigraphs;
        this.sections = sections;
        this.name = name;
        this.lang = lang;
    }

    /**
     * Gets the value of the image property.
     *
     * @return possible object is
     * {@link ImageType }
     */
    public ImageType getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     *
     * @param value allowed object is
     *              {@link ImageType }
     */
    public void setImage(ImageType value) {
        this.image = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link TitleType }
     */
    public TitleType getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link TitleType }
     */
    public void setTitle(TitleType value) {
        this.title = value;
    }

    /**
     * Gets the value of the epigraphs property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the epigraphs property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getEpigraphs().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link EpigraphType }
     */
    public List<EpigraphType> getEpigraphs() {
        if (epigraphs == null) {
            epigraphs = new ArrayList<EpigraphType>();
        }
        return this.epigraphs;
    }

    /**
     * Gets the value of the sections property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the sections property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getSections().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SectionType }
     */
    public List<SectionType> getSections() {
        if (sections == null) {
            sections = new ArrayList<SectionType>();
        }
        return this.sections;
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
