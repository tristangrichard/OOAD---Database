package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class GenreDTO
{
	private int Genreid;
	private String Genre;

	public GenreDTO(int Genreid, String Genre)
	{
		this.Genreid = Genreid;
		this.Genre = Genre;
	}

	public int getGenreid(){return Genreid;}
	public void setGenreid(int Genreid){this.Genreid = Genreid;}

	public String getGenre(){return Genre;}
	public void setGenre(String Genre){this.Genre = Genre;}
}

