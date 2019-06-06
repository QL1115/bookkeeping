package com.no8.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import com.no8.controller.UserService;
import com.no8.exception.BookkeepingException;
import com.no8.model.User;

public class LogSignPanel extends JPanel{

	private static final long serialVersionUID = 1L;
    private static int extraWindowWidth = 100;
	
	private static final int FIELD_WIDTH = 15;
	
	private UserService userService = new UserService();
	
	private JRadioButton logRBtn;
	private JRadioButton signRBtn;
	private JButton goBtn;
	private JPanel lsPnl;
	private JPanel smallLSPnl;
	private JPanel goPnl;
	
	private JLabel userIdLbl;
	private JLabel passwordLbl;
	private JLabel userNameLbl;
	
	private JTextField userIdTF;
	private JTextField passwordTF;
	private JTextField userNameTF;
	
	private JPanel createLSPage() {
		
		JPanel LSPagePnl = new JPanel() {
			private static final long serialVersionUID = 1L;
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
		
	
		smallLSPnl = new JPanel();
		
		logRBtn = new JRadioButton("sign in");
		logRBtn.setSelected(true);
		signRBtn = new JRadioButton("sign up now");

		smallLSPnl.add(logRBtn);
		smallLSPnl.add(signRBtn);
		ButtonGroup gr = new ButtonGroup();
		gr.add(logRBtn);
		gr.add(signRBtn);
		
		goPnl = new JPanel();
		goBtn = new JButton("GO");

		goPnl.add(goBtn);
		
		userIdLbl = new JLabel("     ID:");
		passwordLbl = new JLabel("     Password:");
		userNameLbl = new JLabel("     User Name:");
		userNameLbl.setVisible(false);
		userIdTF = new JTextField(FIELD_WIDTH);
		passwordTF = new JTextField(FIELD_WIDTH);
		userNameTF = new JTextField(FIELD_WIDTH);
		userNameTF.setVisible(false);
		Border bd = BorderFactory.createLineBorder(Color.gray, 2);
		lsPnl = new JPanel();
		lsPnl.setBorder(bd);
		lsPnl.setLayout(new GridLayout(12, 1));
		lsPnl.add(smallLSPnl);
		lsPnl.add(userIdLbl);
		lsPnl.add(userIdTF);
		lsPnl.add(passwordLbl);
		lsPnl.add(passwordTF);
		lsPnl.add(userNameLbl);
		lsPnl.add(userNameTF);
		lsPnl.add(goPnl);
		
		fulfillAction();	// Action
		LSPagePnl.setLayout(new BorderLayout());
		LSPagePnl.add(lsPnl, BorderLayout.CENTER);
		
		return LSPagePnl;
	}	
	
	public LogSignPanel() {
		this.add(createLSPage());
	}
	
	/**
	 * 專門處理 action listener 的 method
	 */
	private void fulfillAction() {
		logRBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userNameLbl.setVisible(false);
				userNameTF.setVisible(false);
				// 清空
				userIdTF.setText("");
				passwordTF.setText("");
				userNameTF.setText("");
			}
		});
		
		signRBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userNameLbl.setVisible(true);
				userNameTF.setVisible(true);
				// 清空
				userIdTF.setText("");
				passwordTF.setText("");
				userNameTF.setText("");
			}
		});
		
		// Go Button
		goBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 獲取值
				String userId = userIdTF.getText();
				String userPwd = passwordTF.getText();
				
				User user = null;
				try {
					if (logRBtn.isSelected()) {			// 登錄
						// 呼叫 UserService的login方法
						user = userService.signIn(userId, userPwd);
					} else if (signRBtn.isSelected()) {	// 註冊
						// 呼叫 UserService的signUp方法
						String userName = userNameTF.getText();
						user = userService.signUp(userId, userName, userPwd);
					}
				} catch (BookkeepingException ex) {
					// TODO
				}
				//
//				if (user != null) {
//					// TODO 其他頁籤可顯示
//				} else {
//					// TODO 登錄失敗處理
//				}
			}
		});
	}
}
