package dto;

public class GamePubDTO
{
	private int Pid;
	private int Gid;

	public GamePubDTO(int Pid, int Gid)
	{
		this.Pid = Pid;
		this.Gid = Gid;
	}

	public int getPid(){return Pid;}
	public void setPid(int Pid){this.Pid = Pid;}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}
}

