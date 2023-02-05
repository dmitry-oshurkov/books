package name.oshurkov.books.fb2;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
 * &amp;lt;element name="Selection" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}SelectionType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 * &amp;lt;/sequence&amp;gt;
 * &amp;lt;attribute name="lock-id" type="{http://www.gribuser.ru/xml/fictionbook/2.0/markup}UUIDType" /&amp;gt;
 * &amp;lt;/restriction&amp;gt;
 * &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "selections"
})
@XmlRootElement(name = "FictionBookMarkup", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup")
public class FictionBookMarkup {

    @XmlElement(name = "Selection", namespace = "http://www.gribuser.ru/xml/fictionbook/2.0/markup")
    protected List<SelectionType> selections;
    @XmlAttribute(name = "lock-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String lockId;

    /**
     * Default no-arg constructor
     */
    public FictionBookMarkup() {
        super();
    }

    /**
     * Fully-initialising value constructor
     */
    public FictionBookMarkup(final List<SelectionType> selections, final String lockId) {
        this.selections = selections;
        this.lockId = lockId;
    }

    /**
     * Gets the value of the selections property.
     * <p>
     * &lt;p&gt;
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the selections property.
     * <p>
     * &lt;p&gt;
     * For example, to add a new item, do as follows:
     * &lt;pre&gt;
     * getSelections().add(newItem);
     * &lt;/pre&gt;
     * <p>
     * <p>
     * &lt;p&gt;
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionType }
     */
    public List<SelectionType> getSelections() {
        if (selections == null) {
            selections = new ArrayList<SelectionType>();
        }
        return this.selections;
    }

    /**
     * Gets the value of the lockId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLockId() {
        return lockId;
    }

    /**
     * Sets the value of the lockId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLockId(String value) {
        this.lockId = value;
    }

}
