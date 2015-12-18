package mainv2;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("hiding")
public class ImageList<String> extends JComboBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] images;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel combobox;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	ImageList()
	{
		super();
		combobox=new DefaultComboBoxModel();
		this.setModel(combobox);
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
	
}
