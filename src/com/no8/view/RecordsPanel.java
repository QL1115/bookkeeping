package com.no8.view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class RecordsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private static int extraWindowWidth = 100;

	private JPanel createPanel() {
		// TODO 歷史收支畫面
		JPanel panel = new JPanel(){
			private static final long serialVersionUID = 1L;
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
		
		return panel;
	}
	
	public RecordsPanel() {
		this.add(createPanel());
	}
}
