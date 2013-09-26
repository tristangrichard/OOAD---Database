package dto;

public class LangDTO
{
	private int Langid;
	private String Lang;

	public LangDTO(int Langid, String Lang)
	{
		this.Langid = Langid;
		this.Lang = Lang;
	}

	public int getLangid(){return Langid;}
	public void setLangid(int Langid){this.Langid = Langid;}

	public String getLang(){return Lang;}
	public void setLang(String Lang){this.Lang = Lang;}
}

