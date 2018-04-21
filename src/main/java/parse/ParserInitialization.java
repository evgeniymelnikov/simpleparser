package parse;

import parse.model.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ParserInitialization {
    public final String filePath = "src/main/resources/process.properties";
    public Configuration fromXmlToObject(String filePath) throws Exception{

        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller un = jaxbContext.createUnmarshaller();

        return (Configuration) un.unmarshal(new File(filePath));
    }
}
