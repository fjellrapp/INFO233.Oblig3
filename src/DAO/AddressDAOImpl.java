package DAO;

import Entities.Address;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;


public class AddressDAOImpl {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();


    public Address accessAddress(int id) {

        Connection con = connector.connect();
        Address address = new Address();

        try{
            Statement stmnt = con.createStatement();
            ResultSet addrResults = stmnt.executeQuery("SELECT * FROM address " +
                    "WHERE address_id =" + id + ";");
            if (addrResults.next()){
                address.setAddressId(addrResults.getInt("address_id"));
                address.setStreetNumber(addrResults.getString("street_number"));
                address.setStreetName(addrResults.getString("street_name"));
                address.setPostalCode(addrResults.getString("postal_code"));
                address.setPostalTown(addrResults.getString("postal_town"));
            }
            }catch (SQLException e){
            System.out.println("Something went wrong. Message: " + e.getMessage());
        }finally {
            connector.disconnect();
        }
        return address;

        }

        public void addAddress(Address address){
            Connection conn = connector.connect();

            try{
                Statement statement = conn.createStatement();
                statement.setQueryTimeout(10);
                PreparedStatement prepStatement = conn.prepareStatement("INSERT INTO address (address_id, street_number, street_name, postal_code, postal_town)" +
                        "VALUES (?,?,?,?,?);");
                prepStatement.setInt(1, address.getAddressId());
                prepStatement.setString(2, address.getStreetNumber());
                prepStatement.setString(3, address.getStreetName());
                prepStatement.setString(4, address.getPostalCode());
                prepStatement.setString(5, address.getPostalTown());
                prepStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connector.disconnect();
            }
        }

        public void deleteAddress(int id){

            Connection con = connector.connect();
            try {
                Statement statement = con.createStatement();
                statement.setQueryTimeout(30);
                PreparedStatement prepStatement = con.prepareStatement("DELETE FROM address WHERE address_id = " + id + ";");
                prepStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connector.disconnect();
            }

        }




        }




