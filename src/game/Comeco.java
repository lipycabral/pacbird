package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Comeco extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	ImageIcon icon = new ImageIcon(getClass().getResource("menu.png"));
	JLabel background = new JLabel(icon);
	JLabel lbNome = new JLabel("Digite o seu nome");
	JTextField tfNome = new JTextField();
	JButton btFim = new JButton("OK"); 
	public Comeco() {
		//DEFAULT
		setTitle("PacBird");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(384, 512);
		setLocationRelativeTo(null);
		setVisible(true);
		//END-DEFAULT
		//BODY
		add(background);
		background.setLayout(null);
		background.add(lbNome);
		lbNome.setBounds(25, 265, 110, 50);
		background.add(tfNome);
		tfNome.setBounds(135, 265, 110, 50);
		background.add(btFim);
		btFim.setBounds(245, 265, 55, 50);
		btFim.addActionListener(this);
		//END-BODY
	}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==btFim) {
			Dados.usuario = tfNome.getText();
			new Jogo();
			dispose();
		}
	}
}
