package it.unipd.dei.wa2122.wadteam.dao.discount;

import it.unipd.dei.wa2122.wadteam.resources.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateDiscountDatabase {

    /**
     * The SQL statement to be executed
     */


    private static final String STATEMENT = "INSERT INTO Discount (Percentage, Start_Date, End_Date) VALUES (?, ?, ?) RETURNING ID, Percentage, Start_Date, End_Date";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The discount to be updated in the database
     */
    private final Discount discount;

    /**
     * Creates a new object for update a discount.
     *
     * @param con             the connection to the database.
     * @param discount the discount to be created in the database.
     */
    public CreateDiscountDatabase(final Connection con, final Discount discount) {
        this.con = con;
        this.discount = discount;
    }

    /**
     * Creates a discount in the database.
     *
     * @return the {@code Discount} object matching the badge.
     * @throws SQLException if any error occurs while reading the discount .
     */
    public Discount createDiscount() throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // the create discount
        Discount resultDiscount = null;

        try {
            preparedStatement = con.prepareStatement(STATEMENT);
            preparedStatement.setInt(1, resultDiscount.getPercentage());
            preparedStatement.setString(2, resultDiscount.getStartDate());// TODO: impostare data e non stringa
            preparedStatement.setString(3, resultDiscount.getEndDate());// TODO: impostare data e non stringa

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultDiscount = new Discount(resultSet.getInt("ID"),
                        resultSet.getInt("Percentage"),
                        resultSet.getString("Start_Date"),
                        resultSet.getString("End_Date"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        con.close();
        return resultDiscount;
    }
}