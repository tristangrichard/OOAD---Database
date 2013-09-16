package connector;


// erstat konstanterne nedenfor

public abstract class Constant
{
	public static final String
		server					= "127.0.0.1",  // database-serveren
		database				=  "gameworld",  //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "client", // dit brugernavn = dit studienummer 
		password				= "1234"; // dit password som du har valgt til din database
	
	public static final int
		port					= 3306;
}
