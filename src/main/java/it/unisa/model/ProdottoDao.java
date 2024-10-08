package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProdottoDao implements ProdottoDaoInterfaccia {

    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/buy_intelligently");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String TABLE_NAME = "prodotto";

    @Override
    public void doSave(ProdottoBean product) throws SQLException {
        String insertSQL = "INSERT INTO " + TABLE_NAME
            + " (NOME, CATEGORIA, DESCRIZIONE, PREZZO, QUANTITA, IN_VENDITA, IVA, IMMAGINE, TAGLIE, VENDITE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, product.getNome());
            preparedStatement.setString(2, product.getCategoria());
            preparedStatement.setString(3, product.getDescrizione());
            preparedStatement.setDouble(4, product.getPrezzo());
            preparedStatement.setInt(5, product.getQuantita());
            preparedStatement.setBoolean(6, product.isInVendita());
            preparedStatement.setString(7, product.getIva());
            preparedStatement.setString(8, product.getImmagine());
            preparedStatement.setString(9, product.getTaglie());
            preparedStatement.setInt(10, product.getvendite());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ProdottoBean doRetrieveByKey(int idProdotto) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_PRODOTTO = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, idProdotto);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToBean(rs);
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean doDelete(int idProdotto) throws SQLException {
        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE ID_PRODOTTO = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, idProdotto);

            int result = preparedStatement.executeUpdate();
            return (result != 0);
        }
    }

    @Override
    public ArrayList<ProdottoBean> doRetrieveAll(String order) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            ArrayList<ProdottoBean> products = new ArrayList<>();
            while (rs.next()) {
                products.add(mapResultSetToBean(rs));
            }
            return products;
        }
    }

    @Override
    public void doUpdateQnt(int id, int qnt) throws SQLException {
        String updateSQL = "UPDATE " + TABLE_NAME
                + " SET QUANTITA = QUANTITA - ? "
                + " WHERE ID_PRODOTTO = ? ";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, qnt);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void doUpdate(ProdottoBean product) throws SQLException {
        String updateSQL = "UPDATE " + TABLE_NAME
                + " SET NOME = ? , QUANTITA = ? , DESCRIZIONE = ?, PREZZO = ?, CATEGORIA = ?, IN_VENDITA = ?, IVA = ?, IMMAGINE = ?, TAGLIE = ?, VENDITE = ?"
                + " WHERE ID_PRODOTTO = ? ";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
        	 preparedStatement.setString(1, product.getNome());
        	 preparedStatement.setInt(2, product.getQuantita());
        	 preparedStatement.setString(3, product.getDescrizione());
             preparedStatement.setDouble(4, product.getPrezzo());
             preparedStatement.setString(5, product.getCategoria());
             preparedStatement.setBoolean(6, product.isInVendita());
             preparedStatement.setString(7, product.getIva());
             preparedStatement.setString(8, product.getImmagine());
             preparedStatement.setString(9, product.getTaglie());
             preparedStatement.setInt(10, product.getvendite());
             
             preparedStatement.setInt(11, product.getIdProdotto());
            

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<ProdottoBean> doRetrieveByCategoria(String categoria) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE CATEGORIA = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, categoria);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<ProdottoBean> prodotti = new ArrayList<>();
            while (rs.next()) {
                prodotti.add(mapResultSetToBean(rs));
            }
            return prodotti;
        }
    }
    
    @Override
 public ArrayList<ProdottoBean> doRetrieveBestSellers() throws SQLException {
    String selectSQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY VENDITE"; 

    try (Connection connection = ds.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

        ResultSet rs = preparedStatement.executeQuery();

        ArrayList<ProdottoBean> prodotti = new ArrayList<>();
        while (rs.next()) {
            prodotti.add(mapResultSetToBean(rs));
        }
        return prodotti;
    }
}
    @Override
    public ArrayList<ProdottoBean> doRetrieveLastBuy() throws SQLException {
        ArrayList<ProdottoBean> prodotti = new ArrayList<>();
        Set<Integer> prodottiVisti = new HashSet<>();  // Set per evitare duplicati

        // Ottenere la connessione dal DataSource
        try (Connection conn = ds.getConnection()) {

            // Prima query: Recuperiamo gli id_prodotto dalla tabella composizione
            String queryComposizione = "SELECT id_prodotto FROM composizione";
            try (PreparedStatement psComposizione = conn.prepareStatement(queryComposizione);
                 ResultSet rsComposizione = psComposizione.executeQuery()) {

                // Iteriamo su tutti gli id_prodotto trovati
                while (rsComposizione.next()) {
                    int idProdotto = rsComposizione.getInt("id_prodotto");

                    // Controlliamo se l'id_prodotto è già stato processato
                    if (!prodottiVisti.contains(idProdotto)) {
                        prodottiVisti.add(idProdotto);  // Aggiungiamo l'id al Set

                        // Seconda query: Recuperiamo i dettagli del prodotto dalla tabella prodotto
                        String queryProdotto = "SELECT id_prodotto, nome, descrizione, prezzo, quantita, iva, in_vendita, immagine, categoria, taglie, vendite FROM prodotto WHERE id_prodotto = ?";
                        try (PreparedStatement psProdotto = conn.prepareStatement(queryProdotto)) {
                            psProdotto.setInt(1, idProdotto);
                            try (ResultSet rsProdotto = psProdotto.executeQuery()) {

                                // Se troviamo il prodotto, lo aggiungiamo alla lista
                                if (rsProdotto.next()) {
                                    ProdottoBean prodotto = mapResultSetToBean(rsProdotto);
                                    prodotti.add(prodotto);
                                }
                            }
                        }
                    }
                }
            }
        } 
        return prodotti;
    }

    
	@Override
	public ArrayList<ProdottoBean> doRetrieveRandomProducts(int numProducts) throws SQLException {
	    String selectSQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY RAND() LIMIT ?";

	    try (Connection connection = ds.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

	        preparedStatement.setInt(1, numProducts);

	        ResultSet rs = preparedStatement.executeQuery();

	        ArrayList<ProdottoBean> prodotti = new ArrayList<>();
	        while (rs.next()) {
	            prodotti.add(mapResultSetToBean(rs));
	        }
	        return prodotti;
	    }
	}
	

    private ProdottoBean mapResultSetToBean(ResultSet rs) throws SQLException {
        ProdottoBean bean = new ProdottoBean();
        bean.setIdProdotto(rs.getInt("ID_PRODOTTO"));
        bean.setNome(rs.getString("NOME"));
        bean.setDescrizione(rs.getString("DESCRIZIONE"));
        bean.setPrezzo(rs.getDouble("PREZZO"));
        bean.setQuantita(rs.getInt("QUANTITA"));
        bean.setIva(rs.getString("IVA"));
        bean.setInVendita(rs.getBoolean("IN_VENDITA"));
        bean.setImmagine(rs.getString("IMMAGINE"));
        bean.setCategoria(rs.getString("CATEGORIA"));
        bean.setTaglie(rs.getString("TAGLIE"));
        bean.setvendite(rs.getInt("VENDITE"));
        
        return bean;
    }
}