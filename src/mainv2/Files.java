package mainv2;

import java.io.File;
public class Files
{

	private Manga manga=new Manga();
	private Chapter chapter=new Chapter();
	private Image image=new Image();
	boolean change=false;
	private LibMan libman;
	private ImageList<?> imageList;
	private ImageLabel imageLabel;
	static String home="C:\\Users\\Vivek\\Downloads\\My Mangas";
	static int NEXT_IMAGE=100;
	static int PREV_IMAGE=-NEXT_IMAGE;
	static int NEXT_CHAP=200;
	static int PREV_CHAP=-NEXT_CHAP;
	/**
	 * @param manga the manga to set
	 */
	void newChapter(File f)
	{
		manga.setManga(f.getParentFile());
		chapter.setChapter(f);
		manga.setChapterNo(chapter);
		image.setImage(chapter.getImage(0));
		imageLabel.setImage(image.Image,false);
		imageList.fillBox(chapter.getImages());
	}
	void changeImage(int number)
	{
		int imgno=chapter.getImage_No();
		change=true;
		if(number==NEXT_IMAGE)
		{
			if(imgno==chapter.getImageCount()-1)
				changeChapter(NEXT_CHAP);
			else
			{
				chapter.setImage_No(++imgno);
				image.setImage(chapter.getImage(imgno));
				imageLabel.setImage(image.Image,false);
				imageList.setSelectedIndex(imgno);
			}
		}
		else if(number==PREV_IMAGE)
		{
			if(imgno==0)
				changeChapter(PREV_CHAP);
			else
			{
				chapter.setImage_No(--imgno);
				image.setImage(chapter.getImage(imgno));
				imageLabel.setImage(image.Image,false);
				imageList.setSelectedIndex(imgno);
			}
		}
		else
		{
			chapter.setImage_No(number);
			image.setImage(chapter.getImage(number));
			imageLabel.setImage(image.Image,false);
		}
	}
	void changeChapter(int number)
	{
		int chapno=manga.getChapter_No();
		if(number==NEXT_CHAP)
		{
			if(chapno!=manga.getChapterCount()-1);
			{
				manga.setChapter_No(++chapno);
				chapter.setChapter(manga.getChapter(chapno));
				image.setImage(chapter.getImage(0));
				imageLabel.setImage(image.Image,false);
				int row=libman.getSelectionModel().getLeadSelectionRow();
				libman.setSelectionInterval(row+1, row+1);
			}
		}
		else if(number==PREV_CHAP)
			if(chapno!=0)
			{
				manga.setChapter_No(--chapno);
				chapter.setChapter(manga.getChapter(chapno));
				image.setImage(chapter.getImage(0));
				imageLabel.setImage(image.Image,false);
				int row=libman.getSelectionModel().getLeadSelectionRow();
				libman.setSelectionInterval(row-1, row-1);
			}
	}
	/**
	 * @param libman the libman to set
	 */
	public void setLibman(LibMan libman) {
		this.libman = libman;
	}
	/**
	 * @param imageList the imageList to set
	 */
	public void setImageList(ImageList<?> imageList) {
		this.imageList = imageList;
	}
	/**
	 * @param imageLabel the imageLabel to set
	 */
	public void setImageLabel(ImageLabel imageLabel) {
		this.imageLabel = imageLabel;
	}
	public String Currentimg()
	{
		return image.Image_Name;
	}
}