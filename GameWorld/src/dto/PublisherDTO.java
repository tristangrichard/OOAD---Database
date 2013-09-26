package dto;

public class PublisherDTO
{
	private int Pid;
	private String Publisher;
	private String Founded;

	public PublisherDTO(int Pid, String Publisher, String Founded)
	{
		this.Pid = Pid;
		this.Publisher = Publisher;
		this.Founded = Founded;
	}

	public int getPid(){return Pid;}
	public void setPid(int Pid){this.Pid = Pid;}

	public String getPublisher(){return Publisher;}
	public void setPublisher(String Publisher){this.Publisher = Publisher;}

	public String getFounded(){return Founded;}
	public void setFounded(String Founded){this.Founded = Founded;}
}

