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

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import views.Loading;
import Util.FileHelper;


public class FlowUtils {
	
	public static String execMujava(int classQuantity , ArrayList<String> testClasses, ArrayList<String> classes,String operators,Loading view3){
		String toReturn = "";

		    String path = Util.FileHelper.getProjectPath();
			String fileConfig = path + File.separator + "lib" + File.separator + "MuScript" + File.separator + "mujavaCLI.config";
			String fileConfig2 = path + File.separator + "lib" + File.separator + "MuScript" + File.separator + "mujava.config";
	
			String instrumentationfolder = path + "MujavaInstrumentation";
			String Command = "teste";
			//tools.jar
			String classpath1 = System.getenv("JAVA_HOME") + File.separator + "lib" + File.separator + "tools.jar";
			//mujava
			String classpath2 = path + "lib" + File.separator + "MuScript" + File.separator + "mujava.jar";
			//openjava
			String classpath3 = path + "lib" + File.separator + "MuScript" + File.separator + "openjava.jar";
			//junit
			String classpath4 = path + "lib" + File.separator  + "junit.jar";
			//hamcrest
			String classpath5 = path + "lib" + File.separator + "org.hamcrest.core_1.3.0.v201303031735.jar";
			//commons-io-2.4
			String classpath6 = path + "lib" + File.separator + "MuScript" + File.separator + "commons-io-2.4.jar";
			String pathMuScript = path + "lib" + File.separator + "MuScript" + File.separator;
			
			
			classpath1 = Util.FileHelper.changeSlashes(classpath1);
			classpath2 = Util.FileHelper.changeSlashes(classpath2);
			classpath3 = Util.FileHelper.changeSlashes(classpath3);
			classpath4 = Util.FileHelper.changeSlashes(classpath4);
			classpath5 = Util.FileHelper.changeSlashes(classpath5);
			classpath6 = Util.FileHelper.changeSlashes(classpath6);
			pathMuScript = Util.FileHelper.changeSlashes(pathMuScript);
	
	
			instrumentationfolder = Util.FileHelper.changeSlashes(instrumentationfolder);
			
			
			view3.setText("Creating Instrumentation folder for Mujava");
			Util.FileHelper.BuildXMLMUJAVA(path,fileConfig,fileConfig2,"MUJAVALOCAL", instrumentationfolder);		
			String command1 = "cd " + pathMuScript;
			String command2 = "set CLASSPATH=%CLASSPATH%;.;"+classpath1+";"+classpath2+";"+classpath3+";"+classpath4+";"+classpath5+";"+classpath6;
			String command3 = "java mujava.cli.testnew -debug session1";
			
			
			String command = "cmd /c " + command1 + " && " + command2 + " && " + command3;
			
			
			String resp = Util.FileHelper.executeCommand(command);
			//System.out.println(resp);
					
			
			Util.FileHelper.copyFolder(path + File.separator + "temp" + File.separator + "classes" , path + File.separator + "MuJavaInstrumentation" + File.separator + "session1" + File.separator + "src");
			//Util.FileHelper.copyFolder(path + File.separator + "temp" + File.separator + "test" , path + File.separator + "MuJavaInstrumentation" + File.separator + "session1" + File.separator + "testset");
			Util.FileHelper.BuildXMLMUJAVACOMPILER(path);
	
			
			view3.setText("Generating Mutants\n It can take several minutes \n In case of freezing: Check if you choose a test class \n as a class to be tested");
			view3.setText2("Operators: " + operators);


	
			
			//************************ colocar operadores aqui
			//************************************************
			String command4 = "java mujava.cli.genmutes "+ operators +" session1";
			
			
			
			command = "cmd /c " + command1 + " && " + command2 + " && " + command4;
			
			resp = "Mutation Report Generation \n";
			resp = resp + Util.FileHelper.executeCommand(command);
			
			Util.FileHelper.copyFromAlljavaToMujava(testClasses,classes);
			
			
			//************************ colocar operadores aqui
			//************************************************
			// Colocar num for para cada test
			// resposta tem que ser transformada e ser colocada em um array
			// ler csv para tela
			// mostrar na tela resultado
			// colocar operadores na tela 1
			// mudar tela 2
			
			view3.setText("Running all Mutants with all the tests");

			
			for (String s : testClasses) {
				view3.setText("Running all Mutants with all the tests\nRunning now with test: "+ s);
				view3.setText2("Operators: " + operators);

				String command5 = "java mujava.cli.runmutes "+ s + " session1"; 
				command = "cmd /c " + command1 + " && " + command2 + " && " + command5;
				//System.out.println(command);

				resp = resp + "GetPart123456789\nMutation Report RunMutants \n";
				resp = resp + Util.FileHelper.executeCommand(command);
			}	
			toReturn = resp;

		
		return toReturn;
	}
	
}
