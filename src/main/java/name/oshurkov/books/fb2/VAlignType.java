package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for vAlignType.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="vAlignType"&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;enumeration value="top"/&amp;gt;
 * &amp;lt;enumeration value="middle"/&amp;gt;
 * &amp;lt;enumeration value="bottom"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlType(name = "vAlignType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
@XmlEnum
public enum VAlignType {

    @XmlEnumValue("top")
    TOP("top"),
    @XmlEnumValue("middle")
    MIDDLE("middle"),
    @XmlEnumValue("bottom")
    BOTTOM("bottom");
    private final String value;

    VAlignType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VAlignType fromValue(String v) {
        for (VAlignType c : VAlignType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
