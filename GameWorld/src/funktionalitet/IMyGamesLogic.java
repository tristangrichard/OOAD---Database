package funktionalitet;

import java.util.List;

import daointerfaces.DALException;
import dto.GameDTO;
import dto.UsersGamesDTO;

public interface IMyGamesLogic {
	
		public void addGame(UsersGamesDTO game) throws DALException;
		public List<GameDTO> getMyGames(String email) throws DALException;
}
