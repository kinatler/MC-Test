package views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import Util.FileHelper;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
public class MainView extends JFrame implements ActionListener {
private JLabel addCode;
private JLabel addTest;
private JButton AddCodeButton;
private JLabel lblException;
private JButton testButton;
public boolean exit = false;
public boolean codeAdded = false;
public boolean testAdded = false;
public int testQuantity = 0;
public String path = "";
public int codeQuantity = 0;
private JLabel result1;
DefaultListModel  model;
JList<String> list;
JButton cleanCodeButton;
JButton cleanTestButton;
public boolean codeCleanFlag = false;
public boolean testCleanFlag = false;
public String codePath = "";
public ArrayList<String> testsPath = new ArrayList();
public String folderPath = "";
public JCheckBox chckbxNewCheckBox = new JCheckBox("AORB");        
public JCheckBox chckbxNewCheckBox_1 = new JCheckBox("AORS");
public JCheckBox chckbxNewCheckBox_2 = new JCheckBox("AOIU");
public JCheckBox chckbxNewCheckBox_3 = new JCheckBox("AOIS");
public JCheckBox chckbxNewCheckBox_4 = new JCheckBox("AODU");
public JCheckBox chckbxNewCheckBox_5 = new JCheckBox("AODS");
public JCheckBox chckbxNewCheckBox_6 = new JCheckBox("COD");
public JCheckBox chckbxNewCheckBox_7 = new JCheckBox("COI");
public JCheckBox chckbxNewCheckBox_8 = new JCheckBox("ROR");
public JCheckBox chckbxNewCheckBox_9 = new JCheckBox("COR");
public JCheckBox chckbxNewCheckBox_10 = new JCheckBox("SOR");
public JCheckBox chckbxNewCheckBox_11 = new JCheckBox("LOR");
public JCheckBox chckbxNewCheckBox_12 = new JCheckBox("LOI");      
public JCheckBox chckbxNewCheckBox_13 = new JCheckBox("LOD");    
public JCheckBox chckbxNewCheckBox_14 = new JCheckBox("ASRS");
public String operators = "";
JCheckBox chckbxAllOperators = new JCheckBox("All Operators");


    
    public MainView()
    {
        super("MC-Test");
    	//System.out.println("Preparing to open Main View!!!!!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        
		
        testButton = new JButton("Start Test");   
        testButton.setActionCommand("startTest");
        testButton.addActionListener(this);
        
        JButton addTestButton = new JButton("Add");
        addTestButton.setActionCommand("getTests");
        addTestButton.addActionListener(this);
        addCode = new JLabel("Add Code Folder");        
        addTest = new JLabel("AddTest.java");   
        
        AddCodeButton = new JButton("Add");
        AddCodeButton.setActionCommand("getCode");
        AddCodeButton.addActionListener(this);
        
        
        AddCodeButton.setActionCommand("getCode");        
        lblException = new JLabel("");
        
        result1 = new JLabel("");
        
        list = new JList ();
        
        model = new DefaultListModel();
        list.setModel(model);
        
        cleanTestButton = new JButton("Clean Test list");
        cleanTestButton.setActionCommand("cleanList");
        cleanTestButton.addActionListener(this);
        
        
        cleanCodeButton = new JButton("Clean Code");
        cleanCodeButton.setActionCommand("cleanCode");
        cleanCodeButton.addActionListener(this);
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        JPanel panel_3 = new JPanel();
        
        JLabel lblMutantOperatorchoose = new JLabel("Mutant Operator (choose at least one for mutation test)");
        
        chckbxAllOperators.setActionCommand("checkAll");
        chckbxAllOperators.addActionListener(this);
        
 
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(24)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(result1)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        									.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        									.addGroup(groupLayout.createSequentialGroup()
        										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        											.addGroup(groupLayout.createSequentialGroup()
        												.addComponent(addTest)
        												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        												.addComponent(addTestButton))
        											.addGroup(groupLayout.createSequentialGroup()
        												.addComponent(addCode)
        												.addGap(18)
        												.addComponent(AddCodeButton)))
        										.addPreferredGap(ComponentPlacement.UNRELATED)
        										.addComponent(cleanCodeButton)
        										.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE))
        									.addComponent(lblMutantOperatorchoose))
        								.addComponent(list, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
        							.addGap(210)
        							.addComponent(lblException)
        							.addGap(187)
        							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(142)
        					.addComponent(cleanTestButton))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(126)
        					.addComponent(testButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(chckbxAllOperators)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(46)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(addCode)
        						.addComponent(AddCodeButton)
        						.addComponent(cleanCodeButton))
        					.addGap(18)
        					.addComponent(result1)
        					.addGap(27)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(addTest)
        						.addComponent(addTestButton))
        					.addGap(20)
        					.addComponent(list, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
        					.addGap(11)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(79)
        							.addComponent(lblException))
        						.addGroup(groupLayout.createSequentialGroup()
        							.addGap(40)
        							.addComponent(lblMutantOperatorchoose)
        							.addGap(9)
        							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(295)
        					.addComponent(cleanTestButton)))
        			.addGap(18)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(testButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        				.addComponent(chckbxAllOperators))
        			.addGap(28))
        );
        panel_3.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel = new JPanel();
        panel_3.add(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        
        panel.add(chckbxNewCheckBox);
        
        panel.add(chckbxNewCheckBox_1);
        panel.add(chckbxNewCheckBox_2);
        panel.add(chckbxNewCheckBox_3);
        panel.add(chckbxNewCheckBox_4);
        
        JPanel panel_1 = new JPanel();
        panel_3.add(panel_1);
        
        
        
        
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));
        panel_1.add(chckbxNewCheckBox_5);
        
        panel_1.add(chckbxNewCheckBox_6);
        
        panel_1.add(chckbxNewCheckBox_7);
        panel_1.add(chckbxNewCheckBox_8);
        panel_1.add(chckbxNewCheckBox_9);
        
        JPanel panel_2 = new JPanel();
        panel_3.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));
        panel_2.add(chckbxNewCheckBox_11);
        panel_2.add(chckbxNewCheckBox_12);
        panel_2.add(chckbxNewCheckBox_13);
        panel_2.add(chckbxNewCheckBox_14);
        panel_2.add(chckbxNewCheckBox_10);
        getContentPane().setLayout(groupLayout);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    public void actionPerformed(ActionEvent e)
    {
		FileHelper fileController = new FileHelper();

        if(e.getActionCommand().equals("getTests")) {
        	JFileChooser fc = new JFileChooser();
            StringBuilder sb = new StringBuilder();
        	
        	if ( fc.showOpenDialog(null) == fc.APPROVE_OPTION ) {
	            File arquivo = fc.getSelectedFile();
	            path = arquivo.getAbsolutePath();
	            if ( !ehJava(path) ) {
	            	lblException.setText("Arquivo Invalido - Nao eh.java");
	            } else {
	            	testAdded = true;
	            	model.addElement(path);
	            	testsPath.add(path);
	            }
	            
	            //label.setText(path);
        	} else {
        		lblException.setText("Arquivo Não escolhido");
        	}
        } else if ( e.getActionCommand().equals("startTest") ) {
        	model.addElement(testQuantity);
        	model.addElement(codeQuantity);
        	if(folderPath.equals("") || testsPath.isEmpty()){        		
        		lblException.setText("Não foi adicionado arquivos para testar");
        	}
        	else{
        		try{
        			fileController.copyFilesToProject(testsPath);
        		}catch(Exception ex ){
        			System.out.println("Error : Failed to copy Files");
        		}
        		if(chckbxNewCheckBox.isSelected()){
        			operators = operators + " -AORB";
        		}
        		if(chckbxNewCheckBox_1.isSelected()){
        			operators = operators + " -AORS";
        		}
        		if(chckbxNewCheckBox_2.isSelected()){
        			operators = operators + " -AOIU";
        		}
        		if(chckbxNewCheckBox_3.isSelected()){
        			operators = operators + " -AOIS";
        		}
        		if(chckbxNewCheckBox_4.isSelected()){
        			operators = operators + " -AODU";
        		}
        		if(chckbxNewCheckBox_5.isSelected()){
        			operators = operators + " -AODS";
        		}
        		if(chckbxNewCheckBox_6.isSelected()){
        			operators = operators + " -ROR";
        		}
        		if(chckbxNewCheckBox_7.isSelected()){
        			operators = operators + " -COR";
        		}
        		if(chckbxNewCheckBox_8.isSelected()){
        			operators = operators + " -COD";
        		}
        		if(chckbxNewCheckBox_9.isSelected()){
        			operators = operators + " -COI";
        		}
        		if(chckbxNewCheckBox_10.isSelected()){
        			operators = operators + " -SOR";
        		}
        		if(chckbxNewCheckBox_11.isSelected()){
        			operators = operators + " -LOR";
        		}
        		if(chckbxNewCheckBox_12.isSelected()){
        			operators = operators + " -LOI";
        		}
        		if(chckbxNewCheckBox_13.isSelected()){
        			operators = operators + " -LOD";
        		}
        		if(chckbxNewCheckBox_14.isSelected()){
        			operators = operators + " -ASRS";
        		}
        		
            	exit = true;
        		setVisible(false);
            	dispose();
        	}
        } else if (e.getActionCommand().equals("getCode")) {
        	JFileChooser chooser = new JFileChooser();
        	 chooser = new JFileChooser(); 
        	    chooser.setCurrentDirectory(new java.io.File("."));
        	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	    chooser.setAcceptAllFileFilterUsed(false);
        	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
        	    	folderPath = chooser.getSelectedFile().getPath();
        	    	codeAdded = true;
        	      }
        	    else {
        	      System.out.println("No Selection ");
        	      }   	

        } else if (e.getActionCommand().equals("cleanCode")) {
        	codeCleanFlag = true;
        	path = "";
        	codePath= "";
        	result1.setText("");
        	
        	
        } else if (e.getActionCommand().equals("cleanList")) {
        	testCleanFlag = true;
        	path = "";
        	testsPath.clear();
        	model.clear();
        } else if (e.getActionCommand().equals("checkAll")) {
        	if (chckbxAllOperators.isSelected()){
        		chckbxNewCheckBox.setSelected(true);
        		chckbxNewCheckBox_1.setSelected(true);
        		chckbxNewCheckBox_2.setSelected(true);
        		chckbxNewCheckBox_3.setSelected(true);
        		chckbxNewCheckBox_4.setSelected(true);
        		chckbxNewCheckBox_5.setSelected(true);
        		chckbxNewCheckBox_6.setSelected(true);
        		chckbxNewCheckBox_7.setSelected(true);
        		chckbxNewCheckBox_8.setSelected(true);
        		chckbxNewCheckBox_9.setSelected(true);
        		chckbxNewCheckBox_10.setSelected(true);
        		chckbxNewCheckBox_11.setSelected(true);
        		chckbxNewCheckBox_12.setSelected(true);
        		chckbxNewCheckBox_13.setSelected(true);
        		chckbxNewCheckBox_14.setSelected(true);
        	}else{
        		chckbxNewCheckBox.setSelected(false);
        		chckbxNewCheckBox_1.setSelected(false);
        		chckbxNewCheckBox_2.setSelected(false);
        		chckbxNewCheckBox_3.setSelected(false);
        		chckbxNewCheckBox_4.setSelected(false);
        		chckbxNewCheckBox_5.setSelected(false);
        		chckbxNewCheckBox_6.setSelected(false);
        		chckbxNewCheckBox_7.setSelected(false);
        		chckbxNewCheckBox_8.setSelected(false);
        		chckbxNewCheckBox_9.setSelected(false);
        		chckbxNewCheckBox_10.setSelected(false);
        		chckbxNewCheckBox_11.setSelected(false);
        		chckbxNewCheckBox_12.setSelected(false);
        		chckbxNewCheckBox_13.setSelected(false);
        		chckbxNewCheckBox_14.setSelected(false);
        	}
        }
    }
    
    public boolean ehJava ( String filePath ) {
    	boolean retorno = false;
    	// arquivo.java
    	try {
    		String tipo = filePath.substring(filePath.length()-5);
    		
    		if ( tipo.equals(".java") ) {
    			return true;
    		}
    		
    	} catch ( Exception e ) {
    		System.out.println("Invalid File");
    	}
    	
    	
    	return retorno;
    }
    
    public static void main(String[] args)
    {
        new MainView();
    }
}