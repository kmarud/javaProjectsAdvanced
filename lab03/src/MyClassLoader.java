import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class MyClassLoader extends ClassLoader{
	public MyClassLoader(){
		super(MyClassLoader.class.getClassLoader());
	}
	
	public Class findClass(String className){
		try{
			System.out.println("loading class " + className);
			String url = "file:C:/Users/Kamil/workspace/javaProjectsAdvanced/lab03/decorators/" + className + ".class";
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
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
