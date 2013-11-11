package dto;

public class RankDTO
{
	private String gName;
	private int count;
	public RankDTO(String gName, int count)
	{
		this.count = count;
		this.gName = gName;
	}
	public int getCount(){return count;}
	public String getGname(){return gName;}
}
