package mainv2;

import java.io.*;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class LibMan extends JTree{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File Library;
	private DefaultTreeModel tm;
	File[] mangas;
	Files files;
	JTree tree;
	Image image;
	LibMan()
	{
		super();
		tree=this;
		Library=new File("C:\\Users\\Vivek\\Downloads\\My Mangas");
		mangas=Library.listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				if(pathname.isDirectory())
					return true;
				return false;
			}});
		tm=(DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(Library.getName());
		tm.setRoot(root);
		DefaultMutableTreeNode node;
		System.out.println("No. of Mangas "+mangas.length);
		for(int i=0;i<mangas.length;i++)
		{
			DefaultMutableTreeNode node2;
			node=new DefaultMutableTreeNode(mangas[i].getName());
			root.add(node);
			File[] temp2=mangas[i].listFiles();
			System.out.println("No of Chapters "+temp2.length);
			for(int j=0;j<temp2.length;j++)
			{
				node2=new DefaultMutableTreeNode(temp2[j].getName());
				node.add(node2);
			}
		}
		this.expandRow(0);
		this.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode file=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				System.out.println(file.toString());
				String filename=file.toString();
				if(filename.equals("My Mangas"))
					return;
				for(int i=0;i<mangas.length;i++)
					if(filename.equals(mangas[i].getName()))
						return;
				files.setChapter(new File(Library.toString()+"\\"+file.getParent().toString()+"\\"+file.toString()));
				files.setManga(new File(Library.toString()+"\\"+file.getParent()));
				image.draw(files);
			}
		});
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
	/**
	 * @return the files
	 */
	public Files getFiles() {
		return files;
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(Files files) {
		this.files = files;
	}
}