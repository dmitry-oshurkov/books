package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Information about a single author
 * <p>
 * &lt;p&gt;Java class for authorType complex type.
 * <p>
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * <p>
 * &lt;pre&gt;
 * &amp;lt;complexType name="authorType"&amp;gt;
 * &amp;lt;complexContent&amp;gt;
 * &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 * &amp;lt;choice&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="first-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType"/&amp;gt;
 * &amp;lt;element name="middle-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="last-name" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType"/&amp;gt;
 * &amp;lt;element name="nickname" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="home-page" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;sequence&amp;gt;
 * &amp;lt;element name="nickname" type="{http://www.gribuser.ru/xml/fictionbook/2.0}textFieldType"/&amp;gt;
 * &amp;lt;element name="home-page" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;/choice&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authorType", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0", propOrder = {
        "firstName",
        "middleName",
        "lastName",
        "nickname",
        "homePages",
        "emails",
        "id"
})
@XmlSeeAlso({
        TitleInfoType.Author.class
})
public class AuthorType {

    @XmlElement(name = "first-name", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TextFieldType firstName;
    @XmlElement(name = "middle-name", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TextFieldType middleName;
    @XmlElement(name = "last-name", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TextFieldType lastName;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected TextFieldType nickname;
    @XmlElement(name = "home-page", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<String> homePages;
    @XmlElement(name = "email", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    protected List<String> emails;
    @XmlElement(namespace = "http://www.gribuser.ru/xml/fictionbook/2.0")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String id;

    /**
     * Default no-arg constructor
     */
    public AuthorType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public AuthorType(final TextFieldType firstName, final TextFieldType middleName, final TextFieldType lastName, final TextFieldType nickname, final List<String> homePages, final List<String> emails, final String id) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homePages = homePages;
        this.emails = emails;
        this.id = id;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setFirstName(TextFieldType value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setMiddleName(TextFieldType value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setLastName(TextFieldType value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the nickname property.
     *
     * @return possible object is
     * {@link TextFieldType }
     */
    public TextFieldType getNickname() {
        return nickname;
    }

    /**
     * Sets the value of the nickname property.
     *
     * @param value allowed object is
     *              {@link TextFieldType }
     */
    public void setNickname(TextFieldType value) {
        this.nickname = value;
    }

    /**
     * Gets the value of the homePages property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the homePages property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getHomePages().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getHomePages() {
        if (homePages == null) {
            homePages = new ArrayList<String>();
        }
        return this.homePages;
    }

    /**
     * Gets the value of the emails property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the emails property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getEmails().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getEmails() {
        if (emails == null) {
            emails = new ArrayList<String>();
        }
        return this.emails;
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
