package dto;
/**
 * 
 * @author Rasmus Hansen, Tristan Richard
 *
 */
public class DeveloperDTO
{
	private int Did;
	private String Developer;
	private String country;

	public DeveloperDTO(int Did, String Developer, String Founded)
	{
		this.Did = Did;
		this.Developer = Developer;
		this.country = Founded;
	}

	public int getDid(){return Did;}
	public void setDid(int Did){this.Did = Did;}

	public String getDeveloper(){return Developer;}
	public void setDeveloper(String Developer){this.Developer = Developer;}

	public String getFounded(){return country;}
	public void setFounded(String Founded){this.country = Founded;}
}

