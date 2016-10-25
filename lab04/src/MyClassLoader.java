import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLConnection;


public class MyClassLoader extends ClassLoader{
	
	private String pathToDirecory;
	public MyClassLoader(){
		super(MyClassLoader.class.getClassLoader());
	}
	
	public void setPathToDirectory(String pathToDirectory){
		this.pathToDirecory = pathToDirectory;
	}
	
	public Class findClass(String className){
		try{
			System.out.println("loading class " + className);
			String url = "file:" + pathToDirecory + className + ".class";
			URL myURL = new URL(url);
			URLConnection connection = myURL.openConnection();
			InputStream input = connection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();
			
			while(data != -1){
				buffer.write(data);
				data = input.read();
			}
			input.close();
			
			byte[] classData = buffer.toByteArray();
			
			
			return defineClass(className, classData, 0, classData.length);

			/*Annotation[] annotations = aClass.getAnnotations();

			for(Annotation annotation : annotations){
			    if(annotation instanceof Decorator){
			    	return aClass;
			    }
			}
			
			System.out.println("class " + className + "is not a decorator class");*/
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
