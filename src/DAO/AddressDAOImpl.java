package DAO;

import Entities.Address;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
            connector.disconnectDatabase();
        }
        return address;

        }

        }




