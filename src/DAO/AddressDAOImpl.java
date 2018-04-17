package DAO;

import Entities.Address;
import Entities.Invoice;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class AddressDAOImpl {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();


    /**
     * Gir tilgang til et element basert på dens addresse ID
     *
     * @param id, IDen til en addresse
     * @return Addressen
     */
    public Address accessAddress(int id) {

        Connection con = connector.connect();
        Address address = new Address();

        try {
            Statement stmnt = con.createStatement();
            ResultSet addrResults = stmnt.executeQuery("SELECT * FROM address " +
                    "WHERE address_id =" + id + ";");
            if (addrResults.next()) {
                address.setAddressId(addrResults.getInt("address_id"));
                address.setStreetNumber(addrResults.getString("street_number"));
                address.setStreetName(addrResults.getString("street_name"));
                address.setPostalCode(addrResults.getString("postal_code"));
                address.setPostalTown(addrResults.getString("postal_town"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong. Message: " + e.getMessage());
        } finally {
            connector.disconnect();
        }
        return address;

    }

    /**
     * Legger til en addresse til databasen
     *
     * @param address, addressen som skal inn i databasen
     */

    public void addAddress(Address address) {
        Connection conn = connector.connect();
        String query = "INSERT INTO address (address_id, street_number, street_name, postal_code, postal_town)" +
                "VALUES (?,?,?,?,?);";

        try {
            PreparedStatement prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, address.getAddressId());
            prepStatement.setString(2, address.getStreetNumber());
            prepStatement.setString(3, address.getStreetName());
            prepStatement.setString(4, address.getPostalCode());
            prepStatement.setString(5, address.getPostalTown());
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Sletter en addresse fra databasen basert på dens ID
     *
     * @param id ID til addressen.
     */
    public void deleteAddress(int id) {

        Connection con = connector.connect();
        try {
            PreparedStatement prepStatement = con.prepareStatement("DELETE FROM address WHERE address_id = " + id + ";");
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

    }

    /**
     * Henter ut alle addresser i databasen og returnerer dem i en liste.
     *
     * @return Liste med alle addresser i databasen.
     */

    public List<Address> getAllAddresses() {

        Connection conn = connector.connect();
        List<Address> all = new LinkedList<>();

        String query = "SELECT * FROM address;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Address address = new Address();
                address.setAddressId(resultSet.getInt("address_id"));
                address.setStreetNumber(resultSet.getString("street_number"));
                address.setStreetName(resultSet.getString("street_name"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setPostalTown(resultSet.getString("postal_town"));
                all.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return all;
    }

    /**
     * Gjør det mulig å endre på en addresse.
     *
     * @param address, addressen som skal endres.
     */

    public void editAddress(Address address) {
        Connection conn = connector.connect();
        String query = "UPDATE address SET " +
                "street_number = ?," +
                "street_name = ?, " +
                "postal_code = ?," +
                "postal_town = ?" +
                "WHERE address_id = " + address.getAddressId() + ";";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, address.getStreetNumber());
            statement.setString(2, address.getStreetName());
            statement.setString(3, address.getPostalCode());
            statement.setString(4, address.getPostalTown());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }


}




