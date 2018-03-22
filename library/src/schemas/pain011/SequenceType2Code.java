
package schemas.pain011;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SequenceType2Code.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SequenceType2Code">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RCUR"/>
 *     &lt;enumeration value="OOFF"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SequenceType2Code")
@XmlEnum
public enum SequenceType2Code {

    RCUR,
    OOFF;

    public String value() {
        return name();
    }

    public static SequenceType2Code fromValue(String v) {
        return valueOf(v);
    }

}
