package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dto.OperatoerDTO;
import daointerfaces.DALException;
import daointerfaces.IOperatoerDAO;
import funktionalitet.IOperatorLogic;
import funktionalitet.OperatorLogic;

public class TestOperatorLogic {
	private IOperatoerDAO mockDAO;
	private OperatoerDTO mockDTO;
	private IOperatorLogic testOL;

	@Before
	public void setUp() throws Exception {
		mockDAO = mock(IOperatoerDAO.class);
		mockDTO = mock(OperatoerDTO.class);
		
		testOL = new OperatorLogic(mockDAO);
	}

	@Test
	public void testIsAdminWhereTrue() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		assertTrue(testOL.isAdmin(1));
	}
	
	@Test
	public void testIsAdminWhereFalse() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("farmaceut");
		assertFalse(testOL.isAdmin(1));
	}

	@Test
	public void testCreateOprValid() throws DALException {
		testOL.createOpr(1, "Test Bruger", "1234567890", "administrator");
		verify(mockDAO).createOperatoer(any(OperatoerDTO.class));
	}
	
	@Test(expected=DALException.class)
	public void testCreateOprInvalidCPR() throws DALException {
		testOL.createOpr(1, "Test Bruger", "123456789", "administrator");
	}
	
	@Test(expected=DALException.class)
	public void testCreateOprInvalidName() throws DALException {
		testOL.createOpr(1, "TestBruger", "123456789", "administrator");
	}

	@Test(expected=DALException.class)
	public void testUpdateOprInvalidNewPass() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDTO.getPassword()).thenReturn("12345");
		
		testOL.updateOpr(1, "Test Bruger", "1234567890", "12345", "123456", "123456", "administrator");
	}
	
	@Test(expected=DALException.class)
	public void testUpdateOprInvalidOldPass() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDTO.getPassword()).thenReturn("1234");
		
		testOL.updateOpr(1, "Test Bruger", "1234567890", "12345", "02324it!", "02324it!", "administrator");
	}
	
	@Test(expected=DALException.class)
	public void testUpdateOprTwoPassNotSame() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDTO.getPassword()).thenReturn("12345");
		
		testOL.updateOpr(1, "Test Bruger", "1234567890", "12345", "0232it!", "02324it!", "administrator");
	}
	
	@Test
	public void testUpdateOpr() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDTO.getPassword()).thenReturn("12345");
		
		testOL.updateOpr(1, "Test Bruger", "1234567890", "12345", "02324it!", "02324it!", "administrator");
		verify(mockDAO).updateOperatoer(any(OperatoerDTO.class));
	}

	@Test
	public void testDeleteOpr() throws DALException {
		OperatoerDTO mockDTO2 = mock(OperatoerDTO.class);
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDAO.getOperatoer(2)).thenReturn(mockDTO2);
		when(mockDTO2.getRolle()).thenReturn("operatoer");
		
		testOL.deleteOpr(1, 2);
		verify(mockDTO2).setRolle("inaktiv");
		verify(mockDAO).updateOperatoer(mockDTO2);
	}
	
	@Test(expected=DALException.class)
	public void testDeleteOprNotAdmin() throws DALException {
		OperatoerDTO mockDTO2 = mock(OperatoerDTO.class);
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		when(mockDTO.getRolle()).thenReturn("administrator");
		when(mockDAO.getOperatoer(2)).thenReturn(mockDTO2);
		when(mockDTO2.getRolle()).thenReturn("operatoer");
		
		testOL.deleteOpr(2, 1);
		verify(mockDTO2).setRolle("inaktiv");
		verify(mockDAO).updateOperatoer(mockDTO2);
	}

	@Test
	public void testGetOperatoerList() throws DALException {
		OperatoerDTO mockDTO2 = mock(OperatoerDTO.class);
		List<OperatoerDTO> mockList = new ArrayList<OperatoerDTO>();
		mockList.add(mockDTO);
		mockList.add(mockDTO2);
		
		when(mockDAO.getOperatoerList()).thenReturn(mockList);
		assertEquals(mockList, testOL.getOperatoerList());
	}

	@Test
	public void testGetOperatoer() throws DALException {
		when(mockDAO.getOperatoer(1)).thenReturn(mockDTO);
		assertEquals(mockDTO, testOL.getOperatoer(1));
		verify(mockDAO).getOperatoer(1);
	}
}