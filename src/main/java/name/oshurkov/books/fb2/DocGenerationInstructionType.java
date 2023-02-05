package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for docGenerationInstructionType.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="docGenerationInstructionType"&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&amp;gt;
 * &amp;lt;enumeration value="require"/&amp;gt;
 * &amp;lt;enumeration value="allow"/&amp;gt;
 * &amp;lt;enumeration value="deny"/&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlType(name = "docGenerationInstructionType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
@XmlEnum
public enum DocGenerationInstructionType {

    @XmlEnumValue("require")
    REQUIRE("require"),
    @XmlEnumValue("allow")
    ALLOW("allow"),
    @XmlEnumValue("deny")
    DENY("deny");
    private final String value;

    DocGenerationInstructionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DocGenerationInstructionType fromValue(String v) {
        for (DocGenerationInstructionType c : DocGenerationInstructionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
