package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class NovoJogo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	ImageIcon icon = new ImageIcon(getClass().getResource("menu.png"));
	JLabel background = new JLabel(icon);
	JLabel lbPont = new JLabel(new ImageIcon(getClass().getResource("placar.png")));
	JLabel lbHi = new JLabel(Dados.Hi+"");
	JButton btFim = new JButton("Tentar de novo"); 
	public NovoJogo() {
		//DEFAULT
		setTitle("PacBird");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(384, 512);
		setLocationRelativeTo(null);
		setVisible(true);
		//END-DEFAULT
		//BODY
		add(lbHi);
		add(background);
		background.setLayout(null);
		background.add(btFim);
		background.add(lbPont);
		lbPont.setLayout(null);
		
		lbPont.setBounds(75, 320, 225, 113);
		lbHi.setBounds(240, 359, 100, 30);
		lbHi.setFont(new Font("Arial", 1, 30));
		lbHi.setForeground(new Color(195,153,42));
		btFim.setBounds(35, 265, 300, 50);
		btFim.addActionListener(this);
		//END-BODY
	}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==btFim) {
			new Jogo();
			dispose();
		}
	}

}
