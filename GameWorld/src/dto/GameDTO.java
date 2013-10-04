package dto;

public class GameDTO
{
	private int Gid;
	private String Gname;
	private String Released;
	private String Url;

	public GameDTO(int Gid, String Gname, String Released, String url)
	{
		this.Gid = Gid;
		this.Gname = Gname;
		this.Released = Released;
		this.Url = url;
	}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}

	public String getGname(){return Gname;}
	public void setGname(String Gname){this.Gname = Gname;}

	public String getReleased(){return Released;}
	public void setReleased(String Released){this.Released = Released;}
	
	public String getUrl(){return Url;}
	public void setUrl(String url){this.Url = url;}
}

