


import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.image.WritableRaster;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//implements ComponentListener
public class FireEmblemCharacterCreator extends JFrame implements ChangeListener, ItemListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage portrait;
	BufferedImage token;
	BufferedImage hair;
	BufferedImage hairb;
	BufferedImage face;
	BufferedImage armor;
	BufferedImage blankPortrait;
	BufferedImage blankToken;
	BufferedImage importedToken;
	
	CreatorComposant CCface;

	JLabel portraitPanel;
	JLabel tokenPanel;
	
	//TODO
	int width;
	int height;
	
	final String textRandomColours = "Random colours";
	final String textRandomPortrait = "Random portrait";
	final String textExport = "Export";

	Random rn = new Random();
	
	ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	ArrayList<JComboBox<String>> boxes = new ArrayList<JComboBox<String>>();
	
	Color skinColor = new Color(192,140,110,255);
	Color hairColor = new Color(64,50,25,255);
	Color metalColor= new Color(100,100,100,255);
	Color trimColor= new Color(247,173,82,255);
	Color clothColor= new Color(82,82,115,255);
	Color leatherColor= new Color(148,100,66,255);
	Color outlineColor = new Color(0,0,0,255);
	Color blankColor = new Color(0,0,0,0);
	
	Color redTextColor = new Color(150,0,0,255);
	Color greenTextColor = new Color(0,150,0,255);
	Color blueTextColor = new Color(0,0,150,255);
	
	int hairXOffsetVal=0;
	int hairYOffsetVal=0;
	int armorXOffsetVal=0;
	int armorYOffsetVal=0;
	
	File folder;
	File[] listOfFiles;
	ArrayList<String> hairs = new ArrayList<String>();
	ArrayList<String> faces = new ArrayList<String>();
	ArrayList<String> armors = new ArrayList<String>();
	ArrayList<String> tokens = new ArrayList<String>();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireEmblemCharacterCreator frame = new FireEmblemCharacterCreator();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedEncodingException 
	 */
	public FireEmblemCharacterCreator() throws UnsupportedEncodingException {
		

		
		String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		System.out.println("rawPath : " + rawPath);
		


		String path = URLDecoder.decode(rawPath, "UTF-8");
		path = path.substring(0, path.lastIndexOf("/") + 1);
		path = path.replaceAll("%20", " ");
		folder = new File(path + "resources");	
		listOfFiles  = folder.listFiles();
		
		
		
		
		hair = null;
		try {
		    hair = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		hairb = null;
		try {
		    hairb = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		CCface = null;
		try {
		    CCface = new CreatorComposant(ImageIO.read(new File(path + "resources/Empty.png")));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		armor = null;
		try {
		    armor = ImageIO.read(new File(path + "resources/Empty.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		portrait = null;
		try {
		    portrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}

		
		token = null;
		try {
		    token = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		blankPortrait = null;
		try {
		    blankPortrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		
		blankToken = null;
		try {
		    blankToken = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		importedToken = null;
		try {
		    importedToken = ImageIO.read(new File(path + "resources/BlankTok.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		
		
		
		//TODO
		int initialWidth = 1000;//932
		int initialHeight = 700;//610	
		int border = 5;//5 (useless ?)
		
		int xRedSlider = 200;//165
		int xGreenSlider = 480;//430
		int xBlueSlider = 760;//688
		
		int xRedText = 10;//10
		int xGreenText = 430;//390
		int xBlueText = 710;//655
		
		//System.out.println(xRedSlider - xRedText);
		//System.out.println(xGreenSlider - xGreenText);
		//System.out.println(xBlueSlider - xBlueText);
		
		int widthTextRed = 190;//124 or 131 or 145
		int widthTextGreen = 50;//46
		int widthTextBlue = 50;//46
		
		int widthTextOffset = 61;//46
		
		int widthSlider = 215;//215
		int xSpace = 15;//10 (between Slider's end and Text's start)
		
		String RandomColourText = "Random colours";
				
		
		
		
		//TODO
		
		
		setFont(new Font("Calibri", Font.BOLD, 12));
		setTitle("Fire Emblem Character Creator - Custom build");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setBounds(100, 100, initialWidth, initialHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(border, border, border, border));//setBorder() useless ?
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//RED(y) : 11 , 60 , 109, 158 , 256 => 49
		
		
		
		
		JLabel lblHairColorRed = new JLabel("Hair Color: Red");
		lblHairColorRed.setForeground(redTextColor);//NEW
		lblHairColorRed.setFont(new Font("Calibri", Font.BOLD, 13));
		lblHairColorRed.setBounds(xRedText, 11, widthTextRed, 21);
		contentPane.add(lblHairColorRed);
		
		JSlider hairRed = new JSlider();
		hairRed.setValue(64);
		hairRed.setMajorTickSpacing(64);
		hairRed.setPaintTicks(true);
		hairRed.setPaintLabels(true);
		hairRed.setMaximum(255);
		hairRed.setBounds(xRedSlider, 11, widthSlider, 38);
		contentPane.add(hairRed);
		
		JLabel lblGreen = new JLabel("Green");
		lblGreen.setForeground(greenTextColor);//NEW		
		lblGreen.setFont(new Font("Calibri", Font.BOLD, 13));
		lblGreen.setBounds(xGreenText, 14, 46, 21);
		contentPane.add(lblGreen);
		
		JSlider hairGreen = new JSlider();
		hairGreen.setPaintTicks(true);
		hairGreen.setPaintLabels(true);
		hairGreen.setMaximum(255);
		hairGreen.setMajorTickSpacing(64);
		hairGreen.setBounds(xGreenSlider, 11, widthSlider, 38);
		contentPane.add(hairGreen);
		
		
		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setForeground(blueTextColor);//NEW
		lblBlue.setForeground(blueTextColor);//NEW		
		lblBlue.setFont(new Font("Calibri", Font.BOLD, 13));
		lblBlue.setBounds(xBlueText, 14, 46, 21);
		contentPane.add(lblBlue);
		
		JSlider hairBlue = new JSlider();
		hairBlue.setValue(24);
		hairBlue.setPaintTicks(true);
		hairBlue.setPaintLabels(true);
		hairBlue.setMaximum(255);
		hairBlue.setMajorTickSpacing(64);
		hairBlue.setBounds(xBlueSlider, 11, widthSlider, 38);
		contentPane.add(hairBlue);
		
		JSlider skinBlue = new JSlider();
		skinBlue.setValue(110);
		skinBlue.setPaintTicks(true);
		skinBlue.setPaintLabels(true);
		skinBlue.setMaximum(255);
		skinBlue.setMajorTickSpacing(64);
		skinBlue.setBounds(xBlueSlider, 60, widthSlider, 38);
		skinBlue.setName("skin");//NEW
		contentPane.add(skinBlue);
		
		JLabel label = new JLabel("Blue");
		label.setForeground(blueTextColor);//NEW
		label.setFont(new Font("Calibri", Font.BOLD, 13));
		label.setBounds(xBlueText, 63, 46, 21);
		contentPane.add(label);
		
		JSlider skinGreen = new JSlider();
		skinGreen.setValue(140);
		skinGreen.setPaintTicks(true);
		skinGreen.setPaintLabels(true);
		skinGreen.setMaximum(255);
		skinGreen.setMajorTickSpacing(64);
		skinGreen.setBounds(xGreenSlider, 60, widthSlider, 38);
		skinGreen.setName("skin");//NEW
		contentPane.add(skinGreen);
		
		JLabel label_1 = new JLabel("Green");
		label_1.setForeground(greenTextColor);//NEW
		label_1.setFont(new Font("Calibri", Font.BOLD, 13));
		label_1.setBounds(xGreenText, 63, 46, 21);
		contentPane.add(label_1);
		
		JSlider skinRed = new JSlider();
		skinRed.setValue(192);
		skinRed.setPaintTicks(true);
		skinRed.setPaintLabels(true);
		skinRed.setMaximum(255);
		skinRed.setMajorTickSpacing(64);
		skinRed.setBounds(xRedSlider, 60, widthSlider, 38);
		skinRed.setName("skin");//NEW
		contentPane.add(skinRed);
		
		JLabel lblSkinColorRed = new JLabel("Skin Color: Red");
		lblSkinColorRed.setForeground(redTextColor);//NEW
		lblSkinColorRed.setFont(new Font("Calibri", Font.BOLD, 13));
		lblSkinColorRed.setBounds(xRedText, 60, widthTextRed, 21);
		contentPane.add(lblSkinColorRed);
		
		JSlider metalBlue = new JSlider();
		metalBlue.setValue(100);
		metalBlue.setPaintTicks(true);
		metalBlue.setPaintLabels(true);
		metalBlue.setMaximum(255);
		metalBlue.setMajorTickSpacing(64);
		metalBlue.setBounds(xBlueSlider, 109, widthSlider, 38);
		contentPane.add(metalBlue);
		
		JLabel label_3 = new JLabel("Blue");
		label_3.setForeground(blueTextColor);//NEW
		label_3.setFont(new Font("Calibri", Font.BOLD, 13));
		label_3.setBounds(xBlueText, 112, 46, 21);
		contentPane.add(label_3);
		
		JSlider metalGreen = new JSlider();
		metalGreen.setValue(100);
		metalGreen.setPaintTicks(true);
		metalGreen.setPaintLabels(true);
		metalGreen.setMaximum(255);
		metalGreen.setMajorTickSpacing(64);
		metalGreen.setBounds(xGreenSlider, 109, widthSlider, 38);
		contentPane.add(metalGreen);
		
		JLabel label_4 = new JLabel("Green");
		label_4.setForeground(greenTextColor);//NEW
		label_4.setFont(new Font("Calibri", Font.BOLD, 13));
		label_4.setBounds(xGreenText, 112, 46, 21);
		contentPane.add(label_4);
		
		JSlider metalRed = new JSlider();
		metalRed.setValue(100);
		metalRed.setPaintTicks(true);
		metalRed.setPaintLabels(true);
		metalRed.setMaximum(255);
		metalRed.setMajorTickSpacing(64);
		metalRed.setBounds(xRedSlider, 109, widthSlider, 38);
		contentPane.add(metalRed);
		
		JLabel lblArmorMetalColor = new JLabel("Armor Metal Color: Red");
		lblArmorMetalColor.setForeground(redTextColor);//NEW
		lblArmorMetalColor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblArmorMetalColor.setBounds(xRedText, 109, widthTextRed, 21);
		contentPane.add(lblArmorMetalColor);
		
		JSlider trimBlue = new JSlider();
		trimBlue.setValue(82);
		trimBlue.setPaintTicks(true);
		trimBlue.setPaintLabels(true);
		trimBlue.setMaximum(255);
		trimBlue.setMajorTickSpacing(64);
		trimBlue.setBounds(xBlueSlider, 158, widthSlider, 38);
		contentPane.add(trimBlue);
		
		JLabel label_6 = new JLabel("Blue");
		label_6.setForeground(blueTextColor);//NEW
		label_6.setFont(new Font("Calibri", Font.BOLD, 13));
		label_6.setBounds(xBlueText, 161, 46, 21);
		contentPane.add(label_6);
		
		JSlider trimGreen = new JSlider();
		trimGreen.setValue(173);
		trimGreen.setPaintTicks(true);
		trimGreen.setPaintLabels(true);
		trimGreen.setMaximum(255);
		trimGreen.setMajorTickSpacing(64);
		trimGreen.setBounds(xGreenSlider, 158, widthSlider, 38);
		contentPane.add(trimGreen);
		
		JLabel label_7 = new JLabel("Green");
		label_7.setForeground(greenTextColor);//NEW
		label_7.setFont(new Font("Calibri", Font.BOLD, 13));
		label_7.setBounds(xGreenText, 161, 46, 21);
		contentPane.add(label_7);
		
		JSlider trimRed = new JSlider();
		trimRed.setValue(247);
		trimRed.setPaintTicks(true);
		trimRed.setPaintLabels(true);
		trimRed.setMaximum(255);
		trimRed.setMajorTickSpacing(64);
		trimRed.setBounds(xRedSlider, 158, widthSlider, 38);
		contentPane.add(trimRed);
		
		JLabel lblMetalTrimColor = new JLabel("Armor Trim Color: Red");
		lblMetalTrimColor.setForeground(redTextColor);//NEW
		lblMetalTrimColor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblMetalTrimColor.setBounds(xRedText, 158, widthTextRed, 21);
		contentPane.add(lblMetalTrimColor);
		
		JSlider clothBlue = new JSlider();
		clothBlue.setValue(115);
		clothBlue.setPaintTicks(true);
		clothBlue.setPaintLabels(true);
		clothBlue.setMaximum(255);
		clothBlue.setMajorTickSpacing(64);
		clothBlue.setBounds(xBlueSlider, 207, widthSlider, 38);
		contentPane.add(clothBlue);
		
		JLabel label_9 = new JLabel("Blue");
		label_9.setForeground(blueTextColor);//NEW
		label_9.setFont(new Font("Calibri", Font.BOLD, 13));
		label_9.setBounds(xBlueText, 210, 46, 21);
		contentPane.add(label_9);
		
		JSlider clothGreen = new JSlider();
		clothGreen.setValue(82);
		clothGreen.setPaintTicks(true);
		clothGreen.setPaintLabels(true);
		clothGreen.setMaximum(255);
		clothGreen.setMajorTickSpacing(64);
		clothGreen.setBounds(xGreenSlider, 207, widthSlider, 38);
		contentPane.add(clothGreen);
		
		JLabel label_10 = new JLabel("Green");
		label_10.setForeground(greenTextColor);//NEW
		label_10.setFont(new Font("Calibri", Font.BOLD, 13));
		label_10.setBounds(xGreenText, 210, 46, 21);
		contentPane.add(label_10);
		
		JSlider clothRed = new JSlider();
		clothRed.setValue(82);
		clothRed.setPaintTicks(true);
		clothRed.setPaintLabels(true);
		clothRed.setMaximum(255);
		clothRed.setMajorTickSpacing(64);
		clothRed.setBounds(xRedSlider, 207, widthSlider, 38);
		contentPane.add(clothRed);
		
		JLabel lblArmorClothColor = new JLabel("Armor Cloth Color: Red");
		lblArmorClothColor.setForeground(redTextColor);//NEW
		lblArmorClothColor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblArmorClothColor.setBounds(xRedText, 207, widthTextRed, 21);//TODO
		contentPane.add(lblArmorClothColor);
		
		JSlider leatherBlue = new JSlider();
		leatherBlue.setValue(66);
		leatherBlue.setPaintTicks(true);
		leatherBlue.setPaintLabels(true);
		leatherBlue.setMaximum(255);
		leatherBlue.setMajorTickSpacing(64);
		leatherBlue.setBounds(xBlueSlider, 256, widthSlider, 38);
		contentPane.add(leatherBlue);
		
		JLabel label_12 = new JLabel("Blue");
		label_12.setForeground(blueTextColor);//NEW
		label_12.setFont(new Font("Calibri", Font.BOLD, 13));
		label_12.setBounds(xBlueText, 259, 46, 21);
		contentPane.add(label_12);
		
		JSlider leatherGreen = new JSlider();
		leatherGreen.setValue(100);
		leatherGreen.setPaintTicks(true);
		leatherGreen.setPaintLabels(true);
		leatherGreen.setMaximum(255);
		leatherGreen.setMajorTickSpacing(64);
		leatherGreen.setBounds(xGreenSlider, 256, widthSlider, 38);
		contentPane.add(leatherGreen);
		
		JLabel label_13 = new JLabel("Green");
		label_13.setForeground(greenTextColor);//NEW
		label_13.setFont(new Font("Calibri", Font.BOLD, 13));
		label_13.setBounds(xGreenText, 259, 46, 21);
		contentPane.add(label_13);
		
		JSlider leatherRed = new JSlider();
		leatherRed.setValue(148);
		leatherRed.setPaintTicks(true);
		leatherRed.setPaintLabels(true);
		leatherRed.setMaximum(255);
		leatherRed.setMajorTickSpacing(64);
		leatherRed.setBounds(xRedSlider, 256, widthSlider, 38);
		contentPane.add(leatherRed);
		
		JLabel lblArmorLeatherColor = new JLabel("Armor Leather Color: Red");
		lblArmorLeatherColor.setForeground(redTextColor);//NEW
		lblArmorLeatherColor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblArmorLeatherColor.setBounds(xRedText, 256, widthTextRed, 21);
		contentPane.add(lblArmorLeatherColor);
		

		
		portraitPanel = new JLabel();
		portraitPanel.setIcon(new ImageIcon(path + "resources/BlankPortrait.png"));
		portraitPanel.setBounds(22, 331, 192, 192);
		contentPane.add(portraitPanel);
		
		
		tokenPanel= new JLabel();
		tokenPanel.setIcon(new ImageIcon(path + "resources/BlankTok.png"));
		tokenPanel.setBounds(224, 403, 128, 128);
		
		
		contentPane.add(tokenPanel);
		
		JLabel lblHair = new JLabel("Hair");
		lblHair.setFont(new Font("Calibri", Font.BOLD, 13));
		lblHair.setBounds(373, 320, 46, 21);
		contentPane.add(lblHair);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setFont(new Font("Calibri", Font.BOLD, 13));
		lblFace.setBounds(557, 323, 46, 21);
		contentPane.add(lblFace);
		
		JLabel lblArmor = new JLabel("Armor");
		lblArmor.setFont(new Font("Calibri", Font.BOLD, 13));
		lblArmor.setBounds(734, 323, 46, 21);
		contentPane.add(lblArmor);
		
		hairs.add("Empty.png");
		faces.add("Empty.png");
		armors.add("Empty.png");
		tokens.add("Emptytok.png");
		
		System.out.println(listOfFiles.length);
		for (int i = 0; i< listOfFiles.length; i++){
			String filename = listOfFiles[i].getName();
			if (filename.contains("Hair.png")){
				hairs.add(filename);
			}
			else if(filename.contains("Face.png")){
				faces.add(filename);
			}
			else if(filename.contains("Armor.png")){
				armors.add(filename);
			}
			else if(filename.contains("Token.png")){
				tokens.add(filename);
			}
		}
		
		JComboBox<String> comboBox = new JComboBox<String>(hairs.toArray(new String[hairs.size()]));
		comboBox.setBounds(373, 352, 131, 20);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>(faces.toArray(new String[faces.size()]));
		comboBox_1.setBounds(557, 355, 131, 20);
		contentPane.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>(armors.toArray(new String[armors.size()]));
		comboBox_2.setBounds(734, 352, 131, 20);
		contentPane.add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>(tokens.toArray(new String[tokens.size()]));
		comboBox_3.setBounds(224, 352, 120, 20);
		comboBox_3.setMaximumRowCount(3);
		contentPane.add(comboBox_3);
		
		//NEW
		comboBox.setName("portrait");
		comboBox_1.setName("portrait");
		comboBox_2.setName("portrait");
		
		
		JLabel lblToken = new JLabel("Token");
		lblToken.setFont(new Font("Calibri", Font.BOLD, 13));
		lblToken.setBounds(224, 323, 46, 21);
		contentPane.add(lblToken);
		
		JSlider hairXOffset = new JSlider();
		hairXOffset.setValue(0);
		hairXOffset.setPaintTicks(true);
		hairXOffset.setPaintLabels(true);
		hairXOffset.setMajorTickSpacing(10);
		hairXOffset.setMaximum(20);
		hairXOffset.setMinimum(-20);
		hairXOffset.setBounds(373, 403, 151, 38);
		hairXOffset.setName("offset");//NEW
		contentPane.add(hairXOffset);
		
		JLabel lblXOffset = new JLabel("Y Offset");
		lblXOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblXOffset.setBounds(373, 383, widthTextOffset, 21);//(373, 383, 46, 21)//TODO
		contentPane.add(lblXOffset);
		
		JLabel lblYOffset = new JLabel("X Offset");
		lblYOffset.setFont(new Font("Calibri", Font.BOLD, 13));
		lblYOffset.setBounds(373, 452, widthTextOffset, 21);
		contentPane.add(lblYOffset);
		
		JSlider hairYOffset = new JSlider();
		hairYOffset.setValue(0);
		hairYOffset.setPaintTicks(true);
		hairYOffset.setPaintLabels(true);
		hairYOffset.setMinimum(-20);
		hairYOffset.setMaximum(20);
		hairYOffset.setMajorTickSpacing(10);
		hairYOffset.setBounds(373, 466, 151, 38);
		hairYOffset.setName("offset");//NEW
		contentPane.add(hairYOffset);
		
		JLabel label_2 = new JLabel("Y Offset");
		label_2.setFont(new Font("Calibri", Font.BOLD, 13));
		label_2.setBounds(557, 386, widthTextOffset, 21);
		contentPane.add(label_2);
		
		JSlider faceXOffset = new JSlider();
		faceXOffset.setValue(0);
		faceXOffset.setPaintTicks(true);
		faceXOffset.setPaintLabels(true);
		faceXOffset.setMinimum(-20);
		faceXOffset.setMaximum(20);
		faceXOffset.setMajorTickSpacing(10);
		faceXOffset.setBounds(557, 406, 151, 38);
		faceXOffset.setName("offset");//NEW
		contentPane.add(faceXOffset);
		
		JLabel label_5 = new JLabel("X Offset");
		label_5.setFont(new Font("Calibri", Font.BOLD, 13));
		label_5.setBounds(557, 455, widthTextOffset, 21);
		contentPane.add(label_5);
		
		JSlider faceYOffset = new JSlider();
		faceYOffset.setValue(0);
		faceYOffset.setPaintTicks(true);
		faceYOffset.setPaintLabels(true);
		faceYOffset.setMinimum(-20);
		faceYOffset.setMaximum(20);
		faceYOffset.setMajorTickSpacing(10);
		faceYOffset.setBounds(557, 469, 151, 38);
		faceYOffset.setName("offset");//NEW
		contentPane.add(faceYOffset);
		
		JLabel label_8 = new JLabel("Y Offset");
		label_8.setFont(new Font("Calibri", Font.BOLD, 13));
		label_8.setBounds(734, 383, widthTextOffset, 21);
		contentPane.add(label_8);
		
		JSlider armorXOffset = new JSlider();
		armorXOffset.setValue(0);
		armorXOffset.setPaintTicks(true);
		armorXOffset.setPaintLabels(true);
		armorXOffset.setMinimum(-20);
		armorXOffset.setMaximum(20);
		armorXOffset.setMajorTickSpacing(10);
		armorXOffset.setBounds(734, 403, 151, 38);
		armorXOffset.setName("offset");//NEW
		contentPane.add(armorXOffset);
		
		JLabel label_11 = new JLabel("X Offset");
		label_11.setFont(new Font("Calibri", Font.BOLD, 13));
		label_11.setBounds(734, 452, widthTextOffset, 21);
		contentPane.add(label_11);
		
		JSlider armorYOffset = new JSlider();
		armorYOffset.setValue(0);
		armorYOffset.setPaintTicks(true);
		armorYOffset.setPaintLabels(true);
		armorYOffset.setMinimum(-20);
		armorYOffset.setMaximum(20);
		armorYOffset.setMajorTickSpacing(10);
		armorYOffset.setBounds(734, 466, 151, 38);
		armorYOffset.setName("offset");//NEW
		contentPane.add(armorYOffset);
		
		JButton btnNewButton = new JButton(textExport);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.setBounds(400, 528, 113, 38);
		contentPane.add(btnNewButton);
		
		//TODO
		JButton btnRandomColour = new JButton(textRandomColours);
		btnRandomColour.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomColour.setBounds(22, 580, 200, 40);
		contentPane.add(btnRandomColour);
		
		//TODO
		JButton btnRandomPortrait = new JButton(textRandomPortrait);
		btnRandomPortrait.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRandomPortrait.setBounds(22, 530, 200, 40);//22, 331
		contentPane.add(btnRandomPortrait);
		

		
		
		hairRed.addChangeListener(this);
		hairGreen.addChangeListener(this);
		hairBlue.addChangeListener(this);
		skinRed.addChangeListener(this);
		skinGreen.addChangeListener(this);
		skinBlue.addChangeListener(this);
		metalRed.addChangeListener(this);
		metalGreen.addChangeListener(this);
		metalBlue.addChangeListener(this);
		trimRed.addChangeListener(this);
		trimGreen.addChangeListener(this);
		trimBlue.addChangeListener(this);
		clothRed.addChangeListener(this);
		clothGreen.addChangeListener(this);
		clothBlue.addChangeListener(this);
		leatherRed.addChangeListener(this);
		leatherGreen.addChangeListener(this);
		leatherBlue.addChangeListener(this);
		hairXOffset.addChangeListener(this);
		hairYOffset.addChangeListener(this);
		faceXOffset.addChangeListener(this);
		faceYOffset.addChangeListener(this);
		armorXOffset.addChangeListener(this);
		armorYOffset.addChangeListener(this);
		
		
		
		sliders.add(hairRed);
		sliders.add(hairGreen);
		sliders.add(hairBlue);
		sliders.add(skinRed);
		sliders.add(skinGreen);
		sliders.add(skinBlue);
		sliders.add(metalRed);
		sliders.add(metalGreen);
		sliders.add(metalBlue);
		sliders.add(trimRed);
		sliders.add(trimGreen);
		sliders.add(trimBlue);
		sliders.add(clothRed);
		sliders.add(clothGreen);
		sliders.add(clothBlue);
		sliders.add(leatherRed);
		sliders.add(leatherGreen);
		sliders.add(leatherBlue);
		sliders.add(hairXOffset);
		sliders.add(hairYOffset);
		sliders.add(faceXOffset);
		sliders.add(faceYOffset);
		sliders.add(armorXOffset);
		sliders.add(armorYOffset);
		
		comboBox.addItemListener(this);
		comboBox_1.addItemListener(this);
		comboBox_2.addItemListener(this);
		comboBox_3.addItemListener(this);
		
		boxes.add(comboBox);
		boxes.add(comboBox_1);
		boxes.add(comboBox_2);
		boxes.add(comboBox_3);
		
		btnNewButton.addActionListener(this);
		btnRandomColour.addActionListener(this);
		btnRandomPortrait.addActionListener(this);
		
		

	}
	
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	

	void pixelIteration(CreatorComposant cc){
		Color pixel = null;
		Color newPixel = null;
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-cc.YOffset<0 || i-cc.YOffset>95) continue;
				if (j+cc.XOffset<0 || j+cc.XOffset>95)continue;
				pixel = new Color(cc.BI.getRGB(i-cc.YOffset, j+cc.XOffset),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
	}

	void drawImages(){
		portrait = deepCopy(blankPortrait);
		token = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Color pixel = null;
		Color newPixel = null;
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-hairYOffsetVal <0 || i- hairYOffsetVal>95) continue;
				if (j+hairXOffsetVal<0 || j+ hairXOffsetVal>95)continue;
				pixel = new Color(hairb.getRGB(i-hairYOffsetVal, j+hairXOffsetVal),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-armorYOffsetVal <0 || i- armorYOffsetVal>95) continue;
				if (j+armorXOffsetVal<0 || j+ armorXOffsetVal>95)continue;
				pixel = new Color(armor.getRGB(i-armorYOffsetVal, j+armorXOffsetVal),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}

		pixelIteration(CCface);

		for(int i = 0; i<96; i++){
			for(int j = 0; j<96; j++){
				if (i-hairYOffsetVal <0 || i- hairYOffsetVal>95) continue;
				if (j+hairXOffsetVal<0 || j+ hairXOffsetVal>95)continue;
				pixel = new Color(hair.getRGB(i-hairYOffsetVal, j+hairXOffsetVal),true);
				newPixel = pixelParser(pixel);
				if(pixel.getAlpha()==0){
					continue;
				}
				portrait.setRGB(i*2, j*2, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2, newPixel.getRGB());
				portrait.setRGB(i*2, j*2+1, newPixel.getRGB());
				portrait.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		
		for(int i = 0; i<64; i++){
			for(int j = 0; j<64; j++){
				pixel = new Color(importedToken.getRGB(i, j),true);
				if(pixel.getAlpha()==0){
					continue;
				}
				newPixel = pixelParser(pixel);
				token.setRGB(i*2, j*2, newPixel.getRGB());
				token.setRGB(i*2+1, j*2, newPixel.getRGB());
				token.setRGB(i*2, j*2+1, newPixel.getRGB());
				token.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
		
	}
	
	Color pixelParser(Color pixel){
		Color newPixel = null;
		//double check this line 
		if(pixel.getAlpha() == 0){
			newPixel = blankColor;
			return newPixel;
		}
		int redIndex = pixel.getRed()/10;
		//System.out.println(redIndex);
		switch(redIndex){
		case 0: newPixel = outlineColor;
				break;
		case 1: newPixel = hairColor.brighter();
				break;
		case 2: newPixel = hairColor;
				break;
		case 3: newPixel = hairColor.darker();
				break;
		case 4: newPixel = skinColor.brighter();
				break;
		case 5: newPixel = skinColor;
				break;
		case 6: newPixel = skinColor.darker();
				break;
		case 7: newPixel = skinColor.darker().darker();
				break;
		case 8: newPixel = skinColor.darker().darker().darker();
				break;
		case 9: newPixel = metalColor.brighter();
				break;
		case 10: newPixel = metalColor;
				break;
		case 11: newPixel = metalColor.darker();
				break;
		case 12: newPixel = trimColor.brighter();
				break;
		case 13: newPixel = trimColor;
				break;
		case 14: newPixel = trimColor.darker();
				break;
		case 15: newPixel = clothColor.brighter();
				break;
		case 16: newPixel = clothColor;
				break;
		case 17: newPixel = clothColor.darker();
				break;
		case 18: newPixel = leatherColor.brighter();
				break;
		case 19: newPixel = leatherColor;
				break;
		case 20: newPixel = leatherColor.darker();
				break;
		default: newPixel = Color.WHITE;
		}
		return newPixel;
	}
	
	public void stateChanged(ChangeEvent e){
		JSlider src = (JSlider) e.getSource();
		int index = sliders.indexOf(src);
		int val = src.getValue();
		int red;
		int blue;
		int green;
		//System.out.println(index);
		switch(index){
		case 0:
			green = hairColor.getGreen();
			blue = hairColor.getBlue();
			hairColor = new Color(val,green,blue);
			break;
		case 1:
			red = hairColor.getRed();
			blue = hairColor.getBlue();
			hairColor = new Color(red,val,blue);
			break;
		case 2:
			red = hairColor.getRed();
			green = hairColor.getGreen();
			hairColor = new Color(red, green,val);
			break;
		case 3:
			green = skinColor.getGreen();
			blue = skinColor.getBlue();
			skinColor = new Color(val, green,blue);
			break;
		case 4:
			red = skinColor.getRed();
			blue = skinColor.getBlue();
			skinColor = new Color(red,val,blue);
			break;
		case 5:
			red = skinColor.getRed();
			green = skinColor.getGreen();
			skinColor = new Color(red,green,val);
			break;
		case 6:
			green = metalColor.getGreen();
			blue = metalColor.getBlue();
			metalColor = new Color(val,green,blue);
			break;
		case 7:
			red = metalColor.getRed();
			blue = metalColor.getBlue();
			metalColor = new Color(red,val,blue);
			break;
		case 8:
			red = metalColor.getRed();
			green = metalColor.getGreen();
			metalColor = new Color(red, green, val);
			break;
		case 9:
			green = trimColor.getGreen();
			blue = trimColor.getBlue();
			trimColor = new Color(val,green,blue);
			break;
		case 10:
			red = trimColor.getRed();
			blue = trimColor.getBlue();
			trimColor = new Color (red,val,blue);
			break;
		case 11:
			red = trimColor.getRed();
			green = trimColor.getGreen();
			trimColor = new Color(red,green,val);
			break;
		case 12:
			green = clothColor.getGreen();
			blue = clothColor.getBlue();
			clothColor = new Color(val,green,blue);
			break;
		case 13:
			red = clothColor.getRed();
			blue = clothColor.getBlue();
			clothColor = new Color(red,val,blue);
			break;
		case 14:
			red = clothColor.getRed();
			green = clothColor.getGreen();
			clothColor = new Color(red,green,val);
			break;
		case 15:
			green = leatherColor.getGreen();
			blue = leatherColor.getBlue();
			leatherColor = new Color(val,green,blue);
			break;
		case 16:
			red = leatherColor.getRed();
			blue = leatherColor.getBlue();
			leatherColor = new Color(red,val,blue);
			break;
		case 17:
			red = leatherColor.getRed();
			green = leatherColor.getGreen();
			leatherColor = new Color(red,green,val);
			break;
		case 18:
			hairXOffsetVal = val;
			break;
		case 19:
			hairYOffsetVal = val;
			break;
		case 20:
			CCface.XOffset = val;
			break;
		case 21:
			CCface.YOffset = val;
			break;
		case 22:
			armorXOffsetVal = val;
			break;
		case 23:
			armorYOffsetVal = val;
			break;
		default:
			//System.out.println("Switch statement overran");
		}
		//System.out.println(src.getName() + " Val: " + val);// src.getName = null
		drawImages();
		
		portraitPanel.setIcon(new ImageIcon(portrait));
		portraitPanel.repaint();
		tokenPanel.setIcon(new ImageIcon(token));
		tokenPanel.repaint();
		
	}
	

	public void itemStateChanged(ItemEvent event){
		try {
			String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String path = URLDecoder.decode(rawPath, "UTF-8");
			path = path.substring(0, path.lastIndexOf("/") + 1);
			path = path.replaceAll("%20", " ");
			
			JComboBox<String> src = (JComboBox<String>)event.getSource();//unsafe
			String fileName = (String)src.getSelectedItem();
			int menuNumber = boxes.indexOf(src);
			switch(menuNumber){
			case 0:
				try {
				    hair = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				try {
					String secondFileName = fileName.substring(0, fileName.length()-4);
				    hairb = ImageIO.read(new File(path + "resources/" + secondFileName + "b.png"));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				break;
			case 1:
				try {
				    CCface.BI = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				break;
			case 2:
				try {
				    armor = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				break;
			case 3:
				try {
				    importedToken = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				break;
			default:
				//System.out.println("File Swith OverRun");
			}
			//System.out.println(src.getName() + " FileName: " + fileName);// src.getName = null
			drawImages();
			
			
		
			portraitPanel.setIcon(new ImageIcon(portrait));
			portraitPanel.repaint();
			tokenPanel.setIcon(new ImageIcon(token));
			tokenPanel.repaint();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void actionPerformed(ActionEvent event){

		String str = event.getActionCommand();
		System.out.println(">> " + str);
		switch(str) {

			case textExport:
				try{	
		
					String rawPath = FireEmblemCharacterCreator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
					String path = URLDecoder.decode(rawPath, "UTF-8");
					path = path.substring(0, path.lastIndexOf("/") + 1);
					path = path.replaceAll("%20", " ");
					File portraitOutputFile = new File(path + "output.png");
					try{		
						ImageIO.write(portrait, "PNG", portraitOutputFile);
						}
					catch(IOException ex){
						System.out.println(ex);
					};
					
					File tokenOutputFile = new File(path + "outputToken.png");
				
						ImageIO.write(token, "PNG", tokenOutputFile);
					}
				catch(IOException ex){
					System.out.println(ex);
				};
				break;
				
			
			case textRandomColours://NEW
				//System.out.println("textRandomColours");
			{
				String sliderName;
				
				int max ;
				int min ;
				int n;
				int i;
				int randomNum;
				
				
				for(JSlider slider : sliders) {
					
					sliderName = slider.getName();
					
					if(sliderName==null) {//colours but not offset or skin
						
						max = slider.getMaximum();
						min = slider.getMinimum();
						
						n = max - min + 1;
						i = Math.abs(rn.nextInt() % n);
						randomNum =  min + i;
						
						slider.setValue(randomNum);
						
					}	
				}	
				
			}
				break;
			
				
				
			case textRandomPortrait:
				//System.out.println("textRandomPortrait");
				//TODO
			{
				
				
				String boxName;
				int n;
				int randomNum;
				
				
				for(JComboBox<String> box : boxes) {
					
					boxName = box.getName();
					
					if(boxName!=null) {//portraits and not token
							
						n = box.getItemCount() ;						
						randomNum = Math.abs(rn.nextInt() % n);
						
						System.out.println(randomNum + "/" + n);
						box.setSelectedIndex(randomNum);
						
					}	
				}
			}
				break;
			
			
			
			
			}
		}

	
	

	
}


