package lab08_JavaFX;

import javax.xml.bind.annotation.*;


//@XmlRootElement
@XmlType
public class Competition{
	
	public Competition(){}
	String name;
	public Competition(String name){
		this.name = name;
	}
	//
	
	
	@XmlElement(name="nazwa")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

}

