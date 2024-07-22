package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ComposizioneDaoInterfaccia {

	public void doSave(ComposizioneBean composizione) throws SQLException;
	
	public ArrayList<ComposizioneBean> doRetrieveByOrdine(int idOrdine) throws SQLException;
}