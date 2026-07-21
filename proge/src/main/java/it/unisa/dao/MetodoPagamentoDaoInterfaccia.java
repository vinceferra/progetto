package it.unisa.dao;

import java.sql.SQLException;

import it.unisa.model.MetodoPagamentoBean;

public interface MetodoPagamentoDaoInterfaccia {

	public void doSave(MetodoPagamentoBean bean) throws SQLException;
	
	public MetodoPagamentoBean doRetrieveByKey(String numeroCarta) throws SQLException;
	
	public void doDelete(MetodoPagamentoBean bean) throws SQLException;
}
