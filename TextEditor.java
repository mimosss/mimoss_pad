package mimos.pad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.UIManager;

public class TextEditor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	

	
	Color lightBlue = new Color(154, 194, 244);
	UIManager ui = new UIManager();
	
	Color sofPink = new Color(247, 177, 208);
	JMenu menuFile, menuAbout;
	JPopupMenu popup;
	JTextArea textField;
	JMenuItem openFile, saveFile, close,about;
	JMenuItem copy, paste, cut, selectAll;

	public TextEditor() {

		JPanel fillPanA = new JPanel();
		fillPanA.setSize(500, 15);
		fillPanA.setOpaque(true);
		fillPanA.setBackground(Color.black);

		JPanel mainHeader = new JPanel();
		mainHeader.setOpaque(true);

		JPanel centralPanel = new JPanel();
		centralPanel.setOpaque(true);
		centralPanel.setBackground(lightBlue);
		centralPanel.add(headerLayout());
		centralPanel.add(textLayout());

		getContentPane().add(mainHeader, BorderLayout.PAGE_START);
		getContentPane().add(centralPanel, BorderLayout.CENTER);

	}

	/**
	 * 
	 */

	private JPanel headerLayout() {

		// JPanel for header
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setBackground(lightBlue);
//		mainPanel.setBackground(Color.white);
		mainPanel.setPreferredSize(new Dimension(548, 35));

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(true);
		menuBar.setBounds(3, 3, 120, 50);
		menuBar.setPreferredSize(new Dimension(120, 50));
		menuBar.setBackground(lightBlue);
		menuBar.setFont(new Font("Arial", Font.BOLD, 12));

		//JMenu
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Arial", Font.BOLD, 12));
		
	    JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Arial", Font.BOLD, 12));
		
	   
	
		about = new JMenuItem("Click Me!");
		about.addActionListener(this);
		
		openFile = new JMenuItem("Open File"); // an open option
		saveFile = new JMenuItem("save"); // a save option
		close = new JMenuItem("close");
       
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		close.addActionListener(this);

		

		// Shortcut for openFile
		openFile.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		// Shortcut for saveFile
		saveFile.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		// Shortcut for close
		close.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		menuAbout.add(about);
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(close);

		menuBar.add(menuFile);
		menuBar.add(menuAbout);

		JPanel fillPanB = new JPanel();
		fillPanB.setPreferredSize(new Dimension(418, 5));
		fillPanB.setOpaque(true);
		fillPanB.setBackground(lightBlue);
		//fillPanB.setBackground(Color.white);

		mainPanel.add(menuBar);
	    mainPanel.add(fillPanB, BorderLayout.WEST);

		return mainPanel;

	}

	JPanel textLayout() {

		JPanel textFieldPanel = new JPanel();
		textField = new JTextArea();
		textField.setPreferredSize(new Dimension(550, 500));
		textField.setFont(new Font("Courier", Font.PLAIN, 12));
		textFieldPanel.add(textField);

		popup = new JPopupMenu();

		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		cut = new JMenuItem("Cut");
		selectAll = new JMenuItem("Select All");

		/*Shortcut for
		 * copy
		 * paste
		 * cut
		 * selectAll
		 */
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		/*Add actionEvent for
		 * copy
		 * paste
		 * cut
		 * selectAll
		 */
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.copy();
			}

		});

		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.paste();
			}
		});

		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.cut();
			}
		});
		selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.selectAll();
			}

		});

		popup.add(copy);
		popup.add(paste);
		popup.add(cut);
		popup.add(selectAll);
		
	    textField.setComponentPopupMenu( popup );

		return textFieldPanel;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TextEditor frame = new TextEditor();
					frame.setVisible(true);
					frame.setSize(580, 600);
					frame.setResizable(false);
					frame.setTitle("요즘의 메모 - Your Memo");

					;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == about) {
			 ImageIcon icon = new ImageIcon(TextEditor.class.getResource("Bob.png"));
			 ui.put("OptionPane.background",Color.WHITE);
			 ui.put("Panel.background", Color.WHITE);
			 //JOptionPane dialogBox = new JOptionPane();
             JOptionPane.showMessageDialog(
		                         this,
		                         "You only live once, but if you do it right,"
		 		                   + " once is enough. ",
		                         "Your Memo By Mimoss",
		                         JOptionPane.INFORMATION_MESSAGE,
		                         icon);		
		}
		if (e.getSource() == close)
			this.dispose();
		if (e.getSource() == openFile) {
			JFileChooser open = new JFileChooser();
			int option = open.showOpenDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				textField.setText(" ");
			}
			try {
				String line = null;
				BufferedReader br = new BufferedReader(new FileReader(open.getSelectedFile().getPath()));

				while ((line = br.readLine()) != null) // while there's still something to read
					textField.append(line + "\n");
				br.close();

			} catch (Exception ex) {
				 ImageIcon icon = new ImageIcon(TextEditor.class.getResource("Thinking.png"));
				 ui.put("OptionPane.background",Color.WHITE);
				 ui.put("Panel.background", Color.WHITE);
				 //JOptionPane dialogBox = new JOptionPane();
	             JOptionPane.showMessageDialog(
			                         this,
			                         "No File Selected ",
			                         "Your Memo By Mimoss",
			                         JOptionPane.INFORMATION_MESSAGE,
			                         icon);		

			}

		}

		if (e.getSource() == saveFile) {
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(this);
			if (option == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedWriter saveFile = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					saveFile.write(textField.getText());
					saveFile.close();
					ImageIcon icon = new ImageIcon(TextEditor.class.getResource("thumbsUp.png"));
					 ui.put("OptionPane.background",Color.WHITE);
					 ui.put("Panel.background", Color.WHITE);
					 //JOptionPane dialogBox = new JOptionPane();
		             JOptionPane.showMessageDialog(
				                         this,
				                         "Bravo!! File have been saved succesfully ",
				                         "Your Memo By Mimoss",
				                         JOptionPane.INFORMATION_MESSAGE,
				                         icon);	
//					JOptionPane.showMessageDialog(this, "Bravo!! File have been saved succesfully");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "File Not Save");

				}
			}

		}

	}

}