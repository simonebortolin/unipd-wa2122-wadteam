package it.unipd.dei.wa2122.wadteam.dao.productCategory;

import it.unipd.dei.wa2122.wadteam.resources.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductCategoryDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "SELECT name, description FROM Product_Category WHERE name = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The productCategory to be created in the database
     */
    private final String category;

    /**
     * Creates a new object for update a productCategory.
     *
     * @param con
     *            the connection to the database.
     * @param category
     *            the productCategory to be created in the database.
     */
    public GetProductCategoryDatabase(final Connection con, final String category) {
        this.con = con;
        this.category = category;
    }

    /**
     * Gets a productCategory from the database.
     *
     * @return the {@code ProductCategory} object matching the name.
     * @throws SQLException if any error occurs while retrieving the product.
     */
    public ProductCategory getProductCategory() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ProductCategory resultCategory = null;

        try {
            preparedStatement = con.prepareStatement(STATEMENT);
            preparedStatement.setString(1,category);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
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

        return resultCategory;
    }
}
