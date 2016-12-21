import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import Util.FileHelper;
import Util.ViewController;
import views.MainView;
import views.Loading;


public class MCTest {
	
	static ArrayList<String> testList = new ArrayList();
	static String codePath = "";

	
	
	
	//FAZER : verificar a movida para a pasta TESTE e deletar a pasta TESTE
	// fazer tela resposta.
	
	public static void main(String[] args) {
		FileHelper fileController = new FileHelper();
		ArrayList<String> coverageResult = new ArrayList();
		ViewController view1 = new ViewController();	
		fileController.BuildXMLDelete(fileController.getProjectPath());
		
		
		
		Loading view3 = new Loading();		
		view3 = view1.callLoading();
		view1.callView1(view3);
		view1.callView2(view3);
	}
	
}
