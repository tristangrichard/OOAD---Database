package dto;

public class DeveloperDTO
{
	private int Did;
	private String Developer;
	private String Founded;

	public DeveloperDTO(int Did, String Developer, String Founded)
	{
		this.Did = Did;
		this.Developer = Developer;
		this.Founded = Founded;
	}

	public int getDid(){return Did;}
	public void setDid(int Did){this.Did = Did;}

	public String getDeveloper(){return Developer;}
	public void setDeveloper(String Developer){this.Developer = Developer;}

	public String getFounded(){return Founded;}
	public void setFounded(String Founded){this.Founded = Founded;}
}

