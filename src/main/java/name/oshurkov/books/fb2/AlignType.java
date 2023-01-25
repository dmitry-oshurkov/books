package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for alignType.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="alignType"&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;enumeration value="left"/&amp;gt;
 * &amp;lt;enumeration value="right"/&amp;gt;
 * &amp;lt;enumeration value="center"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlType(name = "alignType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
@XmlEnum
public enum AlignType {

    @XmlEnumValue("left")
    LEFT("left"),
    @XmlEnumValue("right")
    RIGHT("right"),
    @XmlEnumValue("center")
    CENTER("center");
    private final String value;

    AlignType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlignType fromValue(String v) {
        for (AlignType c : AlignType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
