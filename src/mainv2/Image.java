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
	private Files files;
	private int img_no,chap_no;
	private ImageList<String> imageList;
	private String current_Image="";
	static int NEXT_IMAGE=100;
	static int PREV_IMAGE=-NEXT_IMAGE;
	static int NEXT_CHAP=100;
	static int PREV_CHAP=-NEXT_CHAP;
	Image()
	{
		super();
	}
	void setImage(File f,boolean b)
	{
		image = new ImageIcon(f.getAbsolutePath());
		this.setIcon(image);
		this.getParent().revalidate();
		current_Image=f.getName();
		System.out.println(b);
		if(b)
			imageList.fillBox();
		//System.out.println(f.getAbsolutePath());
	}
	void change(int n)
	{
		if(files==null)
			return;
		((JScrollPane) getParent().getParent()).getVerticalScrollBar().setValue(0);;
		if(n>=0)
		{
			img_no=files.getImgno();
			chap_no=files.getChapno();
			if((files.getImg_count()>img_no+1)&&(n==NEXT_IMAGE))
			{
				img_no++;
				files.setImgno(img_no);
				System.out.println("Entered first if");
				setImage(files.getImage(img_no),true);
			}
			else if((files.getChap_count()>chap_no+1)&&(n==NEXT_CHAP))
			{
				files.setImgno(0);
				chap_no++;
				files.setChapno(chap_no);
				files.updateChap(chap_no);
				System.out.println("Entered second if");
				setImage(files.getImage(0),true);
			}
			else if(n<NEXT_IMAGE)
			{
				files.setImgno(n);
				img_no=n;
				System.out.println("Entered last if");
				setImage(files.getImage(img_no),false);
			}
		}
		else if(n<0)
		{
			
			img_no=files.getImgno();
			chap_no=files.getChapno();
			if((img_no-1>=0)&&(n==PREV_IMAGE))
			{
				img_no--;
				files.setImgno(img_no);
				System.out.println("Entered first else");
				setImage(files.getImage(img_no),true);
			}
			else if((chap_no-1>=0)&&(n==PREV_CHAP))
			{
				files.setImgno(0);
				chap_no--;
				files.setChapno(chap_no);
				files.updateChap(chap_no);				
				System.out.println("Entered second else");
				setImage(files.getImage(0),true);
			}
		}
	}
	void draw(Files files) 
	{
		this.files=files;
		image = new ImageIcon(files.getImages()[0].getAbsolutePath());
		this.setIcon(image);
		this.getParent().revalidate();
		imageList.setImages(files.getChapter().list());
		imageList.setImage(this);
		current_Image=files.getImages()[0].getName();
		imageList.fillBox();
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
	/**
	 * @param imalgeList the imalgeList to set
	 */
	public void setImalgeList(ImageList<String> imalgeList) {
		this.imageList = imalgeList;
	}
	/**
	 * @return the chap_no
	 */
	public int getChap_no() {
		return chap_no;
	}
	/**
	 * @return the files
	 */
	public Files getFiles() {
		return files;
	}
	/**
	 * @param chap_no the chap_no to set
	 */
	public void setChap_no(int chap_no) {
		this.chap_no = chap_no;
	}
	/**
	 * @return the current_Image
	 */
	public String getCurrent_Image() {
		return current_Image;
	}
	/**
	 * @return the img_no
	 */
	public int getImg_no() {
		return img_no;
	}
	
	public int getCorrespondingImg(String s)
	{
		File[] images=files.getImages();
		for(int i=0;i<images.length;i++)
			if(images[i].getName().equals(s))
				return i;
		return -1;
	}
}
