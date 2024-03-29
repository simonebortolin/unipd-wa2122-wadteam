package it.unipd.dei.wa2122.wadteam.dao.product;

import it.unipd.dei.wa2122.wadteam.resources.DateTime;
import it.unipd.dei.wa2122.wadteam.resources.Discount;
import it.unipd.dei.wa2122.wadteam.resources.Product;
import it.unipd.dei.wa2122.wadteam.resources.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListProductDatabase {
    /**
     * The SQL statements to be executed
     */
    private static final String STATEMENT_LIST_PRODUCT = "SELECT p.product_alias, p.name, p.brand, p.description, p.quantity, p.purchase_price, p.sale_price, p.category_name, p.evidence, d.*  FROM product AS p LEFT JOIN (SELECT DISTINCT ON (o.product_alias) o.product_alias, d.* FROM owns o LEFT JOIN discount d on o.id_discount = d.id WHERE d.start_date <= CURRENT_DATE AND d.end_date >= CURRENT_DATE ORDER BY o.product_alias, d.percentage DESC) as d ON d.product_alias = p.product_alias ORDER BY p.product_alias";

    private static final String STATEMENT_LIST_PICTURE = "SELECT id_media FROM Represented_by WHERE product_alias = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for reading a product.
     *
     * @param con the connection to the database.
     */
    public ListProductDatabase(final Connection con) {
        this.con = con;
    }

    /**
     * Lists all the products in the database
     *
     * @return the {@code Product}
     * @throws SQLException if any error occurs while listing the products.
     */
    public List<Product> getProduct() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Product resultProductItem;
        List<Product> resultProduct = new ArrayList<>();

        try {
            preparedStatement = con.prepareStatement(STATEMENT_LIST_PRODUCT);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultProductItem = new Product(
                        resultSet.getString("product_alias"),
                        resultSet.getString("name"),
                        resultSet.getString("brand"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("purchase_price"),
                        resultSet.getDouble("sale_price"),
                        new ProductCategory(resultSet.getString("category_name"), null),
                        resultSet.getBoolean("evidence"),
                        new ArrayList<>(), resultSet.getObject("percentage") != null ? new Discount(resultSet.getInt("id"),
                        resultSet.getInt("percentage"),
                        new DateTime(resultSet.getObject("start_date", LocalDateTime.class)),
                        new DateTime(resultSet.getObject("end_date", LocalDateTime.class))) : null);
                resultProduct.add(resultProductItem);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        for (var product : resultProduct) {
            try {
                preparedStatement = con.prepareStatement(STATEMENT_LIST_PICTURE);
                preparedStatement.setString(1, product.getAlias());

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    product.getPictures().add(resultSet.getInt("id_media"));
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
        con.close();

        return resultProduct;
    }
}
