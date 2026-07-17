package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProdottoDaoInterfaccia {

	public void doSave(ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int idProdotto) throws SQLException;

	public ProdottoBean doRetrieveByKey(int idProdotto) throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveAll(String order) throws SQLException;
	
	public void doUpdateQnt(int id, int qnt) throws SQLException;
	
	public void doUpdate(ProdottoBean bean) throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveByCategoria(String Categoria) throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveBestSellers() throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveRandomProducts() throws SQLException;
	
	public ArrayList<ProdottoBean> doRetrieveLastBuy(String email) throws SQLException;
	
	public void doUpdateVendita(int idProdotto, boolean inVendita) throws SQLException;
	
	ArrayList<ProdottoBean> doRetrieveAllDisponibili(String order) throws SQLException;
	
	ProdottoBean doRetrieveByIdOrdine(int idProdotto) throws SQLException;
	
	ArrayList<ProdottoBean> doRetrieveAllProducts() throws SQLException;
	ArrayList<ProdottoBean> doRetrieveRecommendedProducts(String email) throws SQLException;
}
