package dto;

public class UsersGamesDTO
{
	private int Uid;
	private int Gid;

	public UsersGamesDTO(int Uid, int Gid)
	{
		this.Uid = Uid;
		this.Gid = Gid;
	}

	public int getUid(){return Uid;}
	public void setUid(int Uid){this.Uid = Uid;}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}
}

