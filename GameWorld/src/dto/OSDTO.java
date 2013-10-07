package dto;

public class OSDTO
{
	private int OSid;
	private String OS;

	public OSDTO(int OSid, String OS)
	{
		this.OSid = OSid;
		this.OS = OS;
	}

	public int getOsid(){return OSid;}
	public void setOsid(int OSid){this.OSid = OSid;}

	public String getOs(){return OS;}
	public void setOs(String OS){this.OS = OS;}
}

