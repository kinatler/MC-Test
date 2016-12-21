package Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static java.nio.file.StandardCopyOption.*;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.util.StringUtils;


public class FileHelper {
	
	public void BuildXML ( String path , String arquivo , String alvo, String alvo2, String resultado, String resultado2) {
		//System.out.println("Using Build XML");

		try {
			copyFile( new File(path+"build.xml"), new File(path+"BuildInUse.xml"));
			trocar(path+arquivo, alvo, resultado);
			trocar(path+arquivo, alvo2, resultado2);
			
			
			String path2 = path + "BuildInUse.xml";

			
			File buildFile = new File(path2);			    
			Project p = new Project();
			p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			//System.out.println("Build DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to build ant script to execute coverage test");
				System.exit(0);
			}
			
		}
	
	public static void copyAllJavaTestXML ( String path , String arquivo , String alvo, String resultado) {
		//System.out.println("Using Build XML");

		try {
			
			deleteAllJavaInUseTestXML(path);
			
			copyFile( new File(path+"allJavaCopy.xml"), new File(path+"allJavaCopyInUse.xml"));
			trocar(path+arquivo, alvo, resultado);
			
			String path2 = path + "allJavaCopyInUse.xml";
			File buildFile = new File(path2);			    
			Project p = new Project();
			p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			//System.out.println("Build DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to build ant script to copy tests from ALLJava to MUJAVA");
				System.exit(0);
			}
			
		}
	
	public static void deleteAllJavaInUseTestXML ( String path) {
		//System.out.println("Using Build XML");

		try {
			
			String path2 = path + "DeleteAllJavaCopyInUse.xml";
			File buildFile = new File(path2);			    
			Project p = new Project();
			p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			//System.out.println("Build DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to delete ant script to copy tests from ALLJava to MUJAVA");
				System.exit(0);
			}
			
		}
	
	public static void BuildXMLMUJAVA ( String path , String arquivo, String arquivo2 , String alvo, String resultado) {
		//System.out.println("Using Build XML for mujava");
		
		try {
						
			
			String path2 = path + "mujavaConfigCreation.xml";

			
			File buildFile = new File(path2);			    
			Project p = new Project();
			p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(p.getDefaultTarget());
			//System.out.println("Build DONE!");
			
			
			
			trocar(arquivo, alvo, resultado);
			trocar(arquivo2, alvo, resultado);
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to build ant script to create Mujava instrumentation folder");
				System.exit(0);
			}
			
		}
	
	
	public void BuildXMLDelete ( String path) {
		//System.out.println("Using DeleteXML");
		try {	
			String path2 = path + "buildDelete.xml";

			File buildFile = new File(path2);
			    
			   Project p = new Project();
			   p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			   p.init();
			   ProjectHelper helper = ProjectHelper.getProjectHelper();
			   p.addReference("ant.projectHelper", helper);
			   helper.parse(p, buildFile);
			   p.executeTarget(p.getDefaultTarget());
			   //System.out.println("Delete Build DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to delete copy of ant build to delete folders");
				System.exit(0);
			}
			
		}
	
	public static void BuildXMLMUJAVACOMPILER ( String path) {
		//System.out.println("Using MUJAVACOMPILER");
		try {	
			String path2 = path + "mujavaCompileScript.xml";

			File buildFile = new File(path2);
			    
			   Project p = new Project();
			   p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			   p.init();
			   ProjectHelper helper = ProjectHelper.getProjectHelper();
			   p.addReference("ant.projectHelper", helper);
			   helper.parse(p, buildFile);
			   p.executeTarget(p.getDefaultTarget());
			   //System.out.println("MUJAVACOMPILER DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to compile");
				System.exit(0);
			}
			
		}
	
	public void BuildCopyAllXML ( String path  ,String resultado) {
		//System.out.println("Using Build XML");
		
		resultado = resultado.replace( '\\', '/' );

		try {
			copyFile( new File(path+"buildCopyAll.xml"), new File(path+"buildCopyAllInUse.xml"));
			trocar(path+"buildCopyAllInUse.xml", "PaThToInClUdE", resultado);			
			
			String path2 = path + "buildCopyAllInUse.xml";

			
			File buildFile = new File(path2);			    
			   Project p = new Project();
			   p.setUserProperty("ant.jar", buildFile.getAbsolutePath());
			   p.init();
			   ProjectHelper helper = ProjectHelper.getProjectHelper();
			   p.addReference("ant.projectHelper", helper);
			   helper.parse(p, buildFile);
			   p.executeTarget(p.getDefaultTarget());
			   //System.out.println("Build DONE!");
			} catch ( Exception e ) {
				System.out.println("ERROR : failed to build ant script to copy all .java");
				System.exit(0);
			}
			
		}
	
	
	public static void trocar ( String caminho, String alvo, String resultado ) {
	
		try {
			
			Path path = Paths.get(caminho);
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll(alvo, resultado);
			Files.write(path, content.getBytes(charset));
			
		} catch ( Exception e ) {
			System.out.println("Error : Failed to change string in xml");
			System.exit(0);
		}
		
	}
	
	public static void copyFile(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
	
	public void delete(String source) throws IOException {
		File del = new File(source);
		del.delete();
	}
	
	public void copyFilesToProject(ArrayList<String> testList) throws IOException {
		String projectPath = getProjectPath();
		String newfile= "";

		
		for (int i = 0; i < testList.size(); i++) {
			
			newfile=Paths.get(testList.get(i)).getFileName().toString().substring(0, Paths.get(testList.get(i)).getFileName().toString().length() -5);

			Files.copy(Paths.get(testList.get(i)), Paths.get(projectPath+"temp"+ File.separator + "test"+ File.separator + newfile + ".java"), REPLACE_EXISTING);
		}

	}
	
	public void copyClassesFromAllJavaToClasses(){
		String projectPath = getProjectPath();
		String newfile= "";
		String className = "";
		String testName = "";
		int count = 0;

		File testfolder = new File(projectPath+"temp"+ File.separator + "test"+ File.separator);
		File[] listOfTests = testfolder.listFiles();
		File allJava = new File(projectPath+"AllJava");
		File[] listOfClasses = allJava.listFiles();


		    for (int i = 0; i < listOfClasses.length; i++) {
		    	count = 0;
		    	 for (int j = 0; j < listOfTests.length; j++) {
					 className=Paths.get(listOfClasses[i].getPath()).getFileName().toString().substring(0, Paths.get(listOfClasses[i].getPath()).getFileName().toString().length() -5);
					 testName=Paths.get(listOfTests[j].getPath()).getFileName().toString().substring(0, Paths.get(listOfTests[j].getPath()).getFileName().toString().length() -5);
					 if(className.equals(testName)){
		    			 count ++;
		    		 }
				    }
			      if(count == 0){
			    	  			    	  
			    	  try{
						  Files.copy(Paths.get(projectPath+"AllJava"+ File.separator + className + ".java") , Paths.get(projectPath+"temp"+ File.separator + "classes"+ File.separator + className + ".java"), REPLACE_EXISTING);
			    	  }catch(Exception ex){
			    		  System.out.println("ERROR : failed to copy files from JavaAll to CLasses");
			    	  }
			      }
		    }
		    

	}

	public ArrayList<String> readCSV(String source , ArrayList<String> target ) throws IOException {
		ArrayList<ArrayList<String>> resultList = new ArrayList();
		ArrayList<String> result = new ArrayList();
		
		String csvFile = source + "report.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int count = 0;
        int resultCount = 0;

        
        
        try {

            br = new BufferedReader(new FileReader(csvFile));
            
            while ((line = br.readLine()) != null) {
            	


                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
                count = 0;
                
                if(country[2].equals("CLASS")){
                	count = 1;
                }
                else{
                for(String t : target){
                    if (country[2].equals(t)){
                    	count ++;
                    }
                }
                if (count == 0){
                    
	                result.add(country[2]);
	                result.add(country[3]);
	                result.add(country[4]);
	                result.add(country[5]);
	                result.add(country[6]);
	                result.add(country[7]);
	                result.add(country[8]);
	                result.add(country[9]);
	                result.add(country[10]);
	                result.add(country[11]);
	                result.add(country[12]);
                }
              }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		
		return result;
	}
	
	public static String getProjectPath(){
		String result = "";
		
		try{
			File currDir = new File(".");
			result = currDir.getAbsolutePath();
			result = result.substring(0, result.length()-1);	
		}catch( Exception e ) {
			System.out.println("ERROR : Failed to get Project Path");
			System.exit(0);
		}
		
		return result;
	}
	
	public static String changeSlashes(String entry){
		StringBuilder toReturn = new StringBuilder(entry);
		String t2 = "";
		int found = 0;
		String key = "";
		char target = '*';
		
		if (File.separator.equals("\\")){
			key = "\\\\";
			target = '\\'; 
		}
		else if(File.separator.equals("//")){
			key = "////";
			target = '/'; 
		}
		
		for(int x = 0 ; x < entry.length() ; x++){

			if (entry.charAt(x) == target){
				t2 = toReturn.substring(0, x+found) + key + toReturn.substring(x+1+found, toReturn.length());
				toReturn.delete(0, toReturn.length());
				toReturn.append(t2);
				found++;
			}
		}
		
		return toReturn.toString();
	}
	
	public static String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			System.out.println("Error: failed to execute command");
			System.exit(0);
		}

		return output.toString();

	}
	
	public static void copyFolder(String src , String dest){
		File source = new File(src);
		File destiny = new File(dest);
		try {
		    FileUtils.copyDirectory(source, destiny);
		} catch (IOException e) {
		    e.printStackTrace();
		}	
	}
	
	public static void copyFromAlljavaToMujava(ArrayList<String> testClasses,ArrayList<String> classes){
		File src = new File("");
		File dest = new File("");
		
		String path = getProjectPath();
		String srcPath = path + "AllJava" + File.separator;
		String destTestPath = path + "MuJavaInstrumentation" + File.separator + "session1" + File.separator + "testset" + File.separator;
		String destClassPath = path + "MuJavaInstrumentation" + File.separator + "session1" + File.separator + "classes" + File.separator;

		
		for(String t : testClasses){
			src = new File(srcPath + t + ".class");
			dest = new File(destTestPath + t + ".class");
			
			try {
				copyAllJavaTestXML(path, "allJavaCopyInUse.xml", "INSERTHERENAMEOFFILE123456789", t+".class");
				
				//FileUtils.copyFile(src, dest);
			} catch (Exception e) {
				System.out.println("ERROR: Failed to copy "+ t +".class from AllJava to Mu Java instrumentation");
				System.exit(0); 
			}	
		}
		
//		for(String t : classes){
//			src = new File(srcPath + t + ".class");
//			dest = new File(destClassPath + t + ".class");
//			
//			System.out.println("---------------------");
//			System.out.println(src);
//			System.out.println(dest);
//			
//			try {
//			    FileUtils.copyFile(src, dest);
//			} catch (Exception e) {
//				System.out.println("ERROR: Failed to copy CLASSES "+ t +".class from AllJava to Mu Java instrumentation");
//				System.exit(0);
//			}	
//		}
		
		
	}
	
	public static ArrayList<String> getClasses(){		
		ArrayList<String> classes = new ArrayList<String>();
		String path = getProjectPath() + "temp" + File.separator + "classes" ;
		String temp;
		
		File[] files = new File(path).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	//temp = file.getName();
		    	temp = file.getName().replaceAll("\\..*","");
		    	classes.add(temp);
		    }
		}
		
		return classes;
	}
	
	
	public static String[][] getQuantityOfMutes(String src , int classQuantity){		
		String[] set = src.split("\n");
		
		String[][] matrix = new String[classQuantity][2];
		
		String[] temp;
		
		int count = 0;
		for(int x = 0; x< set.length ; x++){
			if(set[x].contains("Total mutants gnerated for")){
				temp = set[x].split("Total mutants gnerated for");
				temp = temp[1].split(": ");
				matrix[count][0] = temp[0].trim();
				matrix[count][1] = temp[1].trim();
				count++;
			}
			
		}
		
	    //System.out.println(src.substring(src.lastIndexOf("/") + 1));
		
		return matrix;
	}
	
	public static ArrayList<ArrayList<String>> ConvertString(String entry){		
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> generation = new ArrayList<String>();
		ArrayList<ArrayList<String>> running = new ArrayList<ArrayList<String>>();
		
		
		String[] tempString = entry.split("GetPart123456789\n");

		
		for ( String s : tempString){
			temp.add(s);
		}
		
		tempString = temp.get(0).split("Total mutants gnerated for");
		
		//convert every generation information
		String[] temp2 = null;
		for(int x=1 ; x < tempString.length ; x= x + 2){
			temp2 = tempString[x].split(":");
		}

		
		for(int x=0 ; x < temp2.length ; x++){
			temp2[x] = temp2[x].trim();
		}
		
		for(int x=0 ; x < temp2.length ; x=x+2){
			temp2[x] = temp2[x].replaceAll("\\..*","");
		}
				
		for ( String s : temp2){
			generation.add(s);
		}
		
		//System.out.println(generation.toString());

		
		//convert running information
		tempString = entry.split("GetPart123456789\n");

		//System.out.println(tempString[0]);
		
//		for ( String s : tempString){
//			System.out.println(s);
//		}
		
		//System.out.println(tempString[3]);
		ArrayList<String> temp3 = new ArrayList<String>();

		for(int x=1 ; x < tempString.length ; x++){
			//System.out.println(tempString[x]);
			//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
			
			//temp2[x-1] = tempString[x];
			temp3.add(tempString[x]);
		}
		
		ArrayList<String> killed = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();
		ArrayList<String> score = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		
		running.add(generation);
		for ( String s : temp3){
			name = getValue(s,"Test Name:");
			killed = getValue(s,"Total mutants killed:");
			score = getValue(s,"Mutation Score:");
			total = getValue(s,"Total mutants:");
			running.add(name);
			running.add(killed);
			running.add(total);
			running.add(score);
			
//			for ( String y : name){
//				System.out.println(y);
//			}
		}

		//System.out.println(running.toString());

		return running;
	}
	
	private static ArrayList<String> getValue(String text,String key){
		String[] temp = null;
		String[] temp2 =null;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> toReturn = new ArrayList<String>();
		
		temp = text.split("\n");
		
		for ( String s : temp){
			list.add(s);
		}
		
		for ( String s : list){
			if(s.contains(key)){
			toReturn.add(s.split(key)[1].trim());
			}
		}
		
		
		return toReturn;
	}
	
	
}
