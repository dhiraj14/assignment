import java.io.*;
import java.util.Scanner;

public class FileParser{
public static void main(String args[]){

Scanner sc=new Scanner(System.in);
System.out.println("Enter the filename");
String filename=sc.nextLine();
String line;
try {
            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
		//line = bufferedReader.readLine();
		//System.out.println(line);        
	    while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
		String tline=line.trim();
		String arr[]=tline.split(" ");
		
		if(arr[0].equals("class")){
			System.out.println("Class name is: "+arr[1]);			
		}            
		} 
		//String arr[]=line.split(" ");
		//if(arr[0].equals("import")){
		//System.out.println("the given file is a java file");			
		//}
		//int i=0;
		//System.out.println(arr.length);
		
                 bufferedReader.close();            
        }
        catch(Exception ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        

}
}
