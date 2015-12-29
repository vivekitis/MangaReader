package mainv2;

import java.io.File;
import java.io.FileFilter;

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
	private DefaultTreeModel tm;
	private File[] mangas;
	private Files files;
	private JTree tree;
	private DefaultMutableTreeNode node;
	private DefaultMutableTreeNode node2;
	LibMan()
	{
		super();
		tree=this;
		tm=(DefaultTreeModel) this.getModel();
		mangas=new File(Files.home).listFiles(new FileFilter(){
			public boolean accept(File pathname) {
				if(pathname.isDirectory())
					return true;
				return false;
			}});
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(mangas[0].getParentFile().getName());
		tm.setRoot(root);
		for(int i=0;i<mangas.length;i++)
		{
			node=new DefaultMutableTreeNode(mangas[i].getName());
			root.add(node);
			File[] temp2=mangas[i].listFiles();
			for(int j=0;j<temp2.length;j++)
			{
				node2=new DefaultMutableTreeNode(temp2[j].getName());
				node.add(node2);
			}
		}
		this.expandRow(0);
		this.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener(){

			@SuppressWarnings("synthetic-access")
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode file=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				String filename=file.toString();
				if(filename.equals("My Mangas"))
					return;
				for(int i=0;i<mangas.length;i++)
					if(filename.equals(mangas[i].getName()))
						return;
				files.newChapter(new File(Files.home+"\\"+file.getParent()+"\\"+file.toString()));
			}
		});
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(Files files) {
		this.files = files;
	}
}