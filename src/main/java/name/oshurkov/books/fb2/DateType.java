package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A human readable date, maybe not exact, with an optional computer readable variant
 * <p>
 * &lt;p&gt;Java class for dateType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="dateType"&amp;gt;
 * &amp;lt;simpleContent&amp;gt;
 * &amp;lt;extension base="&amp;lt;http://www.w3.org/2001/XMLSchema&amp;gt;string"&amp;gt;
 * &amp;lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}date" /&amp;gt;
 * &amp;lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/simpleContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dateType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "value"
})
public class DateType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "value")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar valueAttribute;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Default no-arg constructor
     */
    public DateType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public DateType(final String value, final XMLGregorianCalendar valueAttribute, final String lang) {
        this.value = value;
        this.valueAttribute = valueAttribute;
        this.lang = lang;
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
     * Gets the value of the valueAttribute property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getValueAttribute() {
        return valueAttribute;
    }

    /**
     * Sets the value of the valueAttribute property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setValueAttribute(XMLGregorianCalendar value) {
        this.valueAttribute = value;
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
