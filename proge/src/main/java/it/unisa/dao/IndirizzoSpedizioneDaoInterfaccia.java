package it.unisa.dao;

import java.sql.SQLException;

import it.unisa.model.IndirizzoSpedizioneBean;

public interface IndirizzoSpedizioneDaoInterfaccia {

	public void doSave(IndirizzoSpedizioneBean bean) throws SQLException;
	
	public void doUpdate(IndirizzoSpedizioneBean bean) throws SQLException;
	
	public IndirizzoSpedizioneBean doRetrieveByKey(String indirizzo, String cap) throws SQLException;
	
	public void doDelete(IndirizzoSpedizioneBean bean) throws SQLException;
	
}
