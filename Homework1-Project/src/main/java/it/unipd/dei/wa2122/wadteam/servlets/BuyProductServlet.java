package it.unipd.dei.wa2122.wadteam.servlets;

import it.unipd.dei.wa2122.wadteam.dao.product.GetProductDatabase;
import it.unipd.dei.wa2122.wadteam.resources.ErrorMessage;
import it.unipd.dei.wa2122.wadteam.resources.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class BuyProductServlet extends AbstractDatabaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException {
        String path = req.getPathInfo() != null ? req.getPathInfo().substring(1) : "";

        int selected = Integer.parseInt(req.getParameter("quantity"));

        Product product = null;

        try {
            product = new GetProductDatabase((getDataSource().getConnection()), path).getProduct();

            writeResource(req, res, "/jsp/buyProduct.jsp", true, product);

        } catch (SQLException e) {
            writeError(req, res, new ErrorMessage.SqlInternalError(e.getMessage()));
        }
    }

}
