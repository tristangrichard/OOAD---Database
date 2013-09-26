package dto;

public class GameOSDTO
{
	private int OSid;
	private int Gid;

	public GameOSDTO(int OSid, int Gid)
	{
		this.OSid = OSid;
		this.Gid = Gid;
	}

	public int getOsid(){return OSid;}
	public void setOsid(int OSid){this.OSid = OSid;}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}
}

