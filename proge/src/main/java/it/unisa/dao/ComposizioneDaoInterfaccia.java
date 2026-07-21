package it.unisa.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.ComposizioneBean;

public interface ComposizioneDaoInterfaccia {

	public void doSave(ComposizioneBean composizione) throws SQLException;
	
	public ArrayList<ComposizioneBean> doRetrieveByOrdine(int idOrdine) throws SQLException;
}