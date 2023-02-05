package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


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
 * &amp;lt;element name="stylesheet" maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;simpleContent&amp;gt;
 * &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;string"&amp;gt;
 * &amp;lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="description"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="title-info" type="{http://www.gribuser.ru/xml/fictionbook/2.0}title-infoType"/&amp;gt;
 * &amp;lt;element name="src-title-info" type="{http://www.gribuser.ru/xml/fictionbook/2.0}title-infoType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="document-info"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded"/&amp;gt;
 * &amp;lt;element name="program-used" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="date" type="{http://www.gribuser.ru/xml/fictionbook/2.0}dateType"/&amp;gt;
 * &amp;lt;element name="src-url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="src-ocr" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}token"/&amp;gt;
 * &amp;lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}float"/&amp;gt;
 * &amp;lt;element name="history" type="{http://www.gribuser.ru/xml/fictionbook/2.0}annotationType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="publish-info" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="book-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="city" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}gYear" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="isbn" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="sequence" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sequenceType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="custom-info" maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;simpleContent&amp;gt;
 * &amp;lt;extension base="&amp;lt;http://www.gribuser.ru/xml/fictionbook/2.0&amp;gt;textFieldType"&amp;gt;
 * &amp;lt;attribute name="info-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="output" type="{http://www.gribuser.ru/xml/fictionbook/2.0}shareInstructionType" maxOccurs="2" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="body" type="{http://www.gribuser.ru/xml/fictionbook/2.0}bodyType"/&amp;gt;
 * &amp;lt;element name="body" type="{http://www.gribuser.ru/xml/fictionbook/2.0}bodyType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="binary" maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;simpleContent&amp;gt;
 * &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;base64Binary"&amp;gt;
 * &amp;lt;attribute name="content-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "stylesheets",
        "description",
        "bodies",
        "binaries"
})
@XmlRootElement(name = "FictionBook", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
public class FictionBook {

    @XmlElement(name = "stylesheet", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<Stylesheet> stylesheets;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected Description description;
    @XmlElement(name = "body", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected List<BodyType> bodies;
    @XmlElement(name = "binary", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<Binary> binaries;

    /**
     * Default no-arg constructor
     */
    public FictionBook() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public FictionBook(final List<Stylesheet> stylesheets, final Description description, final List<BodyType> bodies, final List<Binary> binaries) {
        this.stylesheets = stylesheets;
        this.description = description;
        this.bodies = bodies;
        this.binaries = binaries;
    }

    /**
     * Gets the value of the stylesheets property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the stylesheets property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getStylesheets().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Stylesheet }
     */
    public List<Stylesheet> getStylesheets() {
        if (stylesheets == null) {
            stylesheets = new ArrayList<Stylesheet>();
        }
        return this.stylesheets;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link Description }
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link Description }
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the bodies property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the bodies property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getBodies().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link BodyType }
     */
    public List<BodyType> getBodies() {
        if (bodies == null) {
            bodies = new ArrayList<BodyType>();
        }
        return this.bodies;
    }

    /**
     * Gets the value of the binaries property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the binaries property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getBinaries().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Binary }
     */
    public List<Binary> getBinaries() {
        if (binaries == null) {
            binaries = new ArrayList<Binary>();
        }
        return this.binaries;
    }


    /**
     * &lt;p&gt;Java class for anonymous complex type.
     * <p>
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * <p>
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;simpleContent&amp;gt;
     * &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;base64Binary"&amp;gt;
     * &amp;lt;attribute name="content-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
     * &amp;lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
     * &amp;lt;/extension&amp;gt;
     * &amp;lt;/simpleContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Binary {

        @XmlValue
        protected byte[] value;
        @XmlAttribute(name = "content-type", required = true)
        protected String contentType;
        @XmlAttribute(name = "id", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * Default no-arg constructor
         */
        public Binary() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Binary(final byte[] value, final String contentType, final String id) {
            this.value = value;
            this.contentType = contentType;
            this.id = id;
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * byte[]
         */
        public byte[] getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              byte[]
         */
        public void setValue(byte[] value) {
            this.value = value;
        }

        /**
         * Gets the value of the contentType property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getContentType() {
            return contentType;
        }

        /**
         * Sets the value of the contentType property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setContentType(String value) {
            this.contentType = value;
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
     * &amp;lt;element name="title-info" type="{http://www.gribuser.ru/xml/fictionbook/2.0}title-infoType"/&amp;gt;
     * &amp;lt;element name="src-title-info" type="{http://www.gribuser.ru/xml/fictionbook/2.0}title-infoType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="document-info"&amp;gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;complexContent&amp;gt;
     * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     * &amp;lt;sequence&amp;gt;
     * &amp;lt;element name="author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded"/&amp;gt;
     * &amp;lt;element name="program-used" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="date" type="{http://www.gribuser.ru/xml/fictionbook/2.0}dateType"/&amp;gt;
     * &amp;lt;element name="src-url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="src-ocr" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}token"/&amp;gt;
     * &amp;lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}float"/&amp;gt;
     * &amp;lt;element name="history" type="{http://www.gribuser.ru/xml/fictionbook/2.0}annotationType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &amp;lt;/element&amp;gt;
     * &amp;lt;element name="publish-info" minOccurs="0"&amp;gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;complexContent&amp;gt;
     * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     * &amp;lt;sequence&amp;gt;
     * &amp;lt;element name="book-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="city" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}gYear" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="isbn" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
     * &amp;lt;element name="sequence" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sequenceType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &amp;lt;/element&amp;gt;
     * &amp;lt;element name="custom-info" maxOccurs="unbounded" minOccurs="0"&amp;gt;
     * &amp;lt;complexType&amp;gt;
     * &amp;lt;simpleContent&amp;gt;
     * &amp;lt;extension base="&amp;lt;http://www.gribuser.ru/xml/fictionbook/2.0&amp;gt;textFieldType"&amp;gt;
     * &amp;lt;attribute name="info-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
     * &amp;lt;/extension&amp;gt;
     * &amp;lt;/simpleContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &amp;lt;/element&amp;gt;
     * &amp;lt;element name="output" type="{http://www.gribuser.ru/xml/fictionbook/2.0}shareInstructionType" maxOccurs="2" minOccurs="0"/&amp;gt;
     * &amp;lt;/sequence&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "titleInfo",
            "srcTitleInfo",
            "documentInfo",
            "publishInfo",
            "customInfos",
            "outputs"
    })
    public static class Description {

        @XmlElement(name = "title-info", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
        protected TitleInfoType titleInfo;
        @XmlElement(name = "src-title-info", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected TitleInfoType srcTitleInfo;
        @XmlElement(name = "document-info", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
        protected DocumentInfo documentInfo;
        @XmlElement(name = "publish-info", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected PublishInfo publishInfo;
        @XmlElement(name = "custom-info", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected List<CustomInfo> customInfos;
        @XmlElement(name = "output", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
        protected List<ShareInstructionType> outputs;

        /**
         * Default no-arg constructor
         */
        public Description() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Description(final TitleInfoType titleInfo, final TitleInfoType srcTitleInfo, final DocumentInfo documentInfo, final PublishInfo publishInfo, final List<CustomInfo> customInfos, final List<ShareInstructionType> outputs) {
            this.titleInfo = titleInfo;
            this.srcTitleInfo = srcTitleInfo;
            this.documentInfo = documentInfo;
            this.publishInfo = publishInfo;
            this.customInfos = customInfos;
            this.outputs = outputs;
        }

        /**
         * Gets the value of the titleInfo property.
         *
         * @return possible object is
         * {@link TitleInfoType }
         */
        public TitleInfoType getTitleInfo() {
            return titleInfo;
        }

        /**
         * Sets the value of the titleInfo property.
         *
         * @param value allowed object is
         *              {@link TitleInfoType }
         */
        public void setTitleInfo(TitleInfoType value) {
            this.titleInfo = value;
        }

        /**
         * Gets the value of the srcTitleInfo property.
         *
         * @return possible object is
         * {@link TitleInfoType }
         */
        public TitleInfoType getSrcTitleInfo() {
            return srcTitleInfo;
        }

        /**
         * Sets the value of the srcTitleInfo property.
         *
         * @param value allowed object is
         *              {@link TitleInfoType }
         */
        public void setSrcTitleInfo(TitleInfoType value) {
            this.srcTitleInfo = value;
        }

        /**
         * Gets the value of the documentInfo property.
         *
         * @return possible object is
         * {@link DocumentInfo }
         */
        public DocumentInfo getDocumentInfo() {
            return documentInfo;
        }

        /**
         * Sets the value of the documentInfo property.
         *
         * @param value allowed object is
         *              {@link DocumentInfo }
         */
        public void setDocumentInfo(DocumentInfo value) {
            this.documentInfo = value;
        }

        /**
         * Gets the value of the publishInfo property.
         *
         * @return possible object is
         * {@link PublishInfo }
         */
        public PublishInfo getPublishInfo() {
            return publishInfo;
        }

        /**
         * Sets the value of the publishInfo property.
         *
         * @param value allowed object is
         *              {@link PublishInfo }
         */
        public void setPublishInfo(PublishInfo value) {
            this.publishInfo = value;
        }

        /**
         * Gets the value of the customInfos property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the customInfos property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getCustomInfos().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link CustomInfo }
         */
        public List<CustomInfo> getCustomInfos() {
            if (customInfos == null) {
                customInfos = new ArrayList<CustomInfo>();
            }
            return this.customInfos;
        }

        /**
         * Gets the value of the outputs property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the outputs property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getOutputs().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link ShareInstructionType }
         */
        public List<ShareInstructionType> getOutputs() {
            if (outputs == null) {
                outputs = new ArrayList<ShareInstructionType>();
            }
            return this.outputs;
        }


        /**
         * &lt;p&gt;Java class for anonymous complex type.
         * <p>
         * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
         * <p>
         * &lt;pre&gt;
         * &amp;lt;complexType&amp;gt;
         * &amp;lt;simpleContent&amp;gt;
         * &amp;lt;extension base="&amp;lt;http://www.gribuser.ru/xml/fictionbook/2.0&amp;gt;textFieldType"&amp;gt;
         * &amp;lt;attribute name="info-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
         * &amp;lt;/extension&amp;gt;
         * &amp;lt;/simpleContent&amp;gt;
         * &amp;lt;/complexType&amp;gt;
         * &lt;/pre&gt;
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CustomInfo
                extends TextFieldType {

            @XmlAttribute(name = "info-type", required = true)
            protected String infoType;

            /**
             * Default no-arg constructor
             */
            public CustomInfo() {
                super();
            }

            /**
             * Fully-initialising value constructor
             */
            public CustomInfo(final String value, final String lang, final String infoType) {
                super(value, lang);
                this.infoType = infoType;
            }

            /**
             * Gets the value of the infoType property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getInfoType() {
                return infoType;
            }

            /**
             * Sets the value of the infoType property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setInfoType(String value) {
                this.infoType = value;
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
         * &amp;lt;element name="author" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded"/&amp;gt;
         * &amp;lt;element name="program-used" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="date" type="{http://www.gribuser.ru/xml/fictionbook/2.0}dateType"/&amp;gt;
         * &amp;lt;element name="src-url" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="src-ocr" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}token"/&amp;gt;
         * &amp;lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}float"/&amp;gt;
         * &amp;lt;element name="history" type="{http://www.gribuser.ru/xml/fictionbook/2.0}annotationType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}authorType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
         * &amp;lt;/sequence&amp;gt;
         * &amp;lt;/restriction&amp;gt;
         * &amp;lt;/complexContent&amp;gt;
         * &amp;lt;/complexType&amp;gt;
         * &lt;/pre&gt;
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "authors",
                "programUsed",
                "date",
                "srcUrls",
                "srcOcr",
                "id",
                "version",
                "history",
                "publishers"
        })
        public static class DocumentInfo {

            @XmlElement(name = "author", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
            protected List<AuthorType> authors;
            @XmlElement(name = "program-used", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType programUsed;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
            protected DateType date;
            @XmlElement(name = "src-url", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected List<String> srcUrls;
            @XmlElement(name = "src-ocr", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType srcOcr;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String id;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected float version;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected AnnotationType history;
            @XmlElement(name = "publisher", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected List<AuthorType> publishers;

            /**
             * Default no-arg constructor
             */
            public DocumentInfo() {
                super();
            }

            /**
             * Fully-initialising value constructor
             */
            public DocumentInfo(final List<AuthorType> authors, final TextFieldType programUsed, final DateType date, final List<String> srcUrls, final TextFieldType srcOcr, final String id, final float version, final AnnotationType history, final List<AuthorType> publishers) {
                this.authors = authors;
                this.programUsed = programUsed;
                this.date = date;
                this.srcUrls = srcUrls;
                this.srcOcr = srcOcr;
                this.id = id;
                this.version = version;
                this.history = history;
                this.publishers = publishers;
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
             * {@link AuthorType }
             */
            public List<AuthorType> getAuthors() {
                if (authors == null) {
                    authors = new ArrayList<AuthorType>();
                }
                return this.authors;
            }

            /**
             * Gets the value of the programUsed property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getProgramUsed() {
                return programUsed;
            }

            /**
             * Sets the value of the programUsed property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setProgramUsed(TextFieldType value) {
                this.programUsed = value;
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
             * Gets the value of the srcUrls property.
             * <p>
             * &lt;p&gt;
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the srcUrls property.
             * <p>
             * &lt;p&gt;
             * For example, to add a new item, do as follows:
             * &lt;pre&gt;
             * getSrcUrls().add(newItem);
             * &lt;/pre&gt;
             * <p>
             * <p>
             * &lt;p&gt;
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             */
            public List<String> getSrcUrls() {
                if (srcUrls == null) {
                    srcUrls = new ArrayList<String>();
                }
                return this.srcUrls;
            }

            /**
             * Gets the value of the srcOcr property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getSrcOcr() {
                return srcOcr;
            }

            /**
             * Sets the value of the srcOcr property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setSrcOcr(TextFieldType value) {
                this.srcOcr = value;
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
             * Gets the value of the version property.
             */
            public float getVersion() {
                return version;
            }

            /**
             * Sets the value of the version property.
             */
            public void setVersion(float value) {
                this.version = value;
            }

            /**
             * Gets the value of the history property.
             *
             * @return possible object is
             * {@link AnnotationType }
             */
            public AnnotationType getHistory() {
                return history;
            }

            /**
             * Sets the value of the history property.
             *
             * @param value allowed object is
             *              {@link AnnotationType }
             */
            public void setHistory(AnnotationType value) {
                this.history = value;
            }

            /**
             * Gets the value of the publishers property.
             * <p>
             * &lt;p&gt;
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the publishers property.
             * <p>
             * &lt;p&gt;
             * For example, to add a new item, do as follows:
             * &lt;pre&gt;
             * getPublishers().add(newItem);
             * &lt;/pre&gt;
             * <p>
             * <p>
             * &lt;p&gt;
             * Objects of the following type(s) are allowed in the list
             * {@link AuthorType }
             */
            public List<AuthorType> getPublishers() {
                if (publishers == null) {
                    publishers = new ArrayList<AuthorType>();
                }
                return this.publishers;
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
         * &amp;lt;element name="book-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="publisher" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="city" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}gYear" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="isbn" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
         * &amp;lt;element name="sequence" type="{http://www.gribuser.ru/xml/fictionbook/2.0}sequenceType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
         * &amp;lt;/sequence&amp;gt;
         * &amp;lt;/restriction&amp;gt;
         * &amp;lt;/complexContent&amp;gt;
         * &amp;lt;/complexType&amp;gt;
         * &lt;/pre&gt;
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "bookName",
                "publisher",
                "city",
                "year",
                "isbn",
                "sequences"
        })
        public static class PublishInfo {

            @XmlElement(name = "book-name", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType bookName;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType publisher;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType city;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            @XmlSchemaType(name = "gYear")
            protected XMLGregorianCalendar year;
            @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected TextFieldType isbn;
            @XmlElement(name = "sequence", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
            protected List<SequenceType> sequences;

            /**
             * Default no-arg constructor
             */
            public PublishInfo() {
                super();
            }

            /**
             * Fully-initialising value constructor
             */
            public PublishInfo(final TextFieldType bookName, final TextFieldType publisher, final TextFieldType city, final XMLGregorianCalendar year, final TextFieldType isbn, final List<SequenceType> sequences) {
                this.bookName = bookName;
                this.publisher = publisher;
                this.city = city;
                this.year = year;
                this.isbn = isbn;
                this.sequences = sequences;
            }

            /**
             * Gets the value of the bookName property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getBookName() {
                return bookName;
            }

            /**
             * Sets the value of the bookName property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setBookName(TextFieldType value) {
                this.bookName = value;
            }

            /**
             * Gets the value of the publisher property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getPublisher() {
                return publisher;
            }

            /**
             * Sets the value of the publisher property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setPublisher(TextFieldType value) {
                this.publisher = value;
            }

            /**
             * Gets the value of the city property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getCity() {
                return city;
            }

            /**
             * Sets the value of the city property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setCity(TextFieldType value) {
                this.city = value;
            }

            /**
             * Gets the value of the year property.
             *
             * @return possible object is
             * {@link XMLGregorianCalendar }
             */
            public XMLGregorianCalendar getYear() {
                return year;
            }

            /**
             * Sets the value of the year property.
             *
             * @param value allowed object is
             *              {@link XMLGregorianCalendar }
             */
            public void setYear(XMLGregorianCalendar value) {
                this.year = value;
            }

            /**
             * Gets the value of the isbn property.
             *
             * @return possible object is
             * {@link TextFieldType }
             */
            public TextFieldType getIsbn() {
                return isbn;
            }

            /**
             * Sets the value of the isbn property.
             *
             * @param value allowed object is
             *              {@link TextFieldType }
             */
            public void setIsbn(TextFieldType value) {
                this.isbn = value;
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
     * &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;string"&amp;gt;
     * &amp;lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
     * &amp;lt;/extension&amp;gt;
     * &amp;lt;/simpleContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Stylesheet {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "type", required = true)
        protected String typeAttribute;

        /**
         * Default no-arg constructor
         */
        public Stylesheet() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Stylesheet(final String value, final String typeAttribute) {
            this.value = value;
            this.typeAttribute = typeAttribute;
        }

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
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

}
