package mainv2;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


public class Reader {
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Body());
	}
}
class Body extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu menu;
	private JMenuBar menubar;
	private JMenuItem jmi;
	private JScrollPane jsp1,jsp2;
	private JSplitPane jsp;
	private JPanel nav;
	private JButton button;
	private LibMan libMan=new LibMan();
	private Events events=new Events();
	private ImageList<String> imageList=new ImageList<>();
	private Files files=new Files();;
	private ImageLabel imageLabel=new ImageLabel();
	Body()
	{
		super("Manga Reader");
		this.addWindowListener(new BasicWindowMonitor());
		menubar=new JMenuBar();
		setNav(new JPanel());
		getNav().add(imageList);
		jsp1=new JScrollPane(libMan);
		jsp2=new JScrollPane(imageLabel);
		libMan.setFiles(files);
		imageList.setFiles(files);
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp1,jsp2);
		jsp.setDividerLocation(100);
		files.setLibman(libMan);
		files.setImageList(imageList);
		files.setImageLabel(imageLabel);
		
	}
	@Override
	public void run() {
		setSize(1000, 800);
		setResizable(true);
		menu=new JMenu("File");
		menu.setMnemonic('F');
		menu.addActionListener(events);
		menu.addKeyListener(events);
		jmi=new JMenuItem("Open");
		jmi.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.event.InputEvent.CTRL_DOWN_MASK, true));
		jmi.addKeyListener(events);
		jmi.addActionListener(events);
		menu.add(jmi);
		menubar.add(menu);
		setJMenuBar(menubar);
		button = new JButton("Prev");
		button.addKeyListener(events);
		button.addActionListener(events);
		getNav().add(button,BorderLayout.WEST);
		button = new JButton("Next");
		button.addKeyListener(events);
		button.addActionListener(events);
		getNav().add(button,BorderLayout.WEST);
		getContentPane().add(jsp);
		getContentPane().add(getNav(),BorderLayout.SOUTH);
		setVisible(true);	
		setResizable(true);
		libMan.setFocusable(false);
		imageList.setFocusable(false);
		jsp.setFocusable(true);
		jsp2.requestFocus();
	}

	public JPanel getNav() {
		return nav;
	}
	public void setNav(JPanel nav) {
		this.nav = nav;
	}

	@SuppressWarnings("synthetic-access")
	private class Events implements ActionListener, KeyListener {
		public Events() {
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
				files.changeImage(Files.PREV_IMAGE);
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				files.changeImage(Files.NEXT_IMAGE);
			else if(e.getKeyCode()==KeyEvent.VK_DOWN)
				if(e.getModifiers()==InputEvent.CTRL_MASK)
					files.changeChapter(Files.NEXT_CHAP);
				else	imageLabel.scroll(1);
			else if(e.getKeyCode()==KeyEvent.VK_UP)
				if(e.getModifiers()==InputEvent.CTRL_MASK)
					files.changeChapter(Files.PREV_CHAP);
				else 	imageLabel.scroll(-1);
			}
	
		@Override
		public void keyReleased(KeyEvent e) {}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Open"))
			{
				JFileChooser dialog=new JFileChooser(Files.home);
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(dialog.showOpenDialog(new JPanel())==JFileChooser.APPROVE_OPTION)
				{
					File f=dialog.getSelectedFile();
					files.newChapter(f);
				}		
			}
			else if(e.getActionCommand().equals("Prev"))
				files.changeImage(Files.PREV_IMAGE);
			else if(e.getActionCommand().equals("Next"))
				files.changeImage(Files.NEXT_IMAGE);
		}
	}
}
