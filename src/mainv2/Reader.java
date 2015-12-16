package mainv2;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	Files files;
	Image image;
	String home="C:\\Users\\Vivek\\Downloads\\My Mangas";
	Body()
	{
		super("Manga Reader");
		this.addWindowListener(new BasicWindowMonitor());
		menubar=new JMenuBar();
		files=new Files();
		jsp1=new JScrollPane(libMan);
		jsp2=new JScrollPane(image=new Image());
		libMan.setImage(image);
		libMan.setFiles(files);
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp1,jsp2);
		jsp.setDividerLocation(100);
		setNav(new JPanel());
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
	}

	/**
	 * @return the jsp2
	 */
	public JScrollPane getJsp2() {
		return jsp2;
	}
	/**
	 * @param jsp2 the jsp2 to set
	 */
	public void setJsp2(JScrollPane jsp2) {
		this.jsp2 = jsp2;
	}
	public JPanel getNav() {
		return nav;
	}
	public void setNav(JPanel nav) {
		this.nav = nav;
	}

	class Events implements ActionListener, KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				image.change(-1);
			}
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				image.change(1);
			}
			else if(e.getKeyCode()==KeyEvent.VK_DOWN)
				if(e.getModifiers()==InputEvent.CTRL_MASK)
					{
					image.change(2);
					}
				else{
					image.scroll(1);
				}
					//getRender().scroll(1,getDialog(),getJsp2());
			else if(e.getKeyCode()==KeyEvent.VK_UP)
				if(e.getModifiers()==InputEvent.CTRL_MASK)
					{
					image.change(-2);
					}
				else 
					{
					image.scroll(-1);
					}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
	
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Open"))
			{
				JFileChooser dialog=new JFileChooser(home);
				dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(dialog.showOpenDialog(new JPanel())==JFileChooser.APPROVE_OPTION)
				{
					files.setChapter(dialog.getSelectedFile());
					files.setManga(dialog.getSelectedFile().getParentFile());
					image.draw(files);
				}
				
			}
			else if(e.getActionCommand().equals("Prev"))
			{
				image.change(-1);
			}
			else if(e.getActionCommand().equals("Next"))
			{
				image.change(1);
			}
		}
	}
}
