package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;


/**
 * &lt;p&gt;Java class for tdType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="tdType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;extension base="{http://www.gribuser.ru/xml/fictionbook/2.0}styleType"&amp;gt;
 * &amp;lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" /&amp;gt;
 * &amp;lt;attribute name="style" type="{http://www.w3.org/2001/XMLSchema}string" /&amp;gt;
 * &amp;lt;attribute name="colspan" type="{http://www.w3.org/2001/XMLSchema}integer" /&amp;gt;
 * &amp;lt;attribute name="rowspan" type="{http://www.w3.org/2001/XMLSchema}integer" /&amp;gt;
 * &amp;lt;attribute name="align" type="{http://www.gribuser.ru/xml/fictionbook/2.0}alignType" default="left" /&amp;gt;
 * &amp;lt;attribute name="valign" type="{http://www.gribuser.ru/xml/fictionbook/2.0}vAlignType" default="top" /&amp;gt;
 * &amp;lt;/extension&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tdType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
public class TdType
        extends StyleType {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "style")
    protected String style;
    @XmlAttribute(name = "colspan")
    protected BigInteger colspan;
    @XmlAttribute(name = "rowspan")
    protected BigInteger rowspan;
    @XmlAttribute(name = "align")
    protected AlignType align;
    @XmlAttribute(name = "valign")
    protected VAlignType valign;

    /**
     * Default no-arg constructor
     */
    public TdType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public TdType(final List<Serializable> content, final String lang, final String id, final String style, final BigInteger colspan, final BigInteger rowspan, final AlignType align, final VAlignType valign) {
        super(content, lang);
        this.id = id;
        this.style = style;
        this.colspan = colspan;
        this.rowspan = rowspan;
        this.align = align;
        this.valign = valign;
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
     * Gets the value of the colspan property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getColspan() {
        return colspan;
    }

    /**
     * Sets the value of the colspan property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setColspan(BigInteger value) {
        this.colspan = value;
    }

    /**
     * Gets the value of the rowspan property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getRowspan() {
        return rowspan;
    }

    /**
     * Sets the value of the rowspan property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setRowspan(BigInteger value) {
        this.rowspan = value;
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

    /**
     * Gets the value of the valign property.
     *
     * @return possible object is
     * {@link VAlignType }
     */
    public VAlignType getValign() {
        if (valign == null) {
            return VAlignType.TOP;
        } else {
            return valign;
        }
    }

    /**
     * Sets the value of the valign property.
     *
     * @param value allowed object is
     *              {@link VAlignType }
     */
    public void setValign(VAlignType value) {
        this.valign = value;
    }

}
