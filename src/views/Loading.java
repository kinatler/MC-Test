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
public class Loading extends JFrame {
    public boolean toclose = false;
    public boolean toshow = true;;
    JLabel lblNewLabel = new JLabel("");
    public static JFrame frame = new JFrame();
    private final JTextPane textPane = new JTextPane();

	 
    public Loading()
    {
        super("Loading");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(false);
        frame.setResizable(false);
        //textPane.enableInputMethods(false);
        textPane.setEditable(false);
        
        
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        				.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lblNewLabel)
        			.addContainerGap(33, Short.MAX_VALUE))
        );
        frame.getContentPane().setLayout(groupLayout);
    }
    
    public void setText(String text)
    {
    	textPane.setText(text);
    }
    
    public void setText2(String text)
    {
    	lblNewLabel.setText(text);
    }
    
    public static void main(String[] args)
    {	
        new Loading();
    }
}