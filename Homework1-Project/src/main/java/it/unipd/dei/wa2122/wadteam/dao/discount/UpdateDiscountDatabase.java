package it.unipd.dei.wa2122.wadteam.dao.discount;


import it.unipd.dei.wa2122.wadteam.resources.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDiscountDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE Discount SET Percentage = ?, Start_Date = ?, End_Date = ? WHERE ID = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The id of the discount
     */
    private final Discount discount;

    /**
     * Update a discount item.
     *
     * @param con
     *            the connection to the database.
     * @param discount
     *            the discount to be update.
     */
    public UpdateDiscountDatabase(final Connection con, final Discount discount) {
        this.con = con;
        this.discount = discount;
    }

    /**
     * Reads a discount from the database.
     *
     * @return the {@code Discount} object matching the badge.
     *
     * @throws SQLException
     *             if any error occurs while reading the employee.
     */
    public Discount updateDiscount() throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // the read discount
        Discount resultDiscount = null;

        try {
            preparedStatement = con.prepareStatement(STATEMENT);
            preparedStatement.setInt(2, discount.getPercentage());
            preparedStatement.setString(3, discount.getEndDate());
            preparedStatement.setString(4, discount.getEndDate());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultDiscount = new Discount(
                        resultSet.getInt("ID"),
                        resultSet.getInt("Percentage"),
                        resultSet.getString("Start_Date"),
                        resultSet.getString("End_Date")
                );
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return resultDiscount;
    }
}