package it.unisa.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.UserBean;

public interface UserDaoInterfaccia {

	public void doSave(UserBean user) throws SQLException;
	
	public UserBean doRetrieve(String username, String password) throws SQLException;
	
	public ArrayList<UserBean> doRetrieveAll(String order) throws SQLException;

	public void doUpdateSpedizione(String email, String indirizzo, String cap) throws SQLException;
	
	public void doUpdatePagamento(String email, String carta) throws SQLException;
	
	public void doUpdatePassword(String email,String newpassword ) throws SQLException;
	
	public Boolean checkPassword(String email, String password) throws SQLException;
}