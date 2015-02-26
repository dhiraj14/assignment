import java.io.File;
import java.util.Scanner;

public class ReadDir {
   public static void main(String[] args) {
      
      File file = null;
      String[] paths;
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the filename you want to check:");
	String filename=sc.next();
	System.out.println(filename);		            
	String file_path="assignment/lang_assignment/"+filename;      
	


	try{      
         // create new file object
         file = new File(file_path);                                
         // array of files and directory
         paths = file.list();
         System.out.println(paths);
	System.out.println(file);
	         
	// for each name in the path array
         /*for(String path:paths)
         {
            // prints filename and directory name
            System.out.println(path);
		String ext[]=path.split("\\.");
		System.out.println(path+" is a "+ext[ext.length-1]+" file");
         }*/
      }catch(Exception e){
         // if any error occurs
         e.printStackTrace();
      }
   }
}
