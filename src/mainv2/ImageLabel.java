package mainv2;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ImageLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image; 
	ImageLabel()
	{
		super();
	}
	void setImage(File f,boolean b)
	{
		((JScrollPane) getParent().getParent()).getVerticalScrollBar().setValue(0);
		image = new ImageIcon(f.getAbsolutePath());
		this.setIcon(image);
		this.getParent().revalidate();
		this.transferFocus();
	}
	public void scroll(int i) {
		JScrollBar jsb=((JScrollPane) getParent().getParent()).getVerticalScrollBar();
		if((jsb.getValue()!=jsb.getMaximum())&&i>0)
			if(jsb.getValue()+10<jsb.getMaximum())
				jsb.setValue(jsb.getValue()+10);
			else jsb.setValue(jsb.getMaximum());
		else if(jsb.getValue()!=jsb.getMinimum())
			if(jsb.getValue()-10>jsb.getMinimum())
				jsb.setValue(jsb.getValue()-10);
			else jsb.setValue(jsb.getMinimum());
	}
}
