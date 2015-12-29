package mainv2;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ImageList<T> extends JComboBox<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> combobox;
	private Events events=new Events();
	private Files files;
	ImageList()
	{
		super();
		combobox=new DefaultComboBoxModel<>();
		this.setModel(combobox);
		this.addItemListener(events);
	}
	public void fillBox(String[] images)
	{
		
		this.removeAllItems();
		for(int i=0;i<images.length;i++)
			combobox.addElement(images[i]);
		this.getParent().revalidate();
	}
	private class Events implements ItemListener{
		public Events() {}

		@SuppressWarnings( "synthetic-access" )
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(!e.getItem().equals(files.Currentimg())&&e.getStateChange()==ItemEvent.SELECTED)
				files.changeImage(combobox.getIndexOf(e.getItem())); 
		}
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(Files files) {
		this.files = files;
	}
}
