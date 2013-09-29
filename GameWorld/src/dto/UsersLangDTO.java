package dto;

public class UsersLangDTO
{
	private String email;
	private int Langid;

	public UsersLangDTO(String email, int Langid)
	{
		this.email = email;
		this.Langid = Langid;
	}

	public String getEmail(){return email;}
	public void setUid(String email){this.email = email;}

	public int getLangid(){return Langid;}
	public void setLangid(int Langid){this.Langid = Langid;}
}

