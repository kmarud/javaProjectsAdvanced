package lab08_JavaFX;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

public class XmlGenerator {
	  public static void main(String... arg) throws Exception {
		  PostCodes postCodes = new PostCodes();
	        postCodes.add(new PostCode("43-300", "Bielsko-Biała"));
	        postCodes.add(new PostCode("43-302", "Bielsko-Biała"));
	        postCodes.add(new PostCode("43-303", "Bielsko-Biała"));
	        postCodes.add(new PostCode("43-304", "Bielsko-Biała"));
	        postCodes.add(new PostCode("43-308", "Bielsko-Biała"));
	        postCodes.add(new PostCode("43-309", "Bielsko-Biała"));
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(PostCodes.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        //WYŚWIETLENIE NA OUT
	        jaxbMarshaller.marshal(postCodes, System.out);
	    }
}


 
/**
 *
 * @author Krzysztof Jelonek
 */
@XmlRootElement(name="pozycja")
class PostCode {
    private String code;
    private String city;
    
    public PostCode() {
    }
    
    public PostCode(String code, String city) {
        this.code = code;
        this.city = city;
    }
 
    @XmlAttribute(name="kod")
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    @XmlAttribute(name="miasto")
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
}

 
/**
 *
 * @author Krzysztof Jelonek
 */
@XmlRootElement(name="kodyPocztowe")
class PostCodes {
    private List<PostCode> postCodes = new ArrayList<PostCode>();
    
    public void add(PostCode postCode) {
        this.postCodes.add(postCode);
    }
 
    @XmlElements(@XmlElement(name="pozycja"))
    public List<PostCode> getPostCodes() {
        return postCodes;
    }
 
    public void setPostCodes(List<PostCode> postCodes) {
        this.postCodes = postCodes;
    }
    
}