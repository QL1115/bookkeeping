package com.no8.view;

import java.awt.Dimension;
import javax.swing.JPanel;

public class FeedbackPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private static int extraWindowWidth = 100;
    
    private JPanel createFbPage() {
    	JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
    	};
    	
    	// TODO 回饋畫面
    	return panel;
    }
    
    public FeedbackPanel() {
    	this.add(createFbPage());
    }

}
