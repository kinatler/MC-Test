package views;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Util.FileHelper;
import Util.ViewController;

import javax.swing.JTextPane;

 
public class ResultView extends JPanel {
	
	
    public void CreateTab(int testQuantity , ArrayList<String> classList, ArrayList<ArrayList<String>> mutationInfo) { 
    	    	
    	//System.out.println("Preparing to open Result View!!!!!");
    	ArrayList<JComponent> tabs = new ArrayList();
		ArrayList<String> coverageResult = new ArrayList();
		ArrayList<String> resultToShow = new ArrayList();
		ArrayList<String> classes = new ArrayList();
		FileHelper fileController = new FileHelper();
		String stringToShow = "";
		float coveragePercentage = 0;
		float temp = 0;
		float temp2 = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormat df2 = new DecimalFormat("0.0000");


		
		//create frame with tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        
        //Create String to show in each tab
        // move this to fileUTIL
		
		//System.out.println(mutationInfo.toString());

        
    	for(int x = 0; x < testQuantity; x++){	
			try{
				coverageResult = fileController.readCSV(fileController.getProjectPath() + "report" + File.separator +"Test" + x + File.separator , classList);
				stringToShow = "";
				for(int i = 0 ; i < coverageResult.size() ; i = i + 11){
					
					temp = Integer.parseInt(coverageResult.get(i + 2));
					temp2 = Integer.parseInt(coverageResult.get(i + 1));
					coveragePercentage = 100 * temp / (temp2 + temp);					
					
					
					stringToShow = stringToShow + ""
							+ "Class Name:                           " + coverageResult.get(i).trim() +"\n"
							+ "Coverage Percentage:           " + df.format(coveragePercentage).trim() +"%\n"
							+ "Instruction missed:                 " + coverageResult.get(i + 1).trim() +"\n"
							+ "Instruction Coverage:              " + coverageResult.get(i + 2).trim() +"\n"
							+ "Branch Missed:                        " + coverageResult.get(i + 3).trim() +"\n"
							+ "Branch Covered:                       " + coverageResult.get(i + 4).trim() +"\n"
							+ "Line Missed:                             " + coverageResult.get(i + 5).trim() +"\n"
							+ "Line Covered:                           " + coverageResult.get(x + 6).trim() +"\n"
							+ "Complexity Missed:                  " + coverageResult.get(i + 7).trim() +"\n"
							+ "Complexity Covered:                " + coverageResult.get(i + 8).trim() +"\n"
							+ "Method Missed:                         " + coverageResult.get(i + 9).trim() +"\n"
							+ "Method Covered:                       " + coverageResult.get(i + 10).trim()+"\n"
							+ "------------------------------------------------------" +"\n";
				}
				resultToShow.add(stringToShow);
				
				
				}catch(Exception e){
					System.out.println("Reading CSV failed");
				}	
		}
		
		ArrayList<String> resultToShow2 = new ArrayList();
		ArrayList<String> arrayTemp = new ArrayList();
		int mutantKilled = 0;
		int mutantTotal = 0;
    	for(int x = 0; x < testQuantity; x++){
    		mutantKilled = 0;
    		mutantTotal = 0;
    		stringToShow = "";
    		arrayTemp = mutationInfo.get(1+(x*4));
    		
			stringToShow = stringToShow + "Test Name:		" + arrayTemp.get(0) +"\n";
			stringToShow = stringToShow + "Class Name:                        " + mutationInfo.get(0).get(0) +"\n";
			stringToShow = stringToShow + "Number of Mutantes:               " + mutationInfo.get(0).get(1) +"\n";
			for(int i = 0; i < mutationInfo.get(2+(x*4)).size();i++){
				mutantKilled = mutantKilled + Integer.parseInt(mutationInfo.get(2+(x*4)).get(i));
				mutantTotal = mutantTotal + Integer.parseInt(mutationInfo.get(3+(x*4)).get(i));
			}
			stringToShow = stringToShow + "Mutants Killed:	"+ mutantKilled +" of "+ mutantTotal +"\n";
			for(int i = 0; i < mutationInfo.get(2+(x*4)).size();i++){
				stringToShow = stringToShow + "Method "+ (i+1) + ":	                           " + mutationInfo.get(2+(x*4)).get(i) +" of "+ mutationInfo.get(3+(x*4)).get(i) +"\n";
			}
			stringToShow = stringToShow + "Mutantion Score : \n";
			
			double average = 0;
			
			for(int i = 0; i < mutationInfo.get(2+(x*4)).size();i++){
				stringToShow = stringToShow + "Method "+ (i+1) + ":	                           "+ mutationInfo.get(4+(x*4)).get(i) +"\n";
				
				//System.out.println(mutationInfo.get(4+(x*4)).get(i).replace(',', '.'));
				try{
					average = average + Double.parseDouble(mutationInfo.get(4+(x*4)).get(i).replace(',', '.'));
				}
				catch(Exception e){
					System.out.println("ERROR: Average method did not work");
				}
			}
			average = average/mutationInfo.get(2+(x*4)).size();
			
			stringToShow = stringToShow + "Mutantion Score Average: \n";
			stringToShow = stringToShow + "Score Average:                       "+ df2.format(average) +"\n";
			resultToShow2.add(stringToShow);
		}

    	
	
    	//Show coverage test to all tests at the same time
//    	tabs.add(makeTextPanel("SOON"));
//    	tabs.get(0);
//    	tabbedPane.addTab("Test ALL", tabs.get(0));
//    	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    	
    	//show each coverage test result
        for (int x = 0 ; x < testQuantity ; x++){
        	//tabs.add(makeTextPanel(classes.get(x-1)));
        	tabs.add(makeTextPanel(classList.get(x)));

        	
    		JTextPane textpanel = new JTextPane();
    		textpanel.setLayout(new BorderLayout());
    		textpanel.setText(resultToShow.get(x));
    		textpanel.setEditable(false);
    		
    		//JScrollPane  scroll = new JScrollPane(textpanel);
    		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    		//textpanel.add(scroll);
    		
    		
    		
    		tabs.get(x).add(textpanel);
        	tabbedPane.addTab("Test " + x, tabs.get(x));
        	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        }
        
        for (int x = 0 ; x < testQuantity ; x++){
        	//tabs.add(makeTextPanel(classes.get(x-1)));
        	tabs.add(makeTextPanel(classList.get(x) + " Mut"));

        	
    		JTextPane textpanel = new JTextPane();
    		textpanel.setLayout(new BorderLayout());
    		textpanel.setText(resultToShow2.get(x));
    		textpanel.setEditable(false);
    		
    		//JScrollPane  scroll = new JScrollPane(textpanel);
    		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    		//textpanel.add(scroll);
    		
    		
    		
    		tabs.get(x).add(textpanel);
        	tabbedPane.addTab("Test " + x, tabs.get(x));
        	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        }
        
        
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
     
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
     
    public ResultView(int testQuantity , ArrayList<String> classList, ArrayList<ArrayList<String>> mutationInfo) {
    	super(new GridLayout(1, 1));
        //Create and set up the window.
        JFrame frame = new JFrame("ResultView");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));    	

        //Add content to the window.
    	frame.getContentPane().add(this, BorderLayout.CENTER);
    	CreateTab(testQuantity,classList, mutationInfo);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(int testQuantity , ArrayList<String> classList) {
    	//do nothing
    }
}
