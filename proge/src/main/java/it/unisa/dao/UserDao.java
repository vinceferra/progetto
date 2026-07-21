package it.unisa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.control.PasswordUtil;
import it.unisa.model.UserBean;

public class UserDao implements UserDaoInterfaccia {

    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/buy_intelligently");
        } catch (NamingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static final String TABLE_NAME = "cliente";

    @Override
    public synchronized void doSave(UserBean user) throws SQLException {

        String insertSQL =
                "INSERT INTO " + TABLE_NAME +
                " (NOME, COGNOME, USERNAME, PWD, EMAIL, DATA_NASCITA, CARTA_CREDITO, INDIRIZZO, CAP, AMMINISTRATORE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertSQL)) {

            connection.setAutoCommit(false);

            ps.setString(1, user.getNome());
            ps.setString(2, user.getCognome());
            ps.setString(3, user.getUsername());

            // 🔐 HASH PASSWORD
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            ps.setString(4, hashedPassword);

            ps.setString(5, user.getEmail());
            ps.setDate(6, (Date) user.getDataDiNascita());
            ps.setString(7, user.getCartaDiCredito());
            ps.setString(8, user.getIndirizzo());
            ps.setString(9, user.getCap());
            ps.setBoolean(10, user.isAmministratore());

            ps.executeUpdate();
            connection.commit();
        }
    }
    @Override
    public synchronized UserBean doRetrieve(String username, String hashedPassword) throws SQLException {

        UserBean user = new UserBean();

        String query =
                "SELECT * FROM " + TABLE_NAME +
                " WHERE USERNAME = ? AND PWD = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, hashedPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataDiNascita(rs.getDate("data_nascita"));
                user.setCartaDiCredito(rs.getString("carta_credito"));
                user.setIndirizzo(rs.getString("indirizzo"));
                user.setCap(rs.getString("cap"));
                user.setAmministratore(rs.getBoolean("amministratore"));
                user.setValid(true);
            } else {
                user.setValid(false);
            }
        }

        return user;
    }
    @Override
    public synchronized ArrayList<UserBean> doRetrieveAll(String order) throws SQLException {

        ArrayList<UserBean> users = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectSQL)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setDataDiNascita(rs.getDate("data_nascita"));
                user.setCartaDiCredito(rs.getString("carta_credito"));
                user.setIndirizzo(rs.getString("indirizzo"));
                user.setCap(rs.getString("cap"));
                user.setAmministratore(rs.getBoolean("amministratore"));
                user.setValid(true);
                users.add(user);
            }
        }

        return users;
    }
    
    public synchronized void doUpdateSpedizione(String email, String indirizzo, String cap) throws SQLException {

        String updateSQL =
                "UPDATE " + TABLE_NAME +
                " SET INDIRIZZO = ?, CAP = ? WHERE EMAIL = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSQL)) {

            connection.setAutoCommit(false);

            ps.setString(1, indirizzo);
            ps.setString(2, cap);
            ps.setString(3, email);

            ps.executeUpdate();
            connection.commit();
        }
    }
    
    public synchronized void doUpdatePagamento(String email, String carta) throws SQLException {

        String updateSQL =
                "UPDATE " + TABLE_NAME +
                " SET CARTA_CREDITO = ? WHERE EMAIL = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSQL)) {

            connection.setAutoCommit(false);

            ps.setString(1, carta);
            ps.setString(2, email);

            ps.executeUpdate();
            connection.commit();
        }
    }

    public synchronized void doUpdatePassword(String email, String newPassword) throws SQLException {

        String updateSQL =
                "UPDATE " + TABLE_NAME +
                " SET PWD = ? WHERE EMAIL = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateSQL)) {

            connection.setAutoCommit(false);

            // 🔐 HASH NUOVA PASSWORD
            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            ps.setString(1, hashedPassword);
            ps.setString(2, email);

            ps.executeUpdate();
            connection.commit();
        }
    }

    public Boolean checkPassword(String email, String plainPassword) throws SQLException {

        String query =
                "SELECT PWD FROM " + TABLE_NAME + " WHERE EMAIL = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("PWD");
                String inputHash = PasswordUtil.hashPassword(plainPassword);
                return storedHash.equals(inputHash);
            }
        }

        return false;
    }
}


