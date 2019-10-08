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
	final String textRandomToken = "Random token";
	final String textExport = "Export";
	final String boxPortrait = "Portrait";
	final String boxToken = "Token";

	Random rn = new Random();
	
	ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	ArrayList<JComboBox<String>> boxes = new ArrayList<JComboBox<String>>();
	ArrayList<CreatorSlider> CreatorSliders = new ArrayList<CreatorSlider>();

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
	Color exportBackgroundColor = new Color(150,200,150,255);

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
		System.out.println("\n\nrawPath : " + rawPath + "\n\n");
		
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
		token = null;
		blankPortrait = null;
		try {
			portrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
			token = ImageIO.read(new File(path + "resources/BlankTok.png"));
			blankPortrait = ImageIO.read(new File(path + "resources/BlankPortrait.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		int initialWidth = 1000;//932
		int initialHeight = 700;//610	
		int border = 5;//5 (useless ?)
    
		int labelIncrement = 3;
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
		
		//Build the lists of things we want to combine
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

		//Mega-list of default values
		int elementMax = SliderEnum.info.Max;
		int colorMax = ColorEnum.info.Max;
		int[][] defaultElementColor = new int[elementMax][colorMax];
		
		defaultElementColor[SliderEnum.hair.ID][ColorEnum.red.ID] = 64;
		defaultElementColor[SliderEnum.hair.ID][ColorEnum.green.ID] = 0;
		defaultElementColor[SliderEnum.hair.ID][ColorEnum.blue.ID] = 24;

		defaultElementColor[SliderEnum.skin.ID][ColorEnum.blue.ID] = 64;
		defaultElementColor[SliderEnum.skin.ID][ColorEnum.green.ID] = 140;
		defaultElementColor[SliderEnum.skin.ID][ColorEnum.red.ID] = 192;

		defaultElementColor[SliderEnum.metal.ID][ColorEnum.blue.ID] = 100;
		defaultElementColor[SliderEnum.skin.ID][ColorEnum.green.ID] = 100;
		defaultElementColor[SliderEnum.skin.ID][ColorEnum.red.ID] = 100;

		defaultElementColor[SliderEnum.trim.ID][ColorEnum.blue.ID] = 82;
		defaultElementColor[SliderEnum.trim.ID][ColorEnum.green.ID] = 173;
		defaultElementColor[SliderEnum.trim.ID][ColorEnum.red.ID] = 247;

		defaultElementColor[SliderEnum.cloth.ID][ColorEnum.blue.ID] = 115;
		defaultElementColor[SliderEnum.cloth.ID][ColorEnum.green.ID] = 82;
		defaultElementColor[SliderEnum.cloth.ID][ColorEnum.red.ID] = 82;

		defaultElementColor[SliderEnum.leather.ID][ColorEnum.blue.ID] = 66;
		defaultElementColor[SliderEnum.leather.ID][ColorEnum.green.ID] = 100;
		defaultElementColor[SliderEnum.leather.ID][ColorEnum.red.ID] = 148;


		// Build the sliders and labels
		String labelText;
		
		int initialValue;
		int yLabelPosition;

		for (SliderEnum element : iterationElement) {
			for (ColorEnum color : iterationColor) {

				//Prepare Label
				if(color == ColorEnum.red){
					labelText = element.toString() + " Color: " +  color.toString();
					yLabelPosition = element.PositionY;
				}
				else{
					labelText = color.toString();
					yLabelPosition = element.PositionY + labelIncrement;
				}
				
				//Build Label
				JLabel jlabel = new JLabel(labelText);
				jlabel.setForeground(color.TextColor);
				jlabel.setFont(new Font("Calibri", Font.BOLD, sizeFont));
				jlabel.setBounds(color.TextPositionX, yLabelPosition, color.WidthText, sizeText);
				contentPane.add(jlabel);

				//Prepare Slider
				initialValue = defaultElementColor[element.ID][color.ID];

				//Build Slider
				CreatorSlider cslider = new CreatorSlider(initialValue, color.SliderPositionX, element.PositionY, element.Name);
				CreatorSliders.add(cslider);
				contentPane.add(cslider.Jslider);

				//Add slider to the global lists
				cslider.Jslider.addChangeListener(this);
				sliders.add(cslider.Jslider);

				System.out.println(" ");
			}
		}
		
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
		
		comboBox.setName(boxPortrait);
		comboBox_1.setName(boxPortrait);
		comboBox_2.setName(boxPortrait);
		comboBox_3.setName(boxToken);
		
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
    hairXOffset.setName(SliderEnum.offset.Name);
		contentPane.add(hairXOffset);
		
		JLabel lblXOffset = new JLabel("Y Offset");
		lblXOffset.setFont(new Font("Calibri", Font.BOLD, sizeFont));
    lblXOffset.setBounds(373, 383, widthTextOffset, sizeText);//(373, 383, 46, 21) ??
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
    hairYOffset.setName(SliderEnum.offset.Name);
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
    faceXOffset.setName(SliderEnum.offset.Name);
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
    faceYOffset.setName(SliderEnum.offset.Name);
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
    armorXOffset.setName(SliderEnum.offset.Name);
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
    armorYOffset.setName(SliderEnum.offset.Name);
		contentPane.add(armorYOffset);
		
		JButton btnNewButton = new JButton(textExport);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, sizeFont));
btnNewButton.setBounds(400, 530, 113, 38);
		btnNewButton.setBackground(exportBackgroundColor);
		contentPane.add(btnNewButton);
		
		JButton btnRandomColour = new JButton(textRandomColours);
		btnRandomColour.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnRandomColour.setBounds(35, 580, 160, 40);
		contentPane.add(btnRandomColour);
		
		JButton btnRandomPortrait = new JButton(textRandomPortrait);
		btnRandomPortrait.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnRandomPortrait.setBounds(35, 530, 160, 40);//22, 331
		contentPane.add(btnRandomPortrait);

		JButton btnRandomToken = new JButton(textRandomToken);
		btnRandomToken.setFont(new Font("Calibri", Font.BOLD, sizeFont));
		btnRandomToken.setBounds(222, 530, 145, 40);//22, 331
		contentPane.add(btnRandomToken);
		hairXOffset.addChangeListener(this);
		hairYOffset.addChangeListener(this);
		faceXOffset.addChangeListener(this);
		faceYOffset.addChangeListener(this);
		armorXOffset.addChangeListener(this);
		armorYOffset.addChangeListener(this);
		
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
    btnRandomToken.addActionListener(this);
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

		System.out.println("\n>> " + str);

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
				
case textRandomColours:
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
						if(sliderName != offsetName && sliderName != skinName) {//colours but not offset or skin	
							max = slider.getMaximum();
							min = slider.getMinimum();
							n = max - min + 1;
							i = Math.abs(rn.nextInt() % n);
							randomNum =  min + i;
							slider.setValue(randomNum);

							//System.out.println(randomNum);
						}
					}	
				}
				break;
			
				
			case textRandomPortrait:
				{
					String boxName;
					int n;
					int randomNum;
					String elementName;	
					for(JComboBox<String> box : boxes) {	
						boxName = box.getName();
						if(boxName == boxPortrait) {		
							n = box.getItemCount() ;						
							randomNum = Math.abs(rn.nextInt() % n);
							box.setSelectedIndex(randomNum);

							elementName = box.getSelectedItem().toString();
							System.out.println(randomNum + "/" + n + " - " + elementName );
						}	
					}
				}
				break;

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

case textRandomToken:
			{
				String boxName;
				int n;
				int randomNum;
				String elementName;	
				for(JComboBox<String> box : boxes) {	
					boxName = box.getName();
					if(boxName == boxToken) {	
						n = box.getItemCount() ;						
						randomNum = Math.abs(rn.nextInt() % n);
						box.setSelectedIndex(randomNum);
						elementName = box.getSelectedItem().toString();
						System.out.println(randomNum + "/" + n + " - " + elementName );
					}	
				}
			}
			break;

		}
	}
}