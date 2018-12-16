package game.graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
	private String ID="abcd1234", PW="abcd1234";
	private JTextField txtID;
	private JPasswordField txtPW;
	private JButton login;
	private JLabel loginText = new JLabel();
	
	public LoginFrame() {
		setLayout(new GridLayout(4, 1));
		setTitle("로그인 창");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelID = new JPanel();
		JPanel panelPW = new JPanel();
		
		txtID = new JTextField(12);
		txtPW = new JPasswordField(12);
		
		JLabel labelID = new JLabel("ID ");
		JLabel labelPW = new JLabel("PW ");
		
		login = new JButton("LOGIN");
		login.addActionListener(this);
		
		panelID.add(labelID);
		panelID.add(txtID);
		
		panelPW.add(labelPW);
		panelPW.add(txtPW);
		
		add(panelID);
		add(panelPW);
		add(login);
		add(loginText);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login) {
			String str = String.valueOf(txtPW.getPassword());
			if(ID.equals(txtID.getText())&&PW.equals(str)) {
				loginText.setText("로그인 성공");
			}else {
				loginText.setText("로그인 실패");
			}
		}
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}
