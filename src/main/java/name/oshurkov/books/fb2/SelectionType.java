package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * Корневой элемент для единичного блока выделения
 * <p>
 * &lt;p&gt;Java class for SelectionType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="SelectionType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="Note" maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;element name="Extract" minOccurs="0"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
 * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}imageType"/&amp;gt;
 * &amp;lt;element name="poem" type="{http://www.gribuser.ru/xml/fictionbook/2.0}poemType"/&amp;gt;
 * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
 * &amp;lt;element name="cite" type="{http://www.gribuser.ru/xml/fictionbook/2.0}citeType"/&amp;gt;
 * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
 * &amp;lt;element name="table" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tableType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute name="original-location" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}XPointer" /&amp;gt;
 * &amp;lt;attribute name="selection-text" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute name="selection"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 * &amp;lt;pattern value="xpointer\(point\(here\(\)(/[0-9]+)+\.[0-9]+\)/range-to\(point\(here\(\)(/[0-9]+)+\.[0-9]+\)\)\)"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="id" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}UUIDType" /&amp;gt;
 * &amp;lt;attribute name="selection" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}XRange" /&amp;gt;
 * &amp;lt;attribute name="art-id" use="required" type="{http://www.w3.org/2001/XMLSchema}token" /&amp;gt;
 * &amp;lt;attribute name="group" use="required"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}byte"&amp;gt;
 * &amp;lt;minInclusive value="0"/&amp;gt;
 * &amp;lt;maxInclusive value="9"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;attribute name="last-update" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&amp;gt;
 * &amp;lt;attribute name="class"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}Name"&amp;gt;
 * &amp;lt;minLength value="1"/&amp;gt;
 * &amp;lt;maxLength value="30"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;attribute name="title"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString"&amp;gt;
 * &amp;lt;minLength value="1"/&amp;gt;
 * &amp;lt;maxLength value="100"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;attribute name="percent"&amp;gt;
 * &amp;lt;simpleType&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}byte"&amp;gt;
 * &amp;lt;minInclusive value="0"/&amp;gt;
 * &amp;lt;maxInclusive value="100"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &amp;lt;/attribute&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectionType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", propOrder = {
        "notes",
        "extract"
})
public class SelectionType {

    @XmlElement(name = "Note", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup")
    protected List<Note> notes;
    @XmlElement(name = "Extract", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup")
    protected Extract extract;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlAttribute(name = "selection", required = true)
    protected String selection;
    @XmlAttribute(name = "art-id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String artId;
    @XmlAttribute(name = "group", required = true)
    protected byte group;
    @XmlAttribute(name = "last-update", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlAttribute(name = "class")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String clazz;
    @XmlAttribute(name = "title")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String title;
    @XmlAttribute(name = "percent")
    protected Byte percent;

    /**
     * Default no-arg constructor
     */
    public SelectionType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public SelectionType(final List<Note> notes, final Extract extract, final String id, final String selection, final String artId, final byte group, final XMLGregorianCalendar lastUpdate, final String clazz, final String title, final Byte percent) {
        this.notes = notes;
        this.extract = extract;
        this.id = id;
        this.selection = selection;
        this.artId = artId;
        this.group = group;
        this.lastUpdate = lastUpdate;
        this.clazz = clazz;
        this.title = title;
        this.percent = percent;
    }

    /**
     * Gets the value of the notes property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the notes property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getNotes().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Note }
     */
    public List<Note> getNotes() {
        if (notes == null) {
            notes = new ArrayList<Note>();
        }
        return this.notes;
    }

    /**
     * Gets the value of the extract property.
     *
     * @return possible object is
     * {@link Extract }
     */
    public Extract getExtract() {
        return extract;
    }

    /**
     * Sets the value of the extract property.
     *
     * @param value allowed object is
     *              {@link Extract }
     */
    public void setExtract(Extract value) {
        this.extract = value;
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
     * Gets the value of the selection property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSelection() {
        return selection;
    }

    /**
     * Sets the value of the selection property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSelection(String value) {
        this.selection = value;
    }

    /**
     * Gets the value of the artId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getArtId() {
        return artId;
    }

    /**
     * Sets the value of the artId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setArtId(String value) {
        this.artId = value;
    }

    /**
     * Gets the value of the group property.
     */
    public byte getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     */
    public void setGroup(byte value) {
        this.group = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Gets the value of the clazz property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the title property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the percent property.
     *
     * @return possible object is
     * {@link Byte }
     */
    public Byte getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     *
     * @param value allowed object is
     *              {@link Byte }
     */
    public void setPercent(Byte value) {
        this.percent = value;
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
     * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
     * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
     * &amp;lt;element name="image" type="{http://www.gribuser.ru/xml/fictionbook/2.0}imageType"/&amp;gt;
     * &amp;lt;element name="poem" type="{http://www.gribuser.ru/xml/fictionbook/2.0}poemType"/&amp;gt;
     * &amp;lt;element name="subtitle" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
     * &amp;lt;element name="cite" type="{http://www.gribuser.ru/xml/fictionbook/2.0}citeType"/&amp;gt;
     * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
     * &amp;lt;element name="table" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tableType"/&amp;gt;
     * &amp;lt;/choice&amp;gt;
     * &amp;lt;attribute name="original-location" use="required" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}XPointer" /&amp;gt;
     * &amp;lt;attribute name="selection-text" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
     * &amp;lt;attribute name="selection"&amp;gt;
     * &amp;lt;simpleType&amp;gt;
     * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
     * &amp;lt;pattern value="xpointer\(point\(here\(\)(/[0-9]+)+\.[0-9]+\)/range-to\(point\(here\(\)(/[0-9]+)+\.[0-9]+\)\)\)"/&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/simpleType&amp;gt;
     * &amp;lt;/attribute&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "psAndImagesAndPoems"
    })
    public static class Extract {

        @XmlElementRefs({
                @XmlElementRef(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "image", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "poem", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "subtitle", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "cite", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "table", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<?>> psAndImagesAndPoems;
        @XmlAttribute(name = "original-location", required = true)
        protected String originalLocation;
        @XmlAttribute(name = "selection-text")
        protected String selectionText;
        @XmlAttribute(name = "selection")
        protected String selection;

        /**
         * Default no-arg constructor
         */
        public Extract() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Extract(final List<JAXBElement<?>> psAndImagesAndPoems, final String originalLocation, final String selectionText, final String selection) {
            this.psAndImagesAndPoems = psAndImagesAndPoems;
            this.originalLocation = originalLocation;
            this.selectionText = selectionText;
            this.selection = selection;
        }

        /**
         * Gets the value of the psAndImagesAndPoems property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the psAndImagesAndPoems property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getPSAndImagesAndPoems().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link PType }{@code >}
         * {@link JAXBElement }{@code <}{@link ImageType }{@code >}
         * {@link JAXBElement }{@code <}{@link PoemType }{@code >}
         * {@link JAXBElement }{@code <}{@link PType }{@code >}
         * {@link JAXBElement }{@code <}{@link CiteType }{@code >}
         * {@link JAXBElement }{@code <}{@link Object }{@code >}
         * {@link JAXBElement }{@code <}{@link TableType }{@code >}
         */
        public List<JAXBElement<?>> getPSAndImagesAndPoems() {
            if (psAndImagesAndPoems == null) {
                psAndImagesAndPoems = new ArrayList<JAXBElement<?>>();
            }
            return this.psAndImagesAndPoems;
        }

        /**
         * Gets the value of the originalLocation property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getOriginalLocation() {
            return originalLocation;
        }

        /**
         * Sets the value of the originalLocation property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setOriginalLocation(String value) {
            this.originalLocation = value;
        }

        /**
         * Gets the value of the selectionText property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSelectionText() {
            return selectionText;
        }

        /**
         * Sets the value of the selectionText property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSelectionText(String value) {
            this.selectionText = value;
        }

        /**
         * Gets the value of the selection property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSelection() {
            return selection;
        }

        /**
         * Sets the value of the selection property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSelection(String value) {
            this.selection = value;
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
     * &amp;lt;choice maxOccurs="unbounded" minOccurs="0"&amp;gt;
     * &amp;lt;element name="p" type="{http://www.gribuser.ru/xml/fictionbook/2.0}pType"/&amp;gt;
     * &amp;lt;element name="empty-line" type="{http://www.w3.org/2001/XMLSchema}anyType"/&amp;gt;
     * &amp;lt;/choice&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "psAndEmptyLines"
    })
    public static class Note {

        @XmlElements({
                @XmlElement(name = "p", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup", type = PType.class),
                @XmlElement(name = "empty-line", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup")
        })
        protected List<Object> psAndEmptyLines;

        /**
         * Default no-arg constructor
         */
        public Note() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Note(final List<Object> psAndEmptyLines) {
            this.psAndEmptyLines = psAndEmptyLines;
        }

        /**
         * Gets the value of the psAndEmptyLines property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the psAndEmptyLines property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getPSAndEmptyLines().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link PType }
         * {@link Object }
         */
        public List<Object> getPSAndEmptyLines() {
            if (psAndEmptyLines == null) {
                psAndEmptyLines = new ArrayList<Object>();
            }
            return this.psAndEmptyLines;
        }

    }

}
