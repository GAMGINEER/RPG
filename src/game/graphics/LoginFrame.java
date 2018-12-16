package game.graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
	private HashMap<String, String> ac = new HashMap<>();
	private JTextField txtID;
	private JPasswordField txtPW;
	private JButton login, account;
	private JLabel loginText = new JLabel();
	
	public LoginFrame() {
		setLayout(new GridLayout(3, 1));
		setTitle("로그인 창");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelID = new JPanel();
		JPanel panelPW = new JPanel();
		JPanel panelBtn = new JPanel();
		
		txtID = new JTextField(12);
		txtPW = new JPasswordField(12);
		
		JLabel labelID = new JLabel("ID ");
		JLabel labelPW = new JLabel("PW ");
		
		login = new JButton("로그인");
		login.addActionListener(this);
		account = new JButton("회원가입");
		account.addActionListener(this);
		
		panelID.add(labelID);
		panelID.add(txtID);
		
		panelPW.add(labelPW);
		panelPW.add(txtPW);
		
		panelBtn.add(login);
		panelBtn.add(account);
		
		add(panelID);
		add(panelPW);
		add(panelBtn);
		add(loginText);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login) {
			String str = String.valueOf(txtPW.getPassword());
			int i=0;
			for(i=0; i<ac.size(); i++) {
				if(ac.containsKey(txtID.getText())&& ac.get(txtID.getText()).equals(str)) {
					loginText.setText("로그인 성공");
					break;
				}
			}
			if(i==ac.size())
				loginText.setText("로그인 실패");
		}
		if(e.getSource()==account)
			new Account();
	}
	
	class Account extends JFrame implements ActionListener {
		private JTextField acID, acPW;
		private JButton cf;
		
		public Account() {
			setLayout(new GridLayout(3, 1));
			setTitle("회원가입");
			setSize(100, 200);
			
			acID = new JTextField(10);
			acPW = new JTextField(10);
			
			JPanel panelID = new JPanel();
			JPanel panelPW = new JPanel();
			
			JLabel labelID = new JLabel("ID ");
			JLabel labelPW = new JLabel("PW ");
			
			cf = new JButton("가입하기");
			cf.addActionListener(this);
			
			panelID.add(labelID);
			panelID.add(acID);
			
			panelPW.add(labelPW);
			panelPW.add(acPW);
			
			add(panelID);
			add(panelPW);
			add(cf);
			
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cf) {
				int j=0;
				for(j=0; j<ac.size(); j++) {
					if(ac.containsKey(acID.getText())) {
						JOptionPane.showMessageDialog(null, "이미 있는 계정입니다", "System", JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				if(j==ac.size()) {
					ac.put(acID.getText(), acPW.getText());
					JOptionPane.showMessageDialog(null, "회원가입 완료");
					dispose();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
