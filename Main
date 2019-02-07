package piłka;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {

	public Main() {
		this.setTitle("Animacja piłek");
		this.setBounds(300, 300, 700, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.getContentPane().add(panelAnimacji);
		this.getContentPane().add(panelButtonow, BorderLayout.SOUTH);
		panelAnimacji.setBackground(Color.GREEN);

		JButton buttonDodaj = (JButton) panelButtonow.add(new JButton("Dodaj"));
		buttonDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.dodajPilke();
			}
		});

		JButton buttonStart = (JButton) panelButtonow.add(new JButton("Start"));
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.start();
			}
		});
		JButton buttonStop = (JButton) panelButtonow.add(new JButton("Stop"));
		buttonStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.stop();
			}
		});
		JButton buttonPrzyspiesz = (JButton) panelButtonow.add(new JButton("Przyśpiesz"));
		buttonPrzyspiesz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.przyspiesz();
			}
		});
		JButton buttonZwolnij = (JButton) panelButtonow.add(new JButton("Zwolnij"));
		buttonZwolnij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.zwolnij();
			}
		});
		JButton buttonUsun = (JButton) panelButtonow.add(new JButton("Usuń"));
		buttonUsun.setBackground(Color.red);
		buttonUsun.setForeground(Color.black);
		buttonUsun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.usun();
			}
		});
		JButton buttonIleOdbic = (JButton) panelButtonow.add(new JButton("Ilość odbić"));
		buttonIleOdbic.setBackground(Color.black);
		buttonIleOdbic.setForeground(Color.green);
		buttonIleOdbic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAnimacji.ileOdbic();
			}
		});
		panelButtonow.add(label);
		panelButtonow.add(poleTekstowe);
	}

	JPanel panelButtonow = new JPanel();
	PanelAnimacji panelAnimacji = new PanelAnimacji();
	JTextField poleTekstowe = new JTextField(2);
	JLabel label = new JLabel("Ilość piłek:");

	class PanelAnimacji extends JPanel {

		private ArrayList<Ball> listaPilek = new ArrayList<>();
		PanelAnimacji panel = this;
		private volatile boolean zatrzymany = false;
		Thread watek;
		ThreadGroup grupaWatkow = new ThreadGroup("Grupa Wątków");
		private Object lock = new Object();
		private int tempo = 3;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < listaPilek.size(); i++) {
				Ball ball = listaPilek.get(i);
				g.drawImage(ball.getBall(), ball.x, ball.y, null);
			}
		}

		class BallRunnable implements Runnable {
			Ball ball = new Ball();

			public BallRunnable(Ball ball) {
				this.ball = ball;
			}

			@Override
			public void run() {

				while (true) {

					synchronized (lock) {

						while (zatrzymany) {
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					ball.ruszPilka(panel);
					repaint();
					try {
						Thread.sleep(tempo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void dodajPilke() {
			if (zatrzymany == false) {
				listaPilek.add(new Ball());
				watek = new Thread(grupaWatkow, new BallRunnable(listaPilek.get(listaPilek.size() - 1)));
				watek.start();
				poleTekstowe.setText("" + listaPilek.size());
			} else {
				JOptionPane.showMessageDialog(rootPane, "Uruchom animację aby dodać kolejną piłkę", "Uruchom animację",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		public void start() {
			if (zatrzymany == true) {
				synchronized (lock) {
					zatrzymany = false;
					lock.notifyAll();
				}
			}
		}

		public void stop() {
			zatrzymany = true;
		}

		public void przyspiesz() {
			if (tempo > 1) {
				tempo--;
			}
		}

		public void zwolnij() {
			tempo++;
		}

		public void ileOdbic() {

			if (zatrzymany == true) {
				int odbicia = 0;
				for (int i = 0; i < listaPilek.size(); i++) {
					odbicia = odbicia + listaPilek.get(i).iloscOdbic;
				}
				Object[] opcje = { "Ok, dzięki za info!" };
				JOptionPane.showOptionDialog(rootPane, "Piłki odbiły się już " + odbicia + " razy!", "Liczba odbić",
						JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, new ImageIcon("ball.png"), opcje,
						opcje[0]);
			} else {
				JOptionPane.showMessageDialog(rootPane, "Zatrzymaj animację aby zobaczyć liczbę wszystkich odbić",
						"Zatrzymaj animację", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		public void usun() {
			listaPilek.clear();
			poleTekstowe.setText("" + listaPilek.size());
			repaint();
		}
	}

	public static void main(String[] args) {
		new Main().setVisible(true);
	}

}
