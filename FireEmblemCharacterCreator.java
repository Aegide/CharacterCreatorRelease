


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

	//#region Tons of variables

	private static final long serialVersionUID = 1L;

	//drawImages
	BufferedImage portrait;		
	BufferedImage token;		
	BufferedImage blankPortrait;

	CreatorComposant CCimportedToken;
	CreatorComposant CChair;
	CreatorComposant CChairb;
	CreatorComposant CCface;
	CreatorComposant CCarmor;
	
	JLabel portraitPanel;
	JLabel tokenPanel;
	
	
	int width;
	int height;
	
	final String textRandomColours = "Random colours";
	final String textRandomPortrait = "Random portrait";
	final String textExport = "Export";

	Random rn = new Random();
	
	ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	ArrayList<JComboBox<String>> boxes = new ArrayList<JComboBox<String>>();
	
	ArrayList<CreatorSlider> CreatorSliders = new ArrayList<CreatorSlider>();//NEW

	Color skinColor = new Color(192,140,110,255);
	Color hairColor = new Color(64,50,25,255);
	Color metalColor = new Color(100,100,100,255);
	Color trimColor = new Color(247,173,82,255);
	Color clothColor = new Color(82,82,115,255);
	Color leatherColor = new Color(148,100,66,255);
	Color outlineColor = new Color(0,0,0,255);
	Color blankColor = new Color(0,0,0,0);
	
	Color redTextColor = new Color(150,0,0,255);
	Color greenTextColor = new Color(0,150,0,255);
	Color blueTextColor = new Color(0,0,150,255);

	File folder;
	File[] listOfFiles;
	ArrayList<String> hairs = new ArrayList<String>();
	ArrayList<String> faces = new ArrayList<String>();
	ArrayList<String> armors = new ArrayList<String>();
	ArrayList<String> tokens = new ArrayList<String>();

	private JPanel contentPane;

	//#endregion

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
		
		CChair = new CreatorComposant(path + "resources/Empty.png", 96);
		CChairb = new CreatorComposant(path + "resources/Empty.png", 96);
		CCface = new CreatorComposant(path + "resources/Empty.png", 96);
		CCarmor = new CreatorComposant(path + "resources/Empty.png", 96);
		CCimportedToken = new CreatorComposant(path + "resources/BlankTok.png", 64);

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
		
		int initialWidth = 1000;//932
		int initialHeight = 700;//610	
		int border = 5;//5 (useless ?)
		
		int xRedSlider = 200;
		int xGreenSlider = 480;
		int xBlueSlider = 760;

		int yHairElement = 11;
		int ySkinElement = 60;
		int yMetalElement = 109;
		int yTrimElement = 158;
		int yClothElement = 207;
		int yLeatherElement = 256;

		int labelIncrement = 3;
		
		int xRedText = 10;//10
		int xGreenText = 430;//390
		int xBlueText = 710;//655
		
		int widthTextRed = 190;//124 or 131 or 145
		int widthTextGreen = 46;//50
		int widthTextBlue = 46;//50
		int widthText = 46;

		int sizeText = 21;
		int sizeFont = 13;
		
		int widthTextOffset = 61;//46
		
		//int xSpace = 15;//10 (between Slider's end and Text's start)	
		//String RandomColourText = "Random colours";				
		
		setFont(new Font("Calibri", Font.BOLD, 12));
		setTitle("Fire Emblem Character Creator - Custom build");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, initialWidth, initialHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(border, border, border, border));//setBorder() useless ?
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		//TODO
		ArrayList<SliderEnum> iterationElement = new ArrayList<SliderEnum>();
		
		iterationElement.add(SliderEnum.hair);
		iterationElement.add(SliderEnum.skin);
		iterationElement.add(SliderEnum.metal);
		iterationElement.add(SliderEnum.trim);
		iterationElement.add(SliderEnum.cloth);
		iterationElement.add(SliderEnum.leather);


		ArrayList<ColorEnum> iterationColor = new ArrayList<ColorEnum>();
		iterationColor.add(ColorEnum.red);
		iterationColor.add(ColorEnum.green);
		iterationColor.add(ColorEnum.blue);

		/*
		for (SliderEnum element : iterationElement) {
			for (ColorEnum color : iterationColor) {
				System.out.println("Label : " + element + " : " + color);
				System.out.println("Slider : " + element + " : " + color);
				System.out.println(" ");
			}
		}
		*/



		//Hair

		JLabel lblHairColorRed = new JLabel("Hair Color: Red");
		lblHairColorRed.setForeground(redTextColor);//NEW
		lblHairColorRed.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblHairColorRed.setBounds(xRedText, yHairElement, widthTextRed, sizeText);
		contentPane.add(lblHairColorRed);
		
		CreatorSlider hairRed = new CreatorSlider(64, xRedSlider, yHairElement, SliderEnum.hair);
		CreatorSliders.add(hairRed);
		contentPane.add(hairRed.Jslider);
		


		
		JLabel lblGreen = new JLabel("Green");
		lblGreen.setForeground(greenTextColor);//NEW		
		lblGreen.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblGreen.setBounds(xGreenText, yHairElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(lblGreen);
		
		CreatorSlider hairGreen = new CreatorSlider(0, xGreenSlider, yHairElement, SliderEnum.hair);
		CreatorSliders.add(hairGreen);
		contentPane.add(hairGreen.Jslider);
	



		JLabel lblBlue = new JLabel("Blue");
		lblBlue.setForeground(blueTextColor);//NEW		
		lblBlue.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblBlue.setBounds(xBlueText, yHairElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(lblBlue);
		
		CreatorSlider hairBlue = new CreatorSlider(24, xBlueSlider, yHairElement, SliderEnum.hair);
		CreatorSliders.add(hairBlue);
		contentPane.add(hairBlue.Jslider);
		




		//Skin

		CreatorSlider skinBlue = new CreatorSlider(64, xBlueSlider, ySkinElement, SliderEnum.skin);
		CreatorSliders.add(skinBlue);
		contentPane.add(skinBlue.Jslider);
		
		JLabel label = new JLabel("Blue");
		label.setForeground(blueTextColor);//NEW
		label.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label.setBounds(xBlueText, ySkinElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(label);

		CreatorSlider skinGreen = new CreatorSlider(140, xGreenSlider, ySkinElement, SliderEnum.skin);
		CreatorSliders.add(skinGreen);
		contentPane.add(skinGreen.Jslider);
		
		JLabel label_1 = new JLabel("Green");
		label_1.setForeground(greenTextColor);//NEW
		label_1.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_1.setBounds(xGreenText, ySkinElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(label_1);
		
		CreatorSlider skinRed = new CreatorSlider(192, xRedSlider, ySkinElement, SliderEnum.skin);
		CreatorSliders.add(skinRed);
		contentPane.add(skinRed.Jslider);

		JLabel lblSkinColorRed = new JLabel("Skin Color: Red");
		lblSkinColorRed.setForeground(redTextColor);//NEW
		lblSkinColorRed.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblSkinColorRed.setBounds(xRedText, ySkinElement, widthTextRed, sizeText);
		contentPane.add(lblSkinColorRed);
		


		//Metal


		CreatorSlider metalBlue = new CreatorSlider(100, xBlueSlider, yMetalElement, SliderEnum.metal);
		CreatorSliders.add(metalBlue);
		contentPane.add(metalBlue.Jslider);
		
		JLabel label_3 = new JLabel("Blue");
		label_3.setForeground(blueTextColor);//NEW
		label_3.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_3.setBounds(xBlueText, yMetalElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(label_3);
		
		CreatorSlider metalGreen = new CreatorSlider(100, xGreenSlider, yMetalElement, SliderEnum.metal);
		CreatorSliders.add(metalGreen);
		contentPane.add(metalGreen.Jslider);
		
		JLabel label_4 = new JLabel("Green");
		label_4.setForeground(greenTextColor);//NEW
		label_4.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_4.setBounds(xGreenText, yMetalElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(label_4);
		
		CreatorSlider metalRed = new CreatorSlider(100, xRedSlider, yMetalElement, SliderEnum.metal);
		CreatorSliders.add(metalRed);
		contentPane.add(metalRed.Jslider);
		
		JLabel lblArmorMetalColor = new JLabel("Armor Metal Color: Red");
		lblArmorMetalColor.setForeground(redTextColor);//NEW
		lblArmorMetalColor.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblArmorMetalColor.setBounds(xRedText, yMetalElement, widthTextRed, sizeText);
		contentPane.add(lblArmorMetalColor);
		

		//trim

		CreatorSlider trimBlue = new CreatorSlider(82, xBlueSlider, yTrimElement, SliderEnum.trim);
		CreatorSliders.add(trimBlue);
		contentPane.add(trimBlue.Jslider);
		
		JLabel label_6 = new JLabel("Blue");
		label_6.setForeground(blueTextColor);//NEW
		label_6.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_6.setBounds(xBlueText, yTrimElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(label_6);
		
		CreatorSlider trimGreen = new CreatorSlider(173, xGreenSlider, yTrimElement, SliderEnum.trim);
		CreatorSliders.add(trimGreen);
		contentPane.add(trimGreen.Jslider);

		JLabel label_7 = new JLabel("Green");
		label_7.setForeground(greenTextColor);//NEW
		label_7.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_7.setBounds(xGreenText, yTrimElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(label_7);
		
		CreatorSlider trimRed = new CreatorSlider(247, xRedSlider, yTrimElement, SliderEnum.trim);
		CreatorSliders.add(trimRed);
		contentPane.add(trimRed.Jslider);

		JLabel lblMetalTrimColor = new JLabel("Armor Trim Color: Red");
		lblMetalTrimColor.setForeground(redTextColor);//NEW
		lblMetalTrimColor.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblMetalTrimColor.setBounds(xRedText, yTrimElement, widthTextRed, sizeText);
		contentPane.add(lblMetalTrimColor);
		

		//cloth

		CreatorSlider clothBlue = new CreatorSlider(115, xBlueSlider, yClothElement, SliderEnum.cloth);
		CreatorSliders.add(clothBlue);
		contentPane.add(clothBlue.Jslider);

		JLabel label_9 = new JLabel("Blue");
		label_9.setForeground(blueTextColor);//NEW
		label_9.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_9.setBounds(xBlueText, yClothElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(label_9);

		CreatorSlider clothGreen = new CreatorSlider(82, xGreenSlider, yClothElement, SliderEnum.cloth);
		CreatorSliders.add(clothGreen);
		contentPane.add(clothGreen.Jslider);

		JLabel label_10 = new JLabel("Green");
		label_10.setForeground(greenTextColor);//NEW
		label_10.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_10.setBounds(xGreenText, yClothElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(label_10);

		CreatorSlider clothRed = new CreatorSlider(82, xRedSlider, yClothElement, SliderEnum.cloth);
		CreatorSliders.add(clothRed);
		contentPane.add(clothRed.Jslider);
		
		JLabel lblArmorClothColor = new JLabel("Armor Cloth Color: Red");
		lblArmorClothColor.setForeground(redTextColor);//NEW
		lblArmorClothColor.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblArmorClothColor.setBounds(xRedText, yClothElement, widthTextRed, sizeText);
		contentPane.add(lblArmorClothColor);
		
		//leather

		CreatorSlider leatherBlue = new CreatorSlider(66, xBlueSlider, yLeatherElement, SliderEnum.leather);
		CreatorSliders.add(leatherBlue);
		contentPane.add(leatherBlue.Jslider);
		
		JLabel label_12 = new JLabel("Blue");
		label_12.setForeground(blueTextColor);//NEW
		label_12.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_12.setBounds(xBlueText, yLeatherElement + labelIncrement, widthTextBlue, sizeText);
		contentPane.add(label_12);
		
		CreatorSlider leatherGreen = new CreatorSlider(100, xGreenSlider, yLeatherElement, SliderEnum.leather);
		CreatorSliders.add(leatherGreen);
		contentPane.add(leatherGreen.Jslider);
		
		JLabel label_13 = new JLabel("Green");
		label_13.setForeground(greenTextColor);//NEW
		label_13.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_13.setBounds(xGreenText, yLeatherElement + labelIncrement, widthTextGreen, sizeText);
		contentPane.add(label_13);
		
		CreatorSlider leatherRed = new CreatorSlider(148, xRedSlider, yLeatherElement, SliderEnum.leather);
		CreatorSliders.add(leatherRed);
		contentPane.add(leatherRed.Jslider);
		
		JLabel lblArmorLeatherColor = new JLabel("Armor Leather Color: Red");
		lblArmorLeatherColor.setForeground(redTextColor);//NEW
		lblArmorLeatherColor.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblArmorLeatherColor.setBounds(xRedText, yLeatherElement, widthTextRed, sizeText);
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
		lblHair.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblHair.setBounds(373, 320, widthText, sizeText);
		contentPane.add(lblHair);
		
		JLabel lblFace = new JLabel("Face");
		lblFace.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblFace.setBounds(557, 323, widthText, sizeText);
		contentPane.add(lblFace);
		
		JLabel lblArmor = new JLabel("Armor");
		lblArmor.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblArmor.setBounds(734, 323, widthText, sizeText);
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
		lblToken.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblToken.setBounds(224, 323, widthText, sizeText);
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
		lblXOffset.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblXOffset.setBounds(373, 383, widthTextOffset, sizeText);//(373, 383, 46, 21)//TODO
		contentPane.add(lblXOffset);
		
		JLabel lblYOffset = new JLabel("X Offset");
		lblYOffset.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		lblYOffset.setBounds(373, 452, widthTextOffset, sizeText);
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
		label_2.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_2.setBounds(557, 386, widthTextOffset, sizeText);
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
		label_5.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_5.setBounds(557, 455, widthTextOffset, sizeText);
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
		label_8.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_8.setBounds(734, 383, widthTextOffset, sizeText);
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
		label_11.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		label_11.setBounds(734, 452, widthTextOffset, sizeText);
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
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnNewButton.setBounds(400, 528, 113, 38);
		contentPane.add(btnNewButton);
		
		//TODO
		JButton btnRandomColour = new JButton(textRandomColours);
		btnRandomColour.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnRandomColour.setBounds(22, 580, 200, 40);
		contentPane.add(btnRandomColour);
		
		//TODO
		JButton btnRandomPortrait = new JButton(textRandomPortrait);
		btnRandomPortrait.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnRandomPortrait.setBounds(22, 530, 200, 40);//22, 331
		contentPane.add(btnRandomPortrait);
		

		
		
		hairRed.Jslider.addChangeListener(this);
		hairGreen.Jslider.addChangeListener(this);
		hairBlue.Jslider.addChangeListener(this);
		skinRed.Jslider.addChangeListener(this);
		skinGreen.Jslider.addChangeListener(this);
		skinBlue.Jslider.addChangeListener(this);
		metalRed.Jslider.addChangeListener(this);
		metalGreen.Jslider.addChangeListener(this);
		metalBlue.Jslider.addChangeListener(this);
		trimRed.Jslider.addChangeListener(this);
		trimGreen.Jslider.addChangeListener(this);
		trimBlue.Jslider.addChangeListener(this);
		clothRed.Jslider.addChangeListener(this);
		clothGreen.Jslider.addChangeListener(this);
		clothBlue.Jslider.addChangeListener(this);
		leatherRed.Jslider.addChangeListener(this);
		leatherGreen.Jslider.addChangeListener(this);
		leatherBlue.Jslider.addChangeListener(this);
		hairXOffset.addChangeListener(this);
		hairYOffset.addChangeListener(this);
		faceXOffset.addChangeListener(this);
		faceYOffset.addChangeListener(this);
		armorXOffset.addChangeListener(this);
		armorYOffset.addChangeListener(this);
		
		
		
		sliders.add(hairRed.Jslider);
		sliders.add(hairGreen.Jslider);
		sliders.add(hairBlue.Jslider);
		sliders.add(skinRed.Jslider);
		sliders.add(skinGreen.Jslider);
		sliders.add(skinBlue.Jslider);
		sliders.add(metalRed.Jslider);
		sliders.add(metalGreen.Jslider);
		sliders.add(metalBlue.Jslider);
		sliders.add(trimRed.Jslider);
		sliders.add(trimGreen.Jslider);
		sliders.add(trimBlue.Jslider);
		sliders.add(clothRed.Jslider);
		sliders.add(clothGreen.Jslider);
		sliders.add(clothBlue.Jslider);
		sliders.add(leatherRed.Jslider);
		sliders.add(leatherGreen.Jslider);
		sliders.add(leatherBlue.Jslider);
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
	

	void pixelIteration(CreatorComposant cc, BufferedImage destination, boolean isToken, boolean isRegular ){
		Color pixel = null;
		Color newPixel = null;
		for(int i = 0; i<cc.IJmax; i++){
			for(int j = 0; j<cc.IJmax; j++){
				if(isToken){
					pixel = new Color(cc.BI.getRGB(i, j),true);
					if(pixel.getAlpha()==0){
						continue;
					}
					newPixel = pixelParser(pixel);
				}
				else{
					if (i - cc.YOffset < 0 || i - cc.YOffset > cc.IJmax - 1 ) continue;
					if (j + cc.XOffset < 0 || j + cc.XOffset > cc.IJmax - 1 ) continue;
					if(isRegular){
						pixel = new Color(cc.BI.getRGB(i-cc.YOffset, j+cc.XOffset),true);
						if(pixel.getAlpha()==0){
							continue;
						}
						newPixel = pixelParser(pixel);
					}
					else{
						pixel = new Color(cc.BI.getRGB(i-cc.YOffset, j+cc.XOffset),true);
						newPixel = pixelParser(pixel);
						if(pixel.getAlpha()==0){
							continue;
						}
					}
				}
				destination.setRGB(i*2, j*2, newPixel.getRGB());
				destination.setRGB(i*2+1, j*2, newPixel.getRGB());
				destination.setRGB(i*2, j*2+1, newPixel.getRGB());
				destination.setRGB(i*2+1, j*2+1, newPixel.getRGB());
			}
		}
	}

	void drawImages(){
		portrait = deepCopy(blankPortrait);
		token = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);

		pixelIteration(CChairb , portrait, false, true);
		pixelIteration(CCarmor , portrait, false, true);
		pixelIteration(CCface , portrait, false, true);
		pixelIteration(CChair , portrait, false, false);	
		pixelIteration(CCimportedToken	, token, true, true);
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
			CChair.XOffset = val;
			CChairb.XOffset = val;
			break;
		case 19:
			CChair.YOffset = val;
			CChairb.YOffset = val;
			break;
		case 20:
			CCface.XOffset = val;
			break;
		case 21:
			CCface.YOffset = val;
			break;
		case 22:
			CCarmor.XOffset = val;
			break;
		case 23:
			CCarmor.YOffset = val;
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
					CChair.BI = ImageIO.read(new File(path + "resources/" + fileName));
					String secondFileName = fileName.substring(0, fileName.length()-4);
					CChairb.BI = ImageIO.read(new File(path + "resources/" + secondFileName + "b.png"));
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
				    CCarmor.BI = ImageIO.read(new File(path + "resources/" + fileName));
				} catch (IOException ex) {
					System.out.println(ex);
				}
				break;
			case 3:
				try {
					CCimportedToken.BI = ImageIO.read(new File(path + "resources/" + fileName)); 
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

					String skinName = SliderEnum.skin.toString();
					String offsetName = SliderEnum.offset.toString();

					for(JSlider slider : sliders) {
						sliderName = slider.getName();
						System.out.println(sliderName);
						
						if(sliderName != offsetName && sliderName != skinName) {//colours but not offset or skin	
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