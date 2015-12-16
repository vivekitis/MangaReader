package mainv2;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Image extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image; 
	Files files;
	int img_no,chap_no;
	Image()
	{
		super();
	}
	void setImage(File f)
	{
		image = new ImageIcon(f.getAbsolutePath());
		this.setIcon(image);
		this.getParent().revalidate();
		System.out.println(f.getAbsolutePath());
	}
	void change(int n)
	{
		if(files==null)
			return;
		((JScrollPane) getParent().getParent()).getVerticalScrollBar().setValue(0);;
		if(n>0)
		{
			img_no=files.getImgno();
			System.out.println(n+" "+img_no);
			chap_no=files.getChapno();
			System.out.println("Image count : "+files.getImg_count());
			if(files.getImg_count()>img_no+1)
			{
				System.out.println("image no:"+img_no);
				img_no++;
				files.setImgno(img_no);
				setImage(files.getImage(img_no));
			}
			else if(files.getChap_count()>chap_no+1)
			{
				System.out.println("New Chap++"+files.getChapno()+" "+(++chap_no));
				files.setImgno(0);
				chap_no++;
				files.setChapno(chap_no);
				files.updateChap(chap_no);
				setImage(files.getImage(0));
			}
		}
		else
		{
			
			img_no=files.getImgno();
			System.out.println(n+" "+img_no);
			chap_no=files.getChapno();
			System.out.println("Image count : "+files.getImg_count());
			if(img_no-1>=0)
			{
				System.out.println("image no:"+img_no);
				img_no--;
				files.setImgno(img_no);
				setImage(files.getImage(img_no));
			}
			else if(chap_no-1>=0)
			{
				System.out.println("New Chap--");
				files.setImgno(0);
				chap_no--;
				files.setChapno(chap_no);
				files.updateChap(chap_no);
				setImage(files.getImage(0));
			}
		}
	}
	void draw(Files files) 
	{
		this.files=files;
		image = new ImageIcon(files.getImages()[0].getAbsolutePath());
		this.setIcon(image);
		this.getParent().revalidate();
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
