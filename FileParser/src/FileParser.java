import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


public class FileParser {

		public static void main(String[] args) {
		System.out.println("Enter the file name: ");
		Scanner sc=new Scanner(System.in);
		String filename=sc.next();
		Stack s=new Stack();
		System.out.println(s.size());
		try {
			System.out.println("the type of file is "+ getFileType(filename));
			getClassNames(filename);
			
			if(getFileType(filename).equals("JAVA")){
				getJavaMethodNames(filename);
				getJavaPropertyNames(filename);
				getJavaIndentation(filename);
			}
			else 
				if(getFileType(filename).equals("PHP")){
				getPHPMethodNames(filename);
				getPHPPropertyNames(filename);
				getJavaIndentation(filename);
				}
			else 
				if(getFileType(filename).equals("RUBY")){
				getRubyMethodNames(filename);	
				getRubyPropertyNames(filename);
				}
			
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			sc.close();
		}
	}

		public static String getFileType(String filename) throws IOException{
			String line;
			BufferedReader bufferedreader=null;
			FileReader fileReader = new FileReader(filename);
			bufferedreader = new BufferedReader(fileReader);
			line = bufferedreader.readLine().trim();
			
			if(line.startsWith("<?php")){
				filename = "PHP";
			}
			
			if(line.startsWith("import")||((line.startsWith("public") || line.startsWith("class")) && line.endsWith("{"))){
				filename = "JAVA";
			}
			
			if(line.startsWith("class") && !line.endsWith("{")){	
				filename = "RUBY";
			}
			bufferedreader.close();

		
		return filename;
		
		
		}
		
		
		public static void getClassNames(String filename) throws IOException {
			String line;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			System.out.println("The classes in the file are: ");
			while((line = bufferedreader.readLine())!=null){
			line=line.trim();
			if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
			String str[]=line.split(" ");
			int length=str.length;
			int i=0;
			while(i<length){
				if(str[i].equals("class")){
					System.out.println(str[i+1]);
					}
				i++;
			}
			}
			}
			bufferedreader.close();
		}
		
		
		public static void getJavaMethodNames(String filename) throws IOException{
			String line;
			Stack<String> methodnames=new Stack<String>();
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			while((line = bufferedreader.readLine())!=null){
				line=line.trim();
				if(line.endsWith("{") && line.length()>1){
					String sline[]=line.split(" ");
					int len=sline.length;
					int i=0;
					if(sline[len-2].endsWith("()")){
						while(i<len-2){
							methodnames.push(sline[i]);
							i++;
						}methodnames.push(sline[len-2].substring(0, sline[len-2].indexOf("(")));
						
					}
					else if(sline[len-2].endsWith(")") && sline[len-3].contains("(") && !((sline[len-3].startsWith("if("))||(sline[len-3].startsWith("for("))||(sline[len-3].startsWith("while(")))){
						while(i<len-3){
							methodnames.push(sline[i]);
							i++;
					}
						methodnames.push(sline[len-3].substring(0, sline[len-3].indexOf("(")));
						
					}
					
					int count=1;
					//System.out.println(methodnames.size());
					while(methodnames.size()>0){
					if(count==1){
						System.out.println("Method Name is " + methodnames.pop());	
					} 
					else if(count==2){
						System.out.println("return type is : " + methodnames.pop());	
						}
					else {
					System.out.println("access specifier is: " + methodnames.pop());	
					}
					count++;
					}
				}
				
			}
			bufferedreader.close();
			
		}
		
		
		public static void getPHPMethodNames(String filename) throws IOException{
			String line;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			while((line = bufferedreader.readLine())!=null){
					line=line.trim();
					if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
					String sline[]=line.split(" ");
					int len=sline.length;
					if(sline[0].equals("function")) {
						System.out.println("Function name is: " + sline[1].substring(0, sline[1].indexOf("(")));
					}
					if( len>1 && sline[1].equals("function")) {
						System.out.println("Function name is: " + sline[2].substring(0, sline[2].indexOf("(")));
					}
					}
			}
			bufferedreader.close();
			
		}
		
		public static void getRubyMethodNames(String filename) throws IOException{
			String line;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			while((line = bufferedreader.readLine())!=null){
					line=line.trim();
					if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
						
					if(line.startsWith("def")){
						if(line.contains("(")){
						System.out.println(line.substring(4, line.indexOf("("))+" is a method");	
						} 
						else
							{
							System.out.println(line.substring(4, line.length())+" is a method");	
							}
					}
				}
		}
			bufferedreader.close();
	}

		public static void getJavaPropertyNames(String filename) throws IOException{
			String line;
			int i=0;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			System.out.println("properties for this file are: ");
			while((line = bufferedreader.readLine())!=null){
				line=line.trim();
				if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
						if(line.endsWith("{")){
							i++;
						}
						if(line.endsWith("}")){
							i--;
						}
						if(i==1){
							if(line.endsWith(";")){
								if(!(line.contains("="))){
							System.out.println(line.substring(line.lastIndexOf(" "), line.indexOf(";")));	
							}
								else{
									String str[]=line.split("=");
									System.out.println(str[0].substring(str[0].lastIndexOf(" "), str[0].length()));
									}
						}
				}
			}
			
		}
			bufferedreader.close();
	}
		


		public static void getPHPPropertyNames(String filename) throws IOException{
			String line;
			int i=0;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			System.out.println("properties for this file are: ");
			while((line = bufferedreader.readLine())!=null){
				line=line.trim();
				if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
					if(line.startsWith("var")&&line.contains("=")){
						System.out.println(line.substring(line.indexOf("$")+1, line.indexOf("=")));
					}
					if(line.startsWith("var")&&!(line.contains("="))){
						System.out.println(line.substring(line.indexOf("$")+1, line.indexOf(";")));
					}
					
					if(line.contains("{")){
						++i;
					}
					if(line.contains("}")){
						--i;
					}
					if(i==1){
						if(line.endsWith(";")){
							if(!(line.contains("="))){
							String name=line.substring(line.indexOf("$")+1, line.indexOf(";"));
							System.out.println(name);
							}
							else
							{
								String name=line.substring(line.indexOf("$")+1, line.indexOf("="));
								System.out.println(name);
							}
						}
						
					}
				}
		}
			bufferedreader.close();
	}


		public static void getRubyPropertyNames(String filename) throws IOException{
			String line;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			System.out.println("properties for this file are: ");
			while((line = bufferedreader.readLine())!=null){
				line=line.trim();
				if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
				if(line.startsWith("@@")){
					System.out.println(line.substring(line.lastIndexOf("@")+1, line.indexOf("=")));
				}
			}
				
		}
			bufferedreader.close();
	}

		public static void getJavaIndentation(String filename) throws IOException{
			String line;
			Stack stack=new Stack();
			Stack stack2=new Stack();
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			while((line = bufferedreader.readLine())!=null){
				line=line.trim();
				if(!(line.startsWith("//")||line.startsWith("//**")||line.startsWith("/*")||line.startsWith("#")||line.startsWith("*"))){
					
				if(line.contains("{") && stack2.size()==0){
					stack.push("{");
				}
				
				if(line.contains("(")){
					stack2.push("(");
				}
				
				if(line.contains(")")){
					stack2.pop();
				}
				
				if(line.contains("}") && !stack.isEmpty()){
					stack.pop();
				}
			}
		}
			if(stack.size()==0){
				System.out.println("the file is properly indentated");
			}
			else{
				System.out.println("Check the indentation of the file");
				}
		}
		
}
