package net.emandates.merchant.library;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

class Logger implements ILogger {
    private String version = "";

    public Logger() {
    }

    public Logger(String packageVersion) {
        version = packageVersion;
    }

    private void Write(Configuration config, String content) {
        if (config.isLogsEnabled()) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.INFO, versionedLog(content));
        }
    }

    private void Write(Configuration config, Throwable e) {
        if (config.isLogsEnabled()) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.INFO, versionedLog(null), e);
        }
    }

    private void LogFile(Configuration config, String content) {
        if (!config.isServiceLogsEnabled()) {
            return;
        }
        
        try {
            Document doc = DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().parse(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
            
            String documentElementName = doc.getDocumentElement().getTagName().replace(':', '-');
            String fileName = config.getServiceLogsPattern();

            Calendar c = GregorianCalendar.getInstance();
            
            fileName = fileName.replace("%Y", String.valueOf(c.get(GregorianCalendar.YEAR)));
            fileName = fileName.replace("%M", String.format("%02d", c.get(GregorianCalendar.MONTH) + 1)); // January is 0
            fileName = fileName.replace("%D", String.format("%02d", c.get(GregorianCalendar.DAY_OF_MONTH)));
            fileName = fileName.replace("%h", String.format("%02d", c.get(GregorianCalendar.HOUR_OF_DAY)));
            fileName = fileName.replace("%m", String.format("%02d", c.get(GregorianCalendar.MINUTE)));
            fileName = fileName.replace("%s", String.format("%02d", c.get(GregorianCalendar.SECOND)));
            fileName = fileName.replace("%f", String.format("%03d", c.get(GregorianCalendar.MILLISECOND)));
            fileName = fileName.replace("%a", documentElementName);

            File f = new File(config.getServiceLogsLocation() + File.separator + fileName);
            f.getParentFile().mkdirs();

            try (OutputStreamWriter char_output = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8.newEncoder())) {
                char_output.write(content);
            }
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Write(config, ex);
        }
    }

    @Override
    public void Log(Configuration config, String message, Object... args) {
        String output = String.format(message, args);
        Write(config, output);
    }

    @Override
    public void LogXmlMessage(Configuration config, String content) {
        LogFile(config, content);
    }

    private String versionedLog(String message) {
        if(message != null) {
            return String.format("v[%s] %s", version, message);
        }
        return String.format("v[%s]", version);
    }
}
