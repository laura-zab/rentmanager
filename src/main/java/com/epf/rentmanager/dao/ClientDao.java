package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.modele.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	
	public long create(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ResultSet resultSet = ps.getGeneratedKeys();
			int id = resultSet.getInt(1);

			ps.execute();

			ps.close();
			connection.close();
			return id;

		} catch (SQLException e) {
			throw new DaoException();
		}

	}
	
	public long delete(Client client) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);

			ps.setLong(1, client.getId());

			ps.execute();

			ps.close();
			connection.close();
			return client.getId();

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Client findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_CLIENT_QUERY);

			pstatement.setLong(1, id);

			ResultSet rs = pstatement.executeQuery();

			rs.next();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			LocalDate naissance = rs.getDate("naissance").toLocalDate();

			return new Client(id, nom, prenom, email, naissance);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try{
			Connection connection= ConnectionManager.getConnection();

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);

			while (rs.next()){
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();

				clients.add(new Client(id, nom, prenom, email, naissance));

			}

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}

}
