package dto;

public class GameDTO
{
	private int Gid;
	private String Gname;
	private String Released;

	public GameDTO(int Gid, String Gname, String Released)
	{
		this.Gid = Gid;
		this.Gname = Gname;
		this.Released = Released;
	}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}

	public String getGname(){return Gname;}
	public void setGname(String Gname){this.Gname = Gname;}

	public String getReleased(){return Released;}
	public void setReleased(String Released){this.Released = Released;}
}

