package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A poem
 * <p>
 * &lt;p&gt;Java class for poemType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="poemType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}titleType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="epigraph" type="{http://www.gribuser.ru/xml/fictionbook/2.0}epigraphType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded"&amp;gt;
 * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="stanza"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}titleType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="v" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" maxOccurs="unbounded"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;element name="text-author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="date" type="{http://www.gribuser.ru/xml/fictionbook/2.0}dateType" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "poemType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "title",
        "epigraphs",
        "subtitlesAndStanzas",
        "textAuthors",
        "date"
})
public class PoemType {

    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TitleType title;
    @XmlElement(name = "epigraph", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<EpigraphType> epigraphs;
    @XmlElements({
            @XmlElement(name = "subtitle", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = PType.class),
            @XmlElement(name = "stanza", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = Stanza.class)
    })
    protected List<Object> subtitlesAndStanzas;
    @XmlElement(name = "text-author", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<PType> textAuthors;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected DateType date;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Default no-arg constructor
     */
    public PoemType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public PoemType(final TitleType title, final List<EpigraphType> epigraphs, final List<Object> subtitlesAndStanzas, final List<PType> textAuthors, final DateType date, final String id, final String lang) {
        this.title = title;
        this.epigraphs = epigraphs;
        this.subtitlesAndStanzas = subtitlesAndStanzas;
        this.textAuthors = textAuthors;
        this.date = date;
        this.id = id;
        this.lang = lang;
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
     * Gets the value of the subtitlesAndStanzas property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the subtitlesAndStanzas property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getSubtitlesAndStanzas().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     * {@link Stanza }
     */
    public List<Object> getSubtitlesAndStanzas() {
        if (subtitlesAndStanzas == null) {
            subtitlesAndStanzas = new ArrayList<Object>();
        }
        return this.subtitlesAndStanzas;
    }

    /**
     * Gets the value of the textAuthors property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the textAuthors property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getTextAuthors().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     */
    public List<PType> getTextAuthors() {
        if (textAuthors == null) {
            textAuthors = new ArrayList<PType>();
        }
        return this.textAuthors;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link DateType }
     */
    public DateType getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link DateType }
     */
    public void setDate(DateType value) {
        this.date = value;
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
     * &amp;lt;element name="title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}titleType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="v" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType" maxOccurs="unbounded"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "title",
            "subtitle",
            "vs"
    })
    public static class Stanza {

        @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected TitleType title;
        @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected PType subtitle;
        @XmlElement(name = "v", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
        protected List<PType> vs;
        @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "language")
        protected String lang;

        /**
         * Default no-arg constructor
         */
        public Stanza() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Stanza(final TitleType title, final PType subtitle, final List<PType> vs, final String lang) {
            this.title = title;
            this.subtitle = subtitle;
            this.vs = vs;
            this.lang = lang;
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
         * Gets the value of the subtitle property.
         *
         * @return possible object is
         * {@link PType }
         */
        public PType getSubtitle() {
            return subtitle;
        }

        /**
         * Sets the value of the subtitle property.
         *
         * @param value allowed object is
         *              {@link PType }
         */
        public void setSubtitle(PType value) {
            this.subtitle = value;
        }

        /**
         * Gets the value of the vs property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the vs property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getVS().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link PType }
         */
        public List<PType> getVS() {
            if (vs == null) {
                vs = new ArrayList<PType>();
            }
            return this.vs;
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

}
