package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for shareModesType.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="shareModesType"&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;enumeration value="free"/&amp;gt;
 * &amp;lt;enumeration value="paid"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlType(name = "shareModesType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
@XmlEnum
public enum ShareModesType {

    @XmlEnumValue("free")
    FREE("free"),
    @XmlEnumValue("paid")
    PAID("paid");
    private final String value;

    ShareModesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ShareModesType fromValue(String v) {
        for (ShareModesType c : ShareModesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
