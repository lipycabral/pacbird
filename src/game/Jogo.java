package game;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Felipy da Costa Cabral
// Matricula : 20160300016
public class Jogo extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel background = new JLabel(new ImageIcon(getClass().getResource("fundo.png")));
	JLabel chao = new JLabel(new ImageIcon(getClass().getResource("chao.png")));
	JLabel chao2 = new JLabel(new ImageIcon(getClass().getResource("chao.png")));
	JLabel pacBird = new JLabel(new ImageIcon(getClass().getResource("pacbird.gif")));
	JLabel fantasma = new JLabel(new ImageIcon(getClass().getResource("tunel_fantasma.png")));
	JLabel lbPontuacao = new JLabel("Pontuação");
	JLabel lbPonto = new JLabel("0");
	JLabel lbHi = new JLabel("Melhor pontuação");
	JLabel lbHiP = new JLabel(Dados.Hi+"");
	JLabel fantasma_invertido = new JLabel(new ImageIcon(getClass().getResource("tunel_fantasma_invertido.png")));
	Movimento movimento = new Movimento();
	Tempo tempo = new Tempo();
	Itens itens = new Itens();
	int x=0,y;
	int pontuacao = 0;
	int segundos = 0;
	double movChao = 0;
	double movFant = 0;
	int queda = 1;
	int tamanho;
	int dificuldade,vPont;
	public Jogo() {
		Janela();
		PacBird();
		Chao();
		Fantasmas();
		Background();
		movimento.start();
		tempo.start();
		itens.start();
		if(Dados.usuario.equals("")) {
			Dados.usuario = "Anonimo";
		}
	}
	public void Janela() {
		setTitle("PacBird");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(384, 512);
		setVisible(true);
		setLocationRelativeTo(null);
		add(lbPonto);
		lbPonto.setBounds(65,400,65,50);
		add(lbPontuacao);
		lbPontuacao.setBounds(0, 400, 65, 50);
		add(lbHi);
		lbHi.setBounds(250, 400, 120, 50);
		add(lbHiP);
		lbHiP.setBounds(355, 400, 65, 50);
	}
	public void Chao() {
		add(chao);
		//-movChao aqui é 0 só para aparecer na tela
		chao.setBounds((int) -movChao, 403, 384, 109);
		add(chao2);
		chao2.setBounds((int) -movChao, 403, 384, 109);
	}
	public void Background() {
		add(background);
		background.setBounds(0, 0, 384, 512);
	}
	public void PacBird() {
		add(pacBird);
		pacBird.setBounds(x, 10, 74, 52);
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					//caso aperte espaço a gravidade vai ser alterada pra 1 ou -1
					queda = queda*(-1);
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					queda = queda*(-1);
				}
			}
		});
		addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {

			}
			public void mousePressed(MouseEvent e) {
				//caso clique do mouse a gravidade vai ser alterada pra 1 ou -1
				queda = queda*(-1);
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {	
			}
			public void mouseClicked(MouseEvent e) {

			}
		});
	}
	public void Fantasmas() {
		add(fantasma);
		add(fantasma_invertido);
	}
	public class Movimento extends Thread{
		public void run() {
			while (true) {
				if (pontuacao<50) {
					//dificuldade é a quantidade de milisegundos dentro da Classe itens
					dificuldade = 20;
					//vPont seria a velocidade de pontos que é a quantidade de milisegundos dentro da Classe tempo
					vPont = 500;
				}
				if ((pontuacao>50) && (pontuacao<100)){
					dificuldade = 15;
					vPont = 400;
				}
				if((pontuacao>100) && (pontuacao<150)){
					dificuldade = 14;
					vPont = 350;
				}
				if((pontuacao>150) && (pontuacao<200)){
					dificuldade = 12;
					vPont = 250;
				}
				if((pontuacao>200) && (pontuacao<250)){
					dificuldade = 11;
					vPont = 150;
				}
				if((pontuacao>250) && (pontuacao<300)){
					dificuldade = 10;
					vPont = 100;
				}
				if((pontuacao>300) && (pontuacao<350)){
					dificuldade = 9;
					vPont = 90;
				}
				if((pontuacao>350) && (pontuacao<400)){
					dificuldade = 8;
					vPont = 80;
				}
				if((pontuacao>400) && (pontuacao<450)){
					dificuldade = 7;
					vPont = 70;
				}
				if((pontuacao>450) && (pontuacao<500)){
					dificuldade = 6;
					vPont = 60;
				}
				if((pontuacao>500) && (pontuacao<550)){
					dificuldade = 5;
					vPont = 50;
				}
				if((pontuacao>550) && (pontuacao<600)){
					dificuldade = 4;
					vPont = 40;
				}
				if((pontuacao>600) && (pontuacao<650)){
					dificuldade = 3;
					vPont = 30;
				}
				if((pontuacao>650) && (pontuacao<700)){
					dificuldade = 2;
					vPont = 20;
				}
				try { Thread.sleep(5);} catch (Exception e) {}
				//movChao = velocidade com que o chao se move sempre movendo o X em 2 e 
				//sempre fazendo a imagem reaparecer com o resto(%) de movChao com a largura da tela
				movChao +=2;
				movChao = movChao %384;
				chao.setBounds((int) -movChao, 403, 384, 109);
				//aqui eu tenho de colocar 384 - movChao pro fundo não ficar vazio por um tempo e só dps voltar a reaparecer se
				//tirar o 384 ele vai ficar vazio por um tempo e só depois voltar a aparecer
				chao2.setBounds((int) ((int) 384 - movChao), 403, 384, 109);
				if(y<360) {
					pacBird.setBounds(x, y+=queda, 74, 50);
				}
				if ((y==360) || (y==0) || (bateu(pacBird, fantasma)==true) || (bateu(pacBird, fantasma_invertido))) {
					itens.stop();
					tempo.stop();
					JOptionPane.showMessageDialog(null, Dados.usuario.toUpperCase()+" sua pontuação "+pontuacao);
					new NovoJogo();
					dispose();
					movimento.stop();
				}
			}
		}
	}
	public class Tempo extends Thread{
		public void run() {
			while (true) {
				try { Thread.sleep(vPont);} catch (Exception e) {}
				if(y<385 && y > 0) {
					//essa pontuação é atualizada de acordo com a variavel vPont lá de cima a cada certa quantidade de ponto
					//ela diminui a taxa na qual ela é atualizada por sempre ir diminuindo a quantidade de milisegundos
					pontuacao++;
				}
				lbPonto.setText(pontuacao+"");
				if(pontuacao > Dados.Hi) {
					Dados.Hi++;
					lbHiP.setText(Dados.Hi+"");
				}
			}
		}
	}
	public class Itens extends Thread{
		public void run() {
			while (true) {
				try { Thread.sleep(dificuldade);} catch (Exception e) {}
				segundos++;
				Random nRandom = new Random();
				//funciona da mesma forma que o movChao só coloquei 400 pra não reaparecer logo que acaba a tela então 
				//ganha um pouco de tempo
				movFant += 2;
				movFant = movFant%400;
				//o tempo que será atualizado vai ser de 100 tempos que será alterado de acordo com a dificuldade
				if(segundos == 100) {
					//aqui o tamanho será atualizado de acordo com um random que vai ser entre 0 e 250
					//pra não ter como não ter como não ter espaço pra passar e depois é diminuido em 400
					//por que é a altura da imagem dos fantasmas pra ele ficar negativo e aparecer fora da imagem
					tamanho = nRandom.nextInt(250)-400;
					segundos = 0;
				}
				fantasma.setBounds((int) ((int) 384 - movFant), tamanho, 40, 400);
				//fantasma_invertido vai receber o tamanho + 600 pra ter um espaço de 200 pixels de diferenzxça entre um fantasma
				// e outro pra ter espaço suficiente pra passar
				fantasma_invertido.setBounds((int) ((int) 384 - movFant), tamanho + 600, 40, 400);
			}
		}
	}

	//Aqui ele pega os componentes que seriam as jlabels
	public boolean bateu(Component a, Component b) {
		//getX serve pra pegar x onde o componente está
		//getY serve pra pegar y onde o componente está
		int aX = a.getX();
		int aY = a.getY();
		//lado direito vai receber o x + o getWidth que é o comprimento da imagem 
		int ladoDireitoA = aX+a.getWidth();
		//lado esquerdo vai receber o getX
		int ladoEsquerdoA= aX;
		//lado baixo vai receber o Y + o getHeight que é a altura da imagem
		int ladoBaixoA= aY+a.getHeight();
		//lado cima vai receber o getY
		int ladoCimaA= aY;
		//tudo vai se repetir aqui só atribuindo a outras variaveis por que é outra imagem
		int bX = b.getX();
		int bY = b.getY();
		int ladoDireitoB = bX+b.getWidth();
		int ladoEsquerdoB= bX;
		int ladoBaixoB= bY+b.getHeight();
		int ladoCimaB= bY;

		boolean bateu = false;

		boolean cDireita=false;
		boolean cCima=false;
		boolean cBaixo=false;
		boolean cEsquerda=false;
		//vai verificar se bateu o lado direito de um componente com o lado esquerdo de outro componente
		if(ladoDireitoA>=ladoEsquerdoB) {
			cDireita=true;
		}
		//e vai se repetir pra verificar se bateu ou não se bater vai receber um true e dentro do if
		//de onde ele é usado vai receber o comando do que fazer caso bater for igual a true
		if(ladoCimaA<=ladoBaixoB) {
			cCima=true;
		}
		if(ladoBaixoA>=ladoCimaB) {
			cBaixo=true;
		}
		if(ladoEsquerdoA<=ladoDireitoB) {
			cEsquerda=true;
		}

		if(cDireita && cEsquerda && cBaixo && cCima) {
			bateu = true;
		}

		return bateu;
	}
}