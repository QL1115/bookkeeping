package com.no8;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.no8.view.AddTransactionPanel;
import com.no8.view.FeedbackPanel;
import com.no8.view.LogSignPanel;
import com.no8.view.RecordsPanel;

public class MainTester{
	
    public void addComponentToPane(Container pane) {
    	//
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Sign in / Sign up", new LogSignPanel());
        tabbedPane.addTab("Transaction", new AddTransactionPanel());
        tabbedPane.addTab("Records", new RecordsPanel());
        tabbedPane.addTab("Feedback", new FeedbackPanel());

        pane.add(tabbedPane, BorderLayout.CENTER);
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("記帳本");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainTester demo = new MainTester();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	createAndShowGUI();	
    }

}
