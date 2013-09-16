package daointerfaces;

import java.util.List;

import dto.BrugerDTO;

public interface IOperatoerDAO {
	BrugerDTO getOperatoer(int oprId) throws DALException;
	List<BrugerDTO> getOperatoerList() throws DALException;
	void createOperatoer(BrugerDTO opr) throws DALException;
	void updateOperatoer(BrugerDTO opr) throws DALException;
}