//package lab08_JavaFX;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlElements;
//import javax.xml.bind.annotation.XmlRootElement;
//
//public class XmlGenerator {
//	  public static void main(String... arg) throws Exception {
//
//		  Competition comp1 = new Competition();
//		  comp1.setNazwa("zawody1");
//		  
//		  Competition comp2 = new Competition();
//		  comp2.setNazwa("zawody2");
//		  
//		  Tabela tabela = new Tabela();
//		  
//		  Player player = new Player("A");
//		  player.setName("Kamil");
//		  player.setPlace(1);
//		  player.setPoints(111);
//		  player.setShows(4);
//		  player.setList(comp1, comp2);
//		  
//		  
//		  Player player2 = new Player("A");
//		  player2.setName("Ola");
//		  player2.setPlace(2);
//		  player2.setPoints(111);
//		  player2.setShows(4);
//		  player2.setList(comp1);
//		  
//		  tabela.add(player);
//	        tabela.add(player2);
//	        JAXBContext jaxbContext = JAXBContext.newInstance(Tabela.class);
//	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//	        
//	        //WYŚWIETLENIE NA OUT
//	        jaxbMarshaller.marshal(tabela, System.out);
//	    }
//}
//
//
// 
///**
// *
// * @author Krzysztof Jelonek
// */
//@XmlRootElement(name="pozycja")
//class PostCode {
//    private String code;
//    private String city;
//    
//    public PostCode() {
//    }
//    
//    public PostCode(String code, String city) {
//        this.code = code;
//        this.city = city;
//    }
// 
//    @XmlAttribute(name="kod")
//    public String getCode() {
//        return code;
//    }
// 
//    public void setCode(String code) {
//        this.code = code;
//    }
// 
//    @XmlAttribute(name="miasto")
//    public String getCity() {
//        return city;
//    }
// 
//    public void setCity(String city) {
//        this.city = city;
//    }
//}
//
// 
///**
// *
// * @author Krzysztof Jelonek
// */
//@XmlRootElement(name="kodyPocztowe")
//class PostCodes {
//    private List<PostCode> postCodes = new ArrayList<PostCode>();
//    
//    public void add(PostCode postCode) {
//        this.postCodes.add(postCode);
//    }
// 
//    @XmlElements(@XmlElement(name="pozycja"))
//    public List<PostCode> getPostCodes() {
//        return postCodes;
//    }
// 
//    public void setPostCodes(List<PostCode> postCodes) {
//        this.postCodes = postCodes;
//    }
//    
//}