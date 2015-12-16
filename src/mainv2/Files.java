package mainv2;

import java.io.File;
import java.io.FileFilter;

public class Files{
	//private String home="C:\\Users\\Vivek\\Downloads\\My Mangas";
	private File chapter,manga;
	private File[] chapters,images;
	int chapno=0,imgno=0,chap_count=0,img_count=0;
	private void updatemanga() 
	{
		chapters=manga.listFiles(new FileFilter(){
		public boolean accept(File pathname) {
			if(pathname.isDirectory())
				return true;
			return false;	}});
		for(chapno=0;chapno<chapters.length;chapno++)
			if(chapter.getName().equals(chapters[chapno].getName()))
				break;
		chap_count=manga.listFiles().length;
		images=chapter.listFiles();
	}
	File getManga()
	{
		return manga;
	}
	void setChapter(File x)
	{
		chapter=x;
		img_count=chapter.listFiles().length;
	}
	File getChapter()
	{
		return chapter;
	}
	void setManga(File x)
	{
		if(manga==null|manga!=x)
		{	
			manga=x;
			updatemanga();
		}		
	}
	void updateChap(int chapno)
	{
		this.chapter=chapters[chapno];
		images=chapter.listFiles();
		img_count=images.length;
	}
	/**
	 * @return the chapno
	 */
	public int getChapno() {
		return chapno;
	}
	/**
	 * @param chapno the chapno to set
	 */
	public void setChapno(int chapno) {
		this.chapno = chapno;
	}
	/**
	 * @return the imgno
	 */
	public int getImgno() {
		return imgno;
	}
	/**
	 * @param imgno the imgno to set
	 */
	public void setImgno(int imgno) {
		if(chapter.list().length>imgno)
			this.imgno = imgno;
	}
	/**
	 * @return the images
	 */
	public File[] getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(File[] images) {
		this.images = images;
	}
	public File getImage(int no)
	{
		return images[no];
	}
	/**
	 * @return the chap_count
	 */
	public int getChap_count() {
		return chap_count;
	}
	/**
	 * @return the img_count
	 */
	public int getImg_count() {
		return img_count;
	}
	
}