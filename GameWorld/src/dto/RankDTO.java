package dto;

public class RankDTO
{
	private String gName;
	private int count;
	private int woman;
	private int men;
	public RankDTO(String gName, int count, int woman, int men)
	{
		this.count = count;
		this.gName = gName;
		this.woman = woman;
		this.men = men;
	}
	public int getCount(){return count;}
	public String getGname(){return gName;}
	public int getWoman(){return woman;}
	public int getMen(){return men;}
}
