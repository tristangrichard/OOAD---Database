package dto;

public class RoleDTO
{
	private int Uid;
	private String Role;

	public RoleDTO(int Uid, String Role)
	{
		this.Uid = Uid;
		this.Role = Role;
	}

	public int getUid(){return Uid;}
	public void setUid(int Uid){this.Uid = Uid;}

	public String getRole(){return Role;}
	public void setRole(String Role){this.Role = Role;}
}

