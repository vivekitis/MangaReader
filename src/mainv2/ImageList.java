package mainv2;

import javax.swing.JComboBox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings({ "hiding", "rawtypes" })
public class ImageList<String> extends JComboBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] images;
	private DefaultComboBoxModel combobox;
	private Events events=new Events();
	private Image image;
	@SuppressWarnings({ "unchecked" })
	ImageList()
	{
		super();
		combobox=new DefaultComboBoxModel();
		this.setModel(combobox);
		this.addItemListener(events);
	}
	/**
	 * @return the images
	 */
	public String[] getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(String[] images) {
		this.images = images;
	}
	
	@SuppressWarnings("unchecked")
	public void fillBox()
	{
		
		this.removeAllItems();
		for(int i=0;i<images.length;i++)
			combobox.addElement(images[i]);
		this.getParent().revalidate();
	}
	private class Events implements ItemListener{
		public Events() {}

		@SuppressWarnings("synthetic-access")
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(!e.getItem().equals(image.getCurrent_Image())&&e.getStateChange()==ItemEvent.SELECTED)
			{
				System.out.println(e.getItem()+" "+e.getStateChange());
				int newimgno=image.getCorrespondingImg((java.lang.String)e.getItem());
				System.out.println(newimgno);
				image.change(newimgno);
			}
		}
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
}
