package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Book (as a book opposite a document) description
 * <p>
 * &lt;p&gt;Java class for title-infoType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="title-infoType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="genre" maxOccurs="unbounded"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;simpleContent&amp;gt;
 * &amp;lt;extension base="&amp;lt;http://www.gribuser.ru/xml/fictionbook/2.0/genres&amp;gt;genreType"&amp;gt;
 * &amp;lt;attribute name="match" type="{http://www.w3.org/2001/XMLSchema}integer" default="100" /&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="author" maxOccurs="unbounded"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;extension base="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType"&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="book-title" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType"/&amp;gt;
 * &amp;lt;element name="annotation" type="{http://www.gribuser.ru/xml/fictionbook/2.0}annotationType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="keywords" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="date" type="{http://www.gribuser.ru/xml/fictionbook/2.0}dateType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="coverpage" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}inlineImageType" maxOccurs="unbounded"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="lang" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 * &amp;lt;element name="src-lang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="translator" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="sequence" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sequenceType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "title-infoType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "genres",
        "authors",
        "bookTitle",
        "annotation",
        "keywords",
        "date",
        "coverpage",
        "lang",
        "srcLang",
        "translators",
        "sequences"
})
public class TitleInfoType {

    @XmlElement(name = "genre", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected List<Genre> genres;
    @XmlElement(name = "author", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected List<Author> authors;
    @XmlElement(name = "book-title", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected TextFieldType bookTitle;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected AnnotationType annotation;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TextFieldType keywords;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected DateType date;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected Coverpage coverpage;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected String lang;
    @XmlElement(name = "src-lang", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected String srcLang;
    @XmlElement(name = "translator", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<AuthorType> translators;
    @XmlElement(name = "sequence", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<SequenceType> sequences;

    /**
     * Default no-arg constructor
     */
    public TitleInfoType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public TitleInfoType(final List<Genre> genres, final List<Author> authors, final TextFieldType bookTitle, final AnnotationType annotation, final TextFieldType keywords, final DateType date, final Coverpage coverpage, final String lang, final String srcLang, final List<AuthorType> translators, final List<SequenceType> sequences) {
        this.genres = genres;
        this.authors = authors;
        this.bookTitle = bookTitle;
        this.annotation = annotation;
        this.keywords = keywords;
        this.date = date;
        this.coverpage = coverpage;
        this.lang = lang;
        this.srcLang = srcLang;
        this.translators = translators;
        this.sequences = sequences;
    }

    /**
     * Gets the value of the genres property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the genres property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getGenres().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Genre }
     */
    public List<Genre> getGenres() {
        if (genres == null) {
            genres = new ArrayList<Genre>();
        }
        return this.genres;
    }

    /**
     * Gets the value of the authors property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the authors property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getAuthors().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     */
    public List<Author> getAuthors() {
        if (authors == null) {
            authors = new ArrayList<Author>();
        }
        return this.authors;
    }

    /**
     * Gets the value of the bookTitle property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets the value of the bookTitle property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setBookTitle(TextFieldType value) {
        this.bookTitle = value;
    }

    /**
     * Gets the value of the annotation property.
     *
     * @return possible object is
     * {@link AnnotationType }
     */
    public AnnotationType getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     *
     * @param value allowed object is
     *              {@link AnnotationType }
     */
    public void setAnnotation(AnnotationType value) {
        this.annotation = value;
    }

    /**
     * Gets the value of the keywords property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getKeywords() {
        return keywords;
    }

    /**
     * Sets the value of the keywords property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setKeywords(TextFieldType value) {
        this.keywords = value;
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
     * Gets the value of the coverpage property.
     *
     * @return possible object is
     * {@link Coverpage }
     */
    public Coverpage getCoverpage() {
        return coverpage;
    }

    /**
     * Sets the value of the coverpage property.
     *
     * @param value allowed object is
     *              {@link Coverpage }
     */
    public void setCoverpage(Coverpage value) {
        this.coverpage = value;
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
     * Gets the value of the srcLang property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSrcLang() {
        return srcLang;
    }

    /**
     * Sets the value of the srcLang property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSrcLang(String value) {
        this.srcLang = value;
    }

    /**
     * Gets the value of the translators property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the translators property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getTranslators().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link AuthorType }
     */
    public List<AuthorType> getTranslators() {
        if (translators == null) {
            translators = new ArrayList<AuthorType>();
        }
        return this.translators;
    }

    /**
     * Gets the value of the sequences property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the sequences property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getSequences().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SequenceType }
     */
    public List<SequenceType> getSequences() {
        if (sequences == null) {
            sequences = new ArrayList<SequenceType>();
        }
        return this.sequences;
    }


    /**
     * &lt;p&gt;Java class for anonymous complex type.
     * <p>
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * <p>
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;complexContent&amp;gt;
     * &amp;lt;extension base="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType"&amp;gt;
     * &amp;lt;/extension&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Author
            extends AuthorType {


        /**
         * Default no-arg constructor
         */
        public Author() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Author(final TextFieldType firstName, final TextFieldType middleName, final TextFieldType lastName, final TextFieldType nickname, final List<String> homePages, final List<String> emails, final String id) {
            super(firstName, middleName, lastName, nickname, homePages, emails, id);
        }

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
     * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}inlineImageType" maxOccurs="unbounded"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "images"
    })
    public static class Coverpage {

        @XmlElement(name = "image", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
        protected List<InlineImageType> images;

        /**
         * Default no-arg constructor
         */
        public Coverpage() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Coverpage(final List<InlineImageType> images) {
            this.images = images;
        }

        /**
         * Gets the value of the images property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the images property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getImages().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link InlineImageType }
         */
        public List<InlineImageType> getImages() {
            if (images == null) {
                images = new ArrayList<InlineImageType>();
            }
            return this.images;
        }

    }


    /**
     * &lt;p&gt;Java class for anonymous complex type.
     * <p>
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * <p>
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;simpleContent&amp;gt;
     * &amp;lt;extension base="&amp;lt;http://www.gribuser.ru/xml/fictionbook/2.0/genres&amp;gt;genreType"&amp;gt;
     * &amp;lt;attribute name="match" type="{http://www.w3.org/2001/XMLSchema}integer" default="100" /&amp;gt;
     * &amp;lt;/extension&amp;gt;
     * &amp;lt;/simpleContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Genre {

        @XmlValue
        protected GenreType value;
        @XmlAttribute(name = "match")
        protected BigInteger match;

        /**
         * Default no-arg constructor
         */
        public Genre() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Genre(final GenreType value, final BigInteger match) {
            this.value = value;
            this.match = match;
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link GenreType }
         */
        public GenreType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link GenreType }
         */
        public void setValue(GenreType value) {
            this.value = value;
        }

        /**
         * Gets the value of the match property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getMatch() {
            if (match == null) {
                return new BigInteger("100");
            } else {
                return match;
            }
        }

        /**
         * Sets the value of the match property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setMatch(BigInteger value) {
            this.match = value;
        }

    }

}
