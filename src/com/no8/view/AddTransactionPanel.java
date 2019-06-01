package com.no8.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddTransactionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
    private static int extraWindowWidth = 100;
	
	
	private JRadioButton incomeRBtn, expenseRBtn;
	private JComboBox<String> categoryBox;
	private JButton addBtn;
	private JPanel inputPnl;
	private JTextField amountF;
	
	private JLabel emptyLbl = new JLabel();
	
	public JPanel createAddTransPage() {
		
		incomeRBtn = new JRadioButton("Income");
		expenseRBtn = new JRadioButton("Expense");
		ButtonGroup gr = new ButtonGroup();
		gr.add(expenseRBtn);
		gr.add(incomeRBtn);
		
		categoryBox = new JComboBox<>();
		categoryBox.addItem("Miscellaneous");
		amountF = new JTextField();
		addBtn = new JButton("Add Transaction");
		inputPnl = new JPanel();
		inputPnl.setLayout(new GridLayout(3, 2));
		inputPnl.add(incomeRBtn);
		inputPnl.add(expenseRBtn);
		inputPnl.add(categoryBox);
		inputPnl.add(amountF);
		inputPnl.add(emptyLbl);
		inputPnl.add(addBtn);
		inputPnl.setBorder(new TitledBorder(new EtchedBorder(), "Add Transaction"));
		
		
		JPanel addTtransPnl = new JPanel(){
			private static final long serialVersionUID = 1L;
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
		addTtransPnl.add(inputPnl);
		
		
		return addTtransPnl;
	}
	
	public AddTransactionPanel() {
		this.add(createAddTransPage()); 
		
	}
	
	
	
	
	
	
	
}
