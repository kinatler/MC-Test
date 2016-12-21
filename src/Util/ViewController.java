package Util;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import views.MainView;
import views.ResultView;
import views.Loading;


public class ViewController {
	static ArrayList<String> TestList = new ArrayList();
	static String codePath = "";
	public int testQuantity = 0;
	public ArrayList<String> classList = new ArrayList();
	public String operators = "";


	public void callView1 (Loading view3){
		MainView view1 = new MainView();
		FileHelper fileController = new FileHelper();
		boolean waitView1 = false;
		boolean getExitStatus = false;
		
		while ( !waitView1 ) {
						
			if ( view1.testAdded ) {
				TestList.add(view1.path);
				view1.testAdded = false;
				view1.testQuantity ++;
			}
			
			if ( view1.codeAdded ) {
				codePath = view1.path;
				view1.codeAdded = false;
				view1.codeQuantity ++;
			}
			
			if ( view1.codeCleanFlag ) {
				codePath = "";
				view1.codeCleanFlag = false;
				view1.codeQuantity = 0;
			}
			
			if ( view1.testCleanFlag ) {
				TestList.clear();
				view1.testCleanFlag = false;
				view1.codeQuantity = 0;
			}
			getExitStatus = view1.exit;
			//System.out.println(getExitStatus);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//just to wait
			}
			
			if ( getExitStatus ) {
				waitView1 = true;
			}
		}
		
		view3.frame.setVisible(true);
		view3.setText("Creating files to start Coverage Test");

		String path = fileController.getProjectPath();
		
		String alvo = "testNameToBeUsedInACoverageTest";
		String alvo2 = "folderNameToBeUsedInACoverageTest";
		String arquivo = "BuildInUse.xml";
		
		operators = view1.operators;
		
		testQuantity = view1.testQuantity;
				
		for (int x = 0 ; x < view1.testQuantity ; x++){

			String resultado = Paths.get(TestList.get(x)).getFileName().toString().substring(0, Paths.get(TestList.get(x)).getFileName().toString().length() -5);
			String resultado2 = "Test" + x;
			classList.add(resultado);
			view3.setText("Testing coverage of " + resultado);

			
			fileController.BuildCopyAllXML(path, view1.folderPath);
			fileController.copyClassesFromAllJavaToClasses();
			
			fileController.BuildXML (path , arquivo , alvo, alvo2, resultado, resultado2);
			
			
			try{			
				fileController.delete(path+arquivo);
				fileController.delete(path+"buildCopyAllInUse.xml");
			}catch(Exception e){
				System.out.println("Failed to delete file: " + path+arquivo);
			}
		
		
		}
		
		view3.setText("Coverage test complete !!!");

	}
	
	public void callView2 (Loading view3){
		String mutationTestReturn= "";
		
		
		if(!operators.equals("")){
			mutationTestReturn = Util.FlowUtils.execMujava(Util.FileHelper.getClasses().size(),classList,Util.FileHelper.getClasses(),operators,view3);
		}
		
		view3.frame.setVisible(false);
		view3.frame.dispose();
		
		System.out.println(mutationTestReturn);
		
		
		ResultView view2 = new ResultView(testQuantity, classList, FileHelper.ConvertString(mutationTestReturn));
	}
	
	
	public Loading callLoading (){		
		Loading view3 = new Loading();
		
		return view3;
	}
	
}
