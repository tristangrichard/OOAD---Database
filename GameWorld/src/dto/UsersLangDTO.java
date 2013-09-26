package dto;

public class UsersLangDTO
{
	private int Uid;
	private int Langid;

	public UsersLangDTO(int Uid, int Langid)
	{
		this.Uid = Uid;
		this.Langid = Langid;
	}

	public int getUid(){return Uid;}
	public void setUid(int Uid){this.Uid = Uid;}

	public int getLangid(){return Langid;}
	public void setLangid(int Langid){this.Langid = Langid;}
}

