package it.unisa.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.OrdineBean;

public interface OrdineDaoInterfaccia {

	public void doSave(OrdineBean ordine) throws SQLException;
	
	public OrdineBean doRetrieveByKey(int idOrdine) throws SQLException;
	
	public ArrayList<OrdineBean> doRetrieveByEmail(String email) throws SQLException;
	
	public ArrayList<OrdineBean> doRetrieveAll(String order) throws SQLException;
}