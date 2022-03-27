package it.unipd.dei.wa2122.wadteam.dao.onlineOrder;

import it.unipd.dei.wa2122.wadteam.resources.OnlineOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateOnlineOrderDatabase {
    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "UPDATE OnlineOrder SET oo_datetime = ?, id_customer = ? WHERE id = ?";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The id of the onlineOrder
     */
    private final Integer id;

    /**
     * Creates a new object for updating an onlineOrder.
     *
     * @param con
     *            the connection to the database.
     * @param id
     *            the id of the onlineOrder.
     */
    public UpdateOnlineOrderDatabase(final Connection con, final Integer id) {
        this.con = con;
        this.id = id;
    }

    /**
     * Gets an onlineOrder from the database.
     *
     * @return the {@code OnlineOrder} object matching the id.
     *
     * @throws SQLException
     *             if any error occurs while deleting the onlineOrder.
     */
    public OnlineOrder updateOnlineOrder() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the deleted onlineOrder
        OnlineOrder resultOnlineOrder = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                resultOnlineOrder = new OnlineOrder(
                        rs.getInt("id"),
                        rs.getInt("id_customer"),
                        rs.getString("oo_datetime")
                );
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        return resultOnlineOrder;
    }
}
