package it.unipd.dei.wa2122.wadteam.dao.product;

import it.unipd.dei.wa2122.wadteam.resources.Product;
import it.unipd.dei.wa2122.wadteam.resources.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteProductDatabase {
    /**
     * The SQL statements to be executed
     */
    private static final String STATEMENT_DELETE_PRODUCT = "DELETE FROM product WHERE product_alias = ? RETURNING product_alias, name, brand, description, quantity, purchase_price, sale_price, category_name, evidence";

    private static final String STATEMENT_DELETE_PICTURE = "DELETE FROM rappresented_by WHERE product_alias = ? AND id_media = ? RETURNING product_alias, id_media";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The product to be deleted
     */
    private final Product product;

    /**
     * Creates a new object for deleting a product.
     *
     * @param con
     *            the connection to the database.
     * @param product
     *            the product.
     */
    public DeleteProductDatabase(final Connection con, final Product product){
        this.con = con;
        this.product = product;
    }

    /**
     * Deletes a product from the database.
     *
     * @return the {@code Product} object matching the alias.
     *
     * @throws SQLException
     *             if any error occurs while deleting the media.
     */
    public Product deleteProduct() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Product resultProduct = null;

        try {
            preparedStatement = con.prepareStatement(STATEMENT_DELETE_PRODUCT);
            preparedStatement.setString(1,product.getAlias());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                resultProduct = new Product(
                        resultSet.getString("product_alias"),
                        resultSet.getString("name"),
                        resultSet.getString("brand"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("purchase_price"),
                        resultSet.getDouble("sale_price"),
                        new ProductCategory(resultSet.getString("category_name"),null),
                        resultSet.getBoolean("evidence"),
                        new ArrayList<>());
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        for (var item: product.getPicture()){
            try {
                preparedStatement = con.prepareStatement(STATEMENT_DELETE_PICTURE);
                preparedStatement.setString(1,product.getAlias());
                preparedStatement.setInt(2,item);

                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    assert resultProduct != null;
                    resultProduct.getPicture().add(resultSet.getInt("id_media"));
                }
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
        }

        return resultProduct;
    }
}
