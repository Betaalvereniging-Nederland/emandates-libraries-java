
package net.emandates.merchant.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


class Utils {
    public static XMLGregorianCalendar UtcNow() throws DatatypeConfigurationException {
        GregorianCalendar currentDateTimestamp = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        currentDateTimestamp.setTimeInMillis(System.currentTimeMillis());

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(currentDateTimestamp);
    }
    
    public static String serialize(Object o, Class... classes) throws PropertyException, JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(classes);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        m.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
        m.marshal(o, sw);

        return sw.toString();
    }
    
    public static String serialize(Node node) throws TransformerConfigurationException, TransformerException {
        StringWriter sw = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }
    
    public static String serializeWithoutDeclaration(Node node) throws TransformerConfigurationException, TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }
    
    public static <T> T deserialize(String xml, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller u = context.createUnmarshaller();
        
        Object o = u.unmarshal(new StringReader(xml));
        return type.cast(o);
    }
    
    public static <T> T deserialize(Node node, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller u = context.createUnmarshaller();
        JAXBElement el = (JAXBElement) u.unmarshal(node);
        
        Object o = el.getValue();
        return type.cast(o);
    }
    
    public static String sha1Hex(final byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(data);
        byte fp[] = sha1.digest();
        String fingerprint = "";
        for (int i = 0; i < fp.length; i++) {
            String f = "00" + Integer.toHexString(fp[i]);
            fingerprint = fingerprint + f.substring(f.length() - 2);
        }
        fingerprint = fingerprint.toUpperCase();
        
        return fingerprint;
    }
    
    public static String copy(InputStream input) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));      
        
        String read = reader.readLine();
        while(read != null) {
            builder.append(read);
            builder.append("\n");
            read = reader.readLine();
        }
        
        return builder.toString();
    }
}
