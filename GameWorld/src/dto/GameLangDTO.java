package dto;

public class GameLangDTO
{
	private int Gid;
	private int Langid;

	public GameLangDTO(int Gid, int Langid)
	{
		this.Gid = Gid;
		this.Langid = Langid;
	}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}

	public int getLangid(){return Langid;}
	public void setLangid(int Langid){this.Langid = Langid;}
}

