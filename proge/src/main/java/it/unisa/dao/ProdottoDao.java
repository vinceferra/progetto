package it.unisa.dao;

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

import it.unisa.model.ProdottoBean;

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
    public ProdottoBean doRetrieveByIdOrdine(int idProdotto) throws SQLException {

        String sql = "SELECT * FROM prodotto WHERE ID_PRODOTTO = ?";

        try(Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, idProdotto);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return mapResultSetToBean(rs);
            }

        }

        return null;
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
    public ArrayList<ProdottoBean> doRetrieveAllDisponibili(String order) throws SQLException {

        String selectSQL = "SELECT * FROM prodotto WHERE IN_VENDITA = true";

        if(order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try(Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(selectSQL);
            ResultSet rs = ps.executeQuery()) {

            ArrayList<ProdottoBean> prodotti = new ArrayList<>();

            while(rs.next()) {
                prodotti.add(mapResultSetToBean(rs));
            }

            return prodotti;
        }
    }

    public ArrayList<ProdottoBean> doRetrieveAllProducts() throws SQLException {

        String selectSQL = "SELECT * FROM prodotto";

        try(Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery()) {

            ArrayList<ProdottoBean> prodotti = new ArrayList<>();

            while(rs.next()) {
                prodotti.add(mapResultSetToBean(rs));
            }

            return prodotti;
        }
    }
    
    
    @Override
    public void doUpdateQnt(int id, int qnt) throws SQLException {

        String updateSQL =
            "UPDATE prodotto " +  "SET QUANTITA = QUANTITA - ?, " +
            "VENDITE = VENDITE + ? " + "WHERE ID_PRODOTTO = ? " +
            "AND QUANTITA >= ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement =
                 connection.prepareStatement(updateSQL)) {

            preparedStatement.setInt(1, qnt);
            preparedStatement.setInt(2, qnt);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, qnt);

            int righeModificate = preparedStatement.executeUpdate();

            if (righeModificate == 0) {
                throw new SQLException(
                    "Quantità insufficiente o prodotto non trovato"
                );
            }
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
    public void doUpdateVendita(int idProdotto, boolean inVendita) throws SQLException {

        String updateSQL = 
            "UPDATE prodotto SET IN_VENDITA = ? WHERE ID_PRODOTTO = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setBoolean(1, inVendita);
            preparedStatement.setInt(2, idProdotto);

            preparedStatement.executeUpdate();
        }
    }
    
    
    @Override
    public ArrayList<ProdottoBean> doRetrieveByCategoria(String categoria) throws SQLException {
    	String selectSQL ="SELECT * FROM " + TABLE_NAME + " WHERE CATEGORIA = ? AND IN_VENDITA = true";

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

    	String selectSQL =
    			"SELECT p.* " +
    			"FROM prodotto p " +
    			"JOIN composizione c ON p.ID_PRODOTTO = c.ID_PRODOTTO " +
    			"WHERE p.IN_VENDITA = true " +
    			"GROUP BY p.ID_PRODOTTO " +
    			"ORDER BY SUM(c.QUANTITA) DESC " +
    			"LIMIT 5";

        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            ArrayList<ProdottoBean> prodotti = new ArrayList<>();

            while (rs.next()) {
                prodotti.add(mapResultSetToBean(rs));
            }

            return prodotti;
        }
    }
    @Override
    public ArrayList<ProdottoBean> doRetrieveLastBuy(String email) throws SQLException {

        ArrayList<ProdottoBean> prodotti = new ArrayList<>();
        Set<Integer> prodottiVisti = new HashSet<>();

        String sql =
            "SELECT p.* " +
            "FROM prodotto p " +
            "JOIN composizione c ON p.ID_PRODOTTO = c.ID_PRODOTTO " +
            "JOIN ordine o ON c.ID_ORDINE = o.ID_ORDINE " +
            "WHERE o.EMAIL = ? " +
            "ORDER BY o.DATA_ORDINE DESC, o.ID_ORDINE DESC";

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("ID_PRODOTTO");

                    if (prodottiVisti.add(id)) {
                        prodotti.add(mapResultSetToBean(rs));
                    }
                }
            }
        }

        return prodotti;
    }
    
    @Override
    public ArrayList<ProdottoBean> doRetrieveRecommendedProducts(String email)throws SQLException {
        ArrayList<ProdottoBean> prodotti = new ArrayList<>();
        Set<Integer> idVisti = new HashSet<>();

        String sql =
            "SELECT p.*, preferenze.totale_acquistato " +
            "FROM prodotto p " +
            "JOIN ( " +
            "   SELECT p2.CATEGORIA, SUM(c.QUANTITA) AS totale_acquistato " +
            "   FROM ordine o " +
            "   JOIN composizione c ON o.ID_ORDINE = c.ID_ORDINE " +
            "   JOIN prodotto p2 ON c.ID_PRODOTTO = p2.ID_PRODOTTO " +
            "   WHERE o.EMAIL = ? " +
            "   GROUP BY p2.CATEGORIA " +
            ") preferenze ON p.CATEGORIA = preferenze.CATEGORIA " +
            "WHERE p.IN_VENDITA = true " +
            "AND p.QUANTITA > 0 " +
            "ORDER BY preferenze.totale_acquistato DESC, RAND()";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int id = rs.getInt("ID_PRODOTTO");

                    if (idVisti.add(id)) {
                        prodotti.add(mapResultSetToBean(rs));
                    }
                }
            }
        }
        return prodotti;
    }
    
    
    
    @Override
    public ArrayList<ProdottoBean> doRetrieveRandomProducts()
            throws SQLException {

        String sql =
            "SELECT * FROM prodotto " +
            "WHERE IN_VENDITA = true " +
            "AND QUANTITA > 0 " +
            "ORDER BY RAND()";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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