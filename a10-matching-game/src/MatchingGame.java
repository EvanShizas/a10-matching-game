/**
 * Card-matching game that uses ArrayLists to store card faces.
 * 
 * modified     20220408
 * date         20220404
 * @filename    MatchingGame.java
 * @author      Evan Shizas
 * @version     1.0.0
 * @see         A10 - Matching Game
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.util.*;
import javax.swing.ImageIcon;

public class MatchingGame extends JFrame {

	private JPanel contentPane;
	private JTextField dialogBox;
	private JButton card1;
	private JButton card2;
	private JButton card3;
	private JButton card4;
	private JButton card5;
	private JButton card6;
	private JButton card7;
	private JButton card8;
	private JButton card9;
	private JButton card10;
	private JButton card11;
	private JButton card12;
	private JButton card13;
	private JButton card14;
	private JButton card15;
	private JButton card16;
	private JButton card17;
	private JButton card18;
	private JButton card19;
	private JButton card20;
	private JButton play;
	private JButton guessAgain;
	private JButton reset;
	private JButton exit;
	
	/**
	 * "Array value -> #" is the designated value that corresponds to a specific image.
	 * Through this system, values in the ArrayList, "cards" can be associated with the GUI cards,
	 * allowing images to be loaded on specific cards through the cardImageLoad() method.
	 */
	
	ImageIcon winIcon = new ImageIcon("images//spade.png"); //Program Icon
	ImageIcon back = new ImageIcon("images//back.jpg");
	ImageIcon correct = new ImageIcon("images//correct.png");
	ImageIcon donkeyKong = new ImageIcon("images//donkey-kong.jpg"); //Array value -> 1
	ImageIcon link = new ImageIcon("images//link.jpg"); //Array value -> 2
	ImageIcon luigi = new ImageIcon("images//luigi.jpg"); //Array value -> 3
	ImageIcon mario = new ImageIcon("images//mario.jpg"); //Array value -> 4
	ImageIcon marioKart = new ImageIcon("images//mario-kart.jpg"); //Array value -> 5
	ImageIcon nintendo = new ImageIcon("images//nintendo.png"); //Array value -> 6
	ImageIcon nintendoSwitch = new ImageIcon("images//nintendo-switch-logo.png"); //Array value -> 7
	ImageIcon pikachu = new ImageIcon("images//pikachu.jpg"); //Array value -> 8
	ImageIcon smashOrb = new ImageIcon("images//smash-orb.jpg"); //Array value -> 9
	ImageIcon toad = new ImageIcon("images//toad.jpg"); //Array value -> 10
	
	boolean isMacOS = false, secondCardSelect = false, cardMatch = false, tryAgain = false, gameFinished = false, allowDebug = false; //allowDebug -> true to allow debugging
	
	int cardSelect = 0, cardOne = 0, cardTwo = 0;
	
	ArrayList <Integer> cards = new ArrayList <Integer>(); //ArrayList containing values for images

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchingGame frame = new MatchingGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MatchingGame() { //GUI Constructor
		if (System.getProperty("os.name").contains("Mac"))
			isMacOS = true;
		
		setTitle("A10 - Matching Game (Nintendo)");
		setIconImage(winIcon.getImage()); 
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 698);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchingGame = new JLabel("Matching Game");
		lblMatchingGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatchingGame.setForeground(Color.BLUE);
		lblMatchingGame.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblMatchingGame.setBackground(Color.WHITE);
		lblMatchingGame.setBounds(0, 0, 660, 41);
		contentPane.add(lblMatchingGame);

		JLabel versionLabel = new JLabel("v1.0.0");
		versionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		versionLabel.setFont(new Font("Tahoma", Font.ITALIC, 8));
		versionLabel.setBounds(10, 640, 29, 14);
		contentPane.add(versionLabel);

		JLabel nameLabel = new JLabel("Evan Shizas");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.ITALIC, 8));
		nameLabel.setBounds(602, 640, 48, 14);
		contentPane.add(nameLabel);

		dialogBox = new JTextField();
		dialogBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		dialogBox.setText("Press Play to start the game!");
		dialogBox.setBackground(Color.WHITE);
		dialogBox.setEditable(false);
		dialogBox.setBounds(10, 615, 640, 20);
		contentPane.add(dialogBox);
		dialogBox.setColumns(10);

		play = new JButton("Play");
		play.setBackground(Color.LIGHT_GRAY);
		play.setBounds(10, 586, 137, 23);
		contentPane.add(play);
		play.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playActionPerformed(evt);
			}
		});

		guessAgain = new JButton("Guess Again");
		guessAgain.setEnabled(false);
		guessAgain.setBackground(Color.LIGHT_GRAY);
		guessAgain.setBounds(157, 586, 137, 23);
		contentPane.add(guessAgain);
		guessAgain.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				guessAgainActionPerformed(evt);
			}
		});

		reset = new JButton("Reset");
		reset.setBackground(Color.YELLOW);
		reset.setBounds(366, 586, 137, 23);
		contentPane.add(reset);
		reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				resetActionPerformed(evt);
			}
		});

		exit = new JButton("Exit");
		exit.setBackground(Color.RED);
		exit.setBounds(513, 586, 137, 23);
		contentPane.add(exit);
		exit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitActionPerformed(evt);
			}
		});

		card1 = new JButton("");
		card1.setIcon(back);
		card1.setEnabled(false);
		card1.setBackground(Color.WHITE);
		card1.setBounds(10, 57, 120, 120);
		contentPane.add(card1);
		card1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card1ActionPerformed(evt);
			}
		});

		card2 = new JButton("");
		card2.setIcon(back);
		card2.setEnabled(false);
		card2.setBackground(Color.WHITE);
		card2.setBounds(140, 57, 120, 120);
		contentPane.add(card2);
		card2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card2ActionPerformed(evt);
			}
		});

		card3 = new JButton("");
		card3.setIcon(back);
		card3.setEnabled(false);
		card3.setBackground(Color.WHITE);
		card3.setBounds(270, 57, 120, 120);
		contentPane.add(card3);
		card3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card3ActionPerformed(evt);
			}
		});

		card4 = new JButton("");
		card4.setIcon(back);
		card4.setEnabled(false);
		card4.setBackground(Color.WHITE);
		card4.setBounds(400, 57, 120, 120);
		contentPane.add(card4);
		card4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card4ActionPerformed(evt);
			}
		});

		card5 = new JButton("");
		card5.setIcon(back);
		card5.setEnabled(false);
		card5.setBackground(Color.WHITE);
		card5.setBounds(530, 57, 120, 120);
		contentPane.add(card5);
		card5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card5ActionPerformed(evt);
			}
		});

		card6 = new JButton("");
		card6.setIcon(back);
		card6.setEnabled(false);
		card6.setBackground(Color.WHITE);
		card6.setBounds(10, 187, 120, 120);
		contentPane.add(card6);
		card6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card6ActionPerformed(evt);
			}
		});

		card7 = new JButton("");
		card7.setIcon(back);
		card7.setEnabled(false);
		card7.setBackground(Color.WHITE);
		card7.setBounds(140, 187, 120, 120);
		contentPane.add(card7);
		card7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card7ActionPerformed(evt);
			}
		});

		card8 = new JButton("");
		card8.setIcon(back);
		card8.setEnabled(false);
		card8.setBackground(Color.WHITE);
		card8.setBounds(270, 187, 120, 120);
		contentPane.add(card8);
		card8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card8ActionPerformed(evt);
			}
		});

		card9 = new JButton("");
		card9.setIcon(back);
		card9.setEnabled(false);
		card9.setBackground(Color.WHITE);
		card9.setBounds(400, 187, 120, 120);
		contentPane.add(card9);
		card9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card9ActionPerformed(evt);
			}
		});

		card10 = new JButton("");
		card10.setIcon(back);
		card10.setEnabled(false);
		card10.setBackground(Color.WHITE);
		card10.setBounds(530, 187, 120, 120);
		contentPane.add(card10);
		card10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card10ActionPerformed(evt);
			}
		});

		card11 = new JButton("");
		card11.setIcon(back);
		card11.setEnabled(false);
		card11.setBackground(Color.WHITE);
		card11.setBounds(10, 317, 120, 120);
		contentPane.add(card11);
		card11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card11ActionPerformed(evt);
			}
		});

		card12 = new JButton("");
		card12.setIcon(back);
		card12.setEnabled(false);
		card12.setBackground(Color.WHITE);
		card12.setBounds(140, 317, 120, 120);
		contentPane.add(card12);
		card12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card12ActionPerformed(evt);
			}
		});

		card13 = new JButton("");
		card13.setIcon(back);
		card13.setEnabled(false);
		card13.setBackground(Color.WHITE);
		card13.setBounds(270, 317, 120, 120);
		contentPane.add(card13);
		card13.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card13ActionPerformed(evt);
			}
		});

		card14 = new JButton("");
		card14.setIcon(back);
		card14.setEnabled(false);
		card14.setBackground(Color.WHITE);
		card14.setBounds(400, 317, 120, 120);
		contentPane.add(card14);
		card14.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card14ActionPerformed(evt);
			}
		});

		card15 = new JButton("");
		card15.setIcon(back);
		card15.setEnabled(false);
		card15.setBackground(Color.WHITE);
		card15.setBounds(530, 317, 120, 120);
		contentPane.add(card15);
		card15.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card15ActionPerformed(evt);
			}
		});

		card16 = new JButton("");
		card16.setIcon(back);
		card16.setEnabled(false);
		card16.setBackground(Color.WHITE);
		card16.setBounds(10, 447, 120, 120);
		contentPane.add(card16);
		card16.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card16ActionPerformed(evt);
			}
		});

		card17 = new JButton("");
		card17.setIcon(back);
		card17.setEnabled(false);
		card17.setBackground(Color.WHITE);
		card17.setBounds(140, 447, 120, 120);
		contentPane.add(card17);
		card17.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card17ActionPerformed(evt);
			}
		});

		card18 = new JButton("");
		card18.setIcon(back);
		card18.setEnabled(false);
		card18.setBackground(Color.WHITE);
		card18.setBounds(270, 447, 120, 120);
		contentPane.add(card18);
		card18.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card18ActionPerformed(evt);
			}
		});

		card19 = new JButton("");
		card19.setIcon(back);
		card19.setEnabled(false);
		card19.setBackground(Color.WHITE);
		card19.setBounds(400, 447, 120, 120);
		contentPane.add(card19);
		card19.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card19ActionPerformed(evt);
			}
		});

		card20 = new JButton("");
		card20.setIcon(back);
		card20.setEnabled(false);
		card20.setBackground(Color.WHITE);
		card20.setBounds(530, 447, 120, 120);
		contentPane.add(card20);
		card20.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				card20ActionPerformed(evt);
			}
		});
		
		if (!isMacOS) {
			JSeparator split1 = new JSeparator();
			split1.setForeground(Color.BLACK);
			split1.setBackground(Color.WHITE);
			split1.setBounds(10, 47, 640, 8);
			contentPane.add(split1);

			JSeparator split2 = new JSeparator();
			split2.setBackground(Color.WHITE);
			split2.setForeground(Color.BLACK);
			split2.setBounds(10, 578, 640, 8);
			contentPane.add(split2);
		}
	}

	private void exitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void resetActionPerformed(java.awt.event.ActionEvent evt) {
		int confirmOption = JOptionPane.showConfirmDialog (null, "All progress will be lost!\n\nWish to continue?","RESET WARNING", JOptionPane.YES_NO_OPTION);
		
		if (confirmOption == JOptionPane.YES_OPTION) {
			play.setEnabled(true);
			guessAgain.setEnabled(false);
			cardDisable();
			
			cardReset();
			
			gameFinished = false;
			cardOne = 0;
			cardTwo = 0;
			cardSelect = 0;
			
			dialogBox.setText("Press Play to start the game!");
		}
	}

	private void playActionPerformed(java.awt.event.ActionEvent evt) {
		gameFinished = false;
		cardOne = 0;
		cardTwo = 0;
		cardSelect = 0;
		
		play.setEnabled(false);
		guessAgain.setEnabled(true);
		cardEnable();
		cardReset();
		
		dialogBox.setText("");
		
		cards.clear();
		
		for (int i = 0; i < 20; i++) { //ArrayList initializer
			if (i == 0 || i == 1) {
				cards.add(1);
			}
			else if (i == 2 || i == 3) {
				cards.add(2);
			}
			else if (i == 4 || i == 5) {
				cards.add(3);
			}
			else if (i == 6 || i == 7) {
				cards.add(4);
			}
			else if (i == 8 || i == 9) {
				cards.add(5);
			}
			else if (i == 10 || i == 11) {
				cards.add(6);
			}
			else if (i == 12 || i == 13) {
				cards.add(7);
			}
			else if (i == 14 || i == 15) {
				cards.add(8);
			}
			else if (i == 16 || i == 17) {
				cards.add(9);
			}
			else if (i == 18 || i == 19) {
				cards.add(10);
			}
		}
		
		Collections.shuffle(cards, new Random()); //Randomizer
		
		if (allowDebug) {
			for (int i = 0; i < cards.size(); i++) {
				System.out.print(cards.get(i) + " ");
			}

			System.out.println();
		}
	}

	private void guessAgainActionPerformed(java.awt.event.ActionEvent evt) {
		tryAgain = true;
		secondCardSelect = false;
		cardEnable();
		cardImageLoad();
		cardSelect = cardOne + 1; //Reset first incorrect card selected
		cardImageLoad();
		tryAgain = false;
		dialogBox.setText("");
	}
	
	private void card1ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 1;
		cardMatchCheck();
	}
	
	private void card2ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 2;
		cardMatchCheck();
	}
	
	private void card3ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 3;
		cardMatchCheck();
	}
	
	private void card4ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 4;
		cardMatchCheck();
	}
	
	private void card5ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 5;
		cardMatchCheck();
	}
	
	private void card6ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 6;
		cardMatchCheck();
	}
	
	private void card7ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 7;
		cardMatchCheck();
	}
	
	private void card8ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 8;
		cardMatchCheck();
	}
	
	private void card9ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 9;
		cardMatchCheck();
	}
	
	private void card10ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 10;
		cardMatchCheck();
	}
	
	private void card11ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 11;
		cardMatchCheck();
	}
	
	private void card12ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 12;
		cardMatchCheck();
	}
	
	private void card13ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 13;
		cardMatchCheck();
	}
	
	private void card14ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 14;
		cardMatchCheck();
	}
	
	private void card15ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 15;
		cardMatchCheck();
	}
	
	private void card16ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 16;
		cardMatchCheck();
	}
	
	private void card17ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 17;
		cardMatchCheck();
	}
	
	private void card18ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 18;
		cardMatchCheck();
	}
	
	private void card19ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 19;
		cardMatchCheck();
	}
	
	private void card20ActionPerformed(java.awt.event.ActionEvent evt) {
		cardSelect = 20;
		cardMatchCheck();
	}
	
	public void cardMatchCheck() { //Card checker
		if (!gameFinished) {
			cardImageLoad();

			dialogBox.setText("");

			if (secondCardSelect) { //For when a second card is selected
				secondCardSelect = false;
				cardTwo = cardSelect - 1;

				if (cards.get(cardOne) == cards.get(cardTwo) && cards.get(cardTwo) != -1 && cardOne != cardTwo) { //Case of both cards matching
					cardMatch = true;
					cardImageLoad();
					cardSelect = cardOne + 1;
					cardImageLoad();
					cards.set(cardOne, -1);
					cards.set(cardTwo, -1);
					cardMatch = false;
					
					dialogBox.setText("Cards match! Keep it going!");

					for (int i = 0; i < cards.size(); i++) { //Checks if all cards are matched
						if (cards.get(i) != -1)
							break;

						if (cards.get(i) == -1 && i == cards.size() - 1) {
							gameFinished = true;
							gameComplete();
						}
					}
				}

				else if (cards.get(cardTwo) == -1 || cardOne == cardTwo) { //Case of matched card or same card being selected
					secondCardSelect = true;
					cardTwo = 0;
				}

				else { //No match
					dialogBox.setText("Cards do not match! Press Guess Again to give it another try!");
					cardDisable();
				}
			}

			else { //For when the first card is selected
				if (cards.get(cardSelect - 1) != -1) {
					secondCardSelect = true;
					cardOne = cardSelect - 1;
				}
			}
		}
		
		else {
			gameComplete();
		}
	}
	
	public void cardImageLoad() { //Image loader
		if (cardSelect == 1) {
			card1.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 2) {
			card2.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 3) {
			card3.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 4) {
			card4.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 5) {
			card5.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 6) {
			card6.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 7) {
			card7.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 8) {
			card8.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 9) {
			card9.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 10) {
			card10.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 11) {
			card11.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 12) {
			card12.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 13) {
			card13.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 14) {
			card14.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 15) {
			card15.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 16) {
			card16.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 17) {
			card17.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 18) {
			card18.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 19) {
			card19.setIcon(cardImageSelect());
		}
		
		else if (cardSelect == 20) {
			card20.setIcon(cardImageSelect());
		}
		
		else {
			System.out.println("ERROR");
		}
	}
	
	public ImageIcon cardImageSelect() { //Image selector based on array value, relative to button selected
		if (cards.get(cardSelect - 1) == 1 && !cardMatch && !tryAgain) {
			return donkeyKong;
		}

		else if (cards.get(cardSelect - 1) == 2 && !cardMatch && !tryAgain) {
			return link;
		}

		else if (cards.get(cardSelect - 1) == 3 && !cardMatch && !tryAgain) {
			return luigi;
		}

		else if (cards.get(cardSelect - 1) == 4 && !cardMatch && !tryAgain) {
			return mario;
		}

		else if (cards.get(cardSelect - 1) == 5 && !cardMatch && !tryAgain) {
			return marioKart;
		}

		else if (cards.get(cardSelect - 1) == 6 && !cardMatch && !tryAgain) {
			return nintendo;
		}

		else if (cards.get(cardSelect - 1) == 7 && !cardMatch && !tryAgain) {
			return nintendoSwitch;
		}

		else if (cards.get(cardSelect - 1) == 8 && !cardMatch && !tryAgain) {
			return pikachu;
		}

		else if (cards.get(cardSelect - 1) == 9 && !cardMatch && !tryAgain) {
			return smashOrb;
		}

		else if (cards.get(cardSelect - 1) == 10 && !cardMatch && !tryAgain) {
			return toad;
		}
		
		else if (cardMatch && !tryAgain || cards.get(cardSelect - 1) == -1) {
			return correct;
		}
		
		else {
			return back;
		}
	}
	
	public void gameComplete() {
		play.setEnabled(true);
		guessAgain.setEnabled(false);
		dialogBox.setText("Congratulations! Press Play to start a new game!");
	}
	
	public void cardEnable() {
		card1.setEnabled(true);
		card2.setEnabled(true);
		card3.setEnabled(true);
		card4.setEnabled(true);
		card5.setEnabled(true);
		card6.setEnabled(true);
		card7.setEnabled(true);
		card8.setEnabled(true);
		card9.setEnabled(true);
		card10.setEnabled(true);
		card11.setEnabled(true);
		card12.setEnabled(true);
		card13.setEnabled(true);
		card14.setEnabled(true);
		card15.setEnabled(true);
		card16.setEnabled(true);
		card17.setEnabled(true);
		card18.setEnabled(true);
		card19.setEnabled(true);
		card20.setEnabled(true);
	}
	
	public void cardDisable() {
		card1.setEnabled(false);
		card2.setEnabled(false);
		card3.setEnabled(false);
		card4.setEnabled(false);
		card5.setEnabled(false);
		card6.setEnabled(false);
		card7.setEnabled(false);
		card8.setEnabled(false);
		card9.setEnabled(false);
		card10.setEnabled(false);
		card11.setEnabled(false);
		card12.setEnabled(false);
		card13.setEnabled(false);
		card14.setEnabled(false);
		card15.setEnabled(false);
		card16.setEnabled(false);
		card17.setEnabled(false);
		card18.setEnabled(false);
		card19.setEnabled(false);
		card20.setEnabled(false);
	}
	
	public void cardReset() {
		card1.setIcon(back);
		card2.setIcon(back);
		card3.setIcon(back);
		card4.setIcon(back);
		card5.setIcon(back);
		card6.setIcon(back);
		card7.setIcon(back);
		card8.setIcon(back);
		card9.setIcon(back);
		card10.setIcon(back);
		card11.setIcon(back);
		card12.setIcon(back);
		card13.setIcon(back);
		card14.setIcon(back);
		card15.setIcon(back);
		card16.setIcon(back);
		card17.setIcon(back);
		card18.setIcon(back);
		card19.setIcon(back);
		card20.setIcon(back);
	}
}
