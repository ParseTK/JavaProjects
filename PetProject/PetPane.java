package petProj;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PetPane extends JFrame {
	
	private Pet p = new Pet("Fuzzy");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Timer timer;
	private int seconds = 180;
	private TextField textLifeTimeField;
	private TextField textFieldHunger;
	private TextField textFieldMood;
	private TextField textFieldSkill;
	private JLabel imageLabel;
	private ImageIcon notAliveIcon;
	private ImageIcon superFuzzIcon;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetPane frame = new PetPane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PetPane() {
		super("My Pet Monster");
		
		
		setResizable(true);
		setFont(new Font("Agency FB", Font.BOLD, 12));
		setForeground(new Color(102, 153, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 480, 478);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		// Progress bars
		JProgressBar feedProgressBar = new JProgressBar();
		feedProgressBar.setMaximum(200);
		feedProgressBar.setForeground(new Color(0, 0, 153));
		
		
		JProgressBar exerciseProgressBar = new JProgressBar();
		exerciseProgressBar.setMaximum(200);
		exerciseProgressBar.setForeground(new Color(102, 0, 153));
		
		
		JProgressBar playProgressBar = new JProgressBar();
		playProgressBar.setMaximum(200);
		playProgressBar.setForeground(new Color(0, 102, 0));
		
		// Buttons
		JButton feedButton = new JButton("Apple");
		feedButton.setBackground(new Color(0, 0, 0));
		feedButton.setForeground(new Color(0, 102, 255));
		
		JButton superFeedButton = new JButton("Soup");
		superFeedButton.setForeground(new Color(102, 204, 255));
		superFeedButton.setBackground(new Color(0, 0, 0));
		
		JButton ultraFeedButton = new JButton("Chicken");
		ultraFeedButton.setForeground(new Color(0, 255, 255));
		ultraFeedButton.setBackground(new Color(0, 0, 0));		
		

		JButton exerciseButton = new JButton("Lift 25lbs");
		exerciseButton.setBackground(new Color(0, 0, 0));
		exerciseButton.setForeground(new Color(102, 51, 153));
		JButton superExercise = new JButton("Lift 50lbs");
		superExercise.setForeground(new Color(255, 102, 255));
		superExercise.setBackground(new Color(0, 0, 0));
		JButton extremeExercise = new JButton("Lift 100lbs");
		extremeExercise.setForeground(new Color(255, 204, 255));
		extremeExercise.setBackground(new Color(0, 0, 0));
		
		
		JButton playButton = new JButton("Play");
		playButton.setForeground(new Color(0, 153, 51));
		playButton.setBackground(new Color(0, 0, 0));
		JButton playDanceButton = new JButton("Dance");
		playDanceButton.setForeground(new Color(51, 204, 0));
		playDanceButton.setBackground(new Color(0, 0, 0));
		JButton playSingButton = new JButton("Sing");
		playSingButton.setForeground(new Color(51, 255, 0));
		playSingButton.setBackground(new Color(0, 0, 0));
		
		
		JButton lifeTimeButton = new JButton("Life Time");
		lifeTimeButton.setBackground(new Color(0, 0, 0));
		lifeTimeButton.setForeground(new Color(255, 0, 0));
		
		
		
		// Labels	
		ImageIcon imgFuzzFuzz =  new ImageIcon(getClass().getResource("Fuzz_Fuzz.jpg"));
		Image scaledImgFuzzFuzz = imgFuzzFuzz.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		imageLabel = new JLabel();
		imageLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		imageLabel.setBackground(new Color(255, 204, 204));
		imageLabel.setIcon(new ImageIcon(scaledImgFuzzFuzz));
		
		
		ImageIcon imgUnFuzzFuzz =  new ImageIcon(getClass().getResource("Fuzz_Fuzz_No_more.jpg"));
		Image scaledImgUnFuzzFuzz = imgUnFuzzFuzz.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		notAliveIcon = new ImageIcon(scaledImgUnFuzzFuzz);
		
		ImageIcon imgSuperFuzzFuzz = new ImageIcon(getClass().getResource("Fuzz_Fuzz_Super.jpg"));
		Image scaledImgFuzzFuzzSuper = imgSuperFuzzFuzz.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		superFuzzIcon = new ImageIcon(scaledImgFuzzFuzzSuper);
		
		JLabel monsterName = new JLabel("Name: " + p.getName());
		monsterName.setLabelFor(imageLabel);
		monsterName.setForeground(new Color(0, 0, 0));
		
		
		// Events
		feedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.feed();
				feedProgressBar.setValue(p.getHungry());
				seconds += 1;
			}
		});
		superFeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setHungry(p.getHungry() + 2);
				feedProgressBar.setValue(p.getHungry());
				seconds += 2;
			}
		});
		ultraFeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setHungry(p.getHungry() + 5);
				feedProgressBar.setValue(p.getHungry());
				seconds -= 7;
			}
		});
		
		
		exerciseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.jump();
				exerciseProgressBar.setValue(p.getSkillPoints());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 1);
				}
				seconds += 3;
			}	
		});
		superExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setSkillPoints(p.getSkillPoints() + 2);
				exerciseProgressBar.setValue(p.getSkillPoints());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 2);
				}
				seconds += 2;
			}	
		});
		extremeExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setSkillPoints(p.getSkillPoints() + 5);
				exerciseProgressBar.setValue(p.getSkillPoints());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 3);
				}
				seconds -= 7;
			}	
		});
		
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.play();
				playProgressBar.setValue(p.getMood());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 1);
				}
			}
		});
		playDanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setMood(p.getMood() + 2);
				playProgressBar.setValue(p.getMood());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 2);
				}
			}
		});
		playSingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setMood(p.getMood() + 5);
				playProgressBar.setValue(p.getMood());
				if (p.getHungry() >= 0) {
					p.setHungry(p.getHungry() - 3);
				}
			}
		});
		
		
		// Add to pane
		getContentPane().add(feedButton);
		contentPane.add(superFeedButton);
		contentPane.add(ultraFeedButton);
		textFieldHunger = new TextField();
		textFieldHunger.setEditable(false);
		contentPane.add(textFieldHunger);
		contentPane.add(feedProgressBar);


		
		getContentPane().add(exerciseButton);
		contentPane.add(superExercise);
		contentPane.add(extremeExercise);
		textFieldSkill = new TextField();
		textFieldSkill.setEditable(false);
		contentPane.add(textFieldSkill);
		contentPane.add(exerciseProgressBar);
		
		
		getContentPane().add(playButton);
		contentPane.add(playDanceButton);
		contentPane.add(playSingButton);
		textFieldMood = new TextField();
		textFieldMood.setEditable(false);
		contentPane.add(textFieldMood);
		contentPane.add(playProgressBar);
		
		
		getContentPane().add(lifeTimeButton);
		textLifeTimeField = new TextField();
		textLifeTimeField.setEditable(false);
		contentPane.add(textLifeTimeField);
		initTimer();
	
		
	
		getContentPane().add(imageLabel);



		contentPane.add(monsterName);
		

	} // end of constructor

	// Methods  --- Huge one method just for the sake of finishing. 
	public void initTimer() {
		ActionListener timerTick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// do something if work done timer stop
				if(p.getMood() >= 0) {
					p.setMood(p.getMood() - 1);
				}
				
				if(p.getSkillPoints() >= 0) {
					p.setSkillPoints(p.getSkillPoints() - 1);
				}
				
				seconds--;
				
				p.setHungry(p.getHungry() - 1);
				
				textLifeTimeField.setText(String.valueOf(seconds));
				textFieldHunger.setText(String.valueOf(p.getHungry()));
				textFieldMood.setText(String.valueOf(p.getMood()));
				textFieldSkill.setText(String.valueOf(p.getSkillPoints()));
				
				if (seconds <= 0) {
					timer.stop();
					textLifeTimeField.setText("You lose!");
					imageLabel.setIcon(notAliveIcon);
					for (Component c : contentPane.getComponents()) { 
						c.setEnabled(false); 
						}
					return;
				}
				
				if (p.getHungry() >= 100 &&
					p.getMood() >= 100 &&
					p.getSkillPoints() >= 100) {
					imageLabel.setIcon(superFuzzIcon);
				}
			}
		};
		timer = new Timer(1000,timerTick);
		timer.start();
	}

	
} // end of PetPane class


