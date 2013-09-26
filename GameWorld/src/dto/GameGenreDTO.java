package dto;

public class GameGenreDTO
{
	private int Gid;
	private int Genreid;

	public GameGenreDTO(int Gid, int Genreid)
	{
		this.Gid = Gid;
		this.Genreid = Genreid;
	}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}

	public int getGenreid(){return Genreid;}
	public void setGenreid(int Genreid){this.Genreid = Genreid;}
}

