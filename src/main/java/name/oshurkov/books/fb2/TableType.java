package name.oshurkov.books.fb2;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Basic html-like tables
 * <p>
 * &lt;p&gt;Java class for tableType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="tableType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="tr" maxOccurs="unbounded"&amp;gt;
 * &amp;lt;complexType&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice maxOccurs="unbounded"&amp;gt;
 * &amp;lt;element name="th" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tdType"/&amp;gt;
 * &amp;lt;element name="td" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tdType"/&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;attribute name="align" type="{http://www.gribuser.ru/xml/fictionbook/2.0}alignType" default="left" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &amp;lt;/element&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="style" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tableType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "trs"
})
public class TableType {

    @XmlElement(name = "tr", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", required = true)
    protected List<Tr> trs;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Default no-arg constructor
     */
    public TableType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public TableType(final List<Tr> trs, final String style, final String id) {
        this.trs = trs;
        this.style = style;
        this.id = id;
    }

    /**
     * Gets the value of the trs property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the trs property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getTrs().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link Tr }
     */
    public List<Tr> getTrs() {
        if (trs == null) {
            trs = new ArrayList<Tr>();
        }
        return this.trs;
    }

    /**
     * Gets the value of the style property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStyle(String value) {
        this.style = value;
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
     * &amp;lt;choice maxOccurs="unbounded"&amp;gt;
     * &amp;lt;element name="th" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tdType"/&amp;gt;
     * &amp;lt;element name="td" type="{http://www.gribuser.ru/xml/fictionbook/2.0}tdType"/&amp;gt;
     * &amp;lt;/choice&amp;gt;
     * &amp;lt;attribute name="align" type="{http://www.gribuser.ru/xml/fictionbook/2.0}alignType" default="left" /&amp;gt;
     * &amp;lt;/restriction&amp;gt;
     * &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "thsAndTds"
    })
    public static class Tr {

        @XmlElementRefs({
                @XmlElementRef(name = "th", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false),
                @XmlElementRef(name = "td", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<TdType>> thsAndTds;
        @XmlAttribute(name = "align")
        protected AlignType align;

        /**
         * Default no-arg constructor
         */
        public Tr() {
            super();
        }

        /**
         * Fully-initialising value constructor
         */
        public Tr(final List<JAXBElement<TdType>> thsAndTds, final AlignType align) {
            this.thsAndTds = thsAndTds;
            this.align = align;
        }

        /**
         * Gets the value of the thsAndTds property.
         * <p>
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the thsAndTds property.
         * <p>
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         * getThsAndTds().add(newItem);
         * &lt;/pre&gt;
         * <p>
         * <p>
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link TdType }{@code >}
         * {@link JAXBElement }{@code <}{@link TdType }{@code >}
         */
        public List<JAXBElement<TdType>> getThsAndTds() {
            if (thsAndTds == null) {
                thsAndTds = new ArrayList<JAXBElement<TdType>>();
            }
            return this.thsAndTds;
        }

        /**
         * Gets the value of the align property.
         *
         * @return possible object is
         * {@link AlignType }
         */
        public AlignType getAlign() {
            if (align == null) {
                return AlignType.LEFT;
            } else {
                return align;
            }
        }

        /**
         * Sets the value of the align property.
         *
         * @param value allowed object is
         *              {@link AlignType }
         */
        public void setAlign(AlignType value) {
            this.align = value;
        }

    }

}
