package it.unipd.dei.wa2122.wadteam.servlets;

import it.unipd.dei.wa2122.wadteam.dao.customer.GetIdCustomerDatabase;
import it.unipd.dei.wa2122.wadteam.dao.onlineOrder.GetOnlineOrderByCustomerDatabase;
import it.unipd.dei.wa2122.wadteam.dao.onlineOrder.GetOnlineOrderByIdDatabase;
import it.unipd.dei.wa2122.wadteam.resources.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderListServlet extends AbstractDatabaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo() != null ? req.getPathInfo().substring(1).lastIndexOf('/') != -1 ? req.getPathInfo().substring(1,req.getPathInfo().lastIndexOf('/')) : req.getPathInfo().substring(1) : "";
        String param = req.getPathInfo() != null ? req.getPathInfo().substring(1).lastIndexOf('/') != -1 ? req.getPathInfo().substring(req.getPathInfo().lastIndexOf('/')+1) : "" : "";


        switch (path){
            case "detail" -> orderDetailOp(req,res, param);
            case "list" -> orderListOp(req,res);
        }
    }

    private void orderListOp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<OnlineOrder> list = null;
        HttpSession session = req.getSession(false);
        UserCredential user = (UserCredential) session.getAttribute("user");
        Integer id = user.getId();

        try{
            list = new GetOnlineOrderByCustomerDatabase(getDataSource().getConnection(), id).getOnlineOrderByCustomer();
            List<Resource> resList = new ArrayList<>();
            resList.addAll(list);
            writeResource(req,res, "/jsp/orderList.jsp",false, resList.toArray(Resource[]::new));

        } catch (SQLException e) {
            //Message m = new Message("Couldn't find the order", "EU01", e.getMessage());
            ErrorMessage errorMessage = new ErrorMessage.OrderNotFoundError(e.getMessage());
            writeError(req, res, errorMessage);
        }
    }

    private void orderDetailOp(HttpServletRequest req, HttpServletResponse res, String path) throws ServletException, IOException {
        OnlineOrder order = null;
        //String path = req.getParameter("ID");
        Integer id = null;
        if(path.chars().allMatch(Character::isDigit)){
            id = Integer.parseInt(path);
        }

        try{

            order = new GetOnlineOrderByIdDatabase(getDataSource().getConnection(), id).getOnlineOrderId();

            writeResource(req,res, "/jsp/orderDetails.jsp",true, order);

        } catch (SQLException e) {
            //Message m = new Message("Couldn't find the order", "EU01", e.getMessage());
            ErrorMessage errorMessage = new ErrorMessage.OrderNotFoundError(e.getMessage());
            writeError(req, res, errorMessage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException {


    }
}
