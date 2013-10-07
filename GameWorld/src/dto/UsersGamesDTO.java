package dto;

public class UsersGamesDTO
{
	private String email;
	private int Gid;

	public UsersGamesDTO(String email, int Gid)
	{
		this.email = email;
		this.Gid = Gid;
	}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public int getGid(){return Gid;}
	public void setGid(int Gid){this.Gid = Gid;}
}

