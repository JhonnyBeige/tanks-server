package gtanks.battles.maps.parser;

import gtanks.battles.maps.parser.map.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Parser {
    private Unmarshaller unmarshaller;

    public Parser() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Map.class);
        this.unmarshaller = jc.createUnmarshaller();
    }

    public Map parseMap(File file) throws JAXBException {
        return (Map) this.unmarshaller.unmarshal(file);
    }
}
