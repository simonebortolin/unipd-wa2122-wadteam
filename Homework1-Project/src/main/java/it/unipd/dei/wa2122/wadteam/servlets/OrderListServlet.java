package it.unipd.dei.wa2122.wadteam.servlets;

import it.unipd.dei.wa2122.wadteam.dao.onlineOrder.GetOnlineOrderByCustomerDatabase;
import it.unipd.dei.wa2122.wadteam.dao.onlineOrder.GetOnlineOrderByIdDatabase;
import it.unipd.dei.wa2122.wadteam.resources.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class OrderListServlet extends AbstractDatabaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getPathInfo() != null ? req.getPathInfo().substring(1).lastIndexOf('/') != -1 ? req.getPathInfo().substring(1,req.getPathInfo().lastIndexOf('/')) : req.getPathInfo().substring(1) : "";
        String param = req.getPathInfo() != null ? req.getPathInfo().substring(1).lastIndexOf('/') != -1 ? req.getPathInfo().substring(req.getPathInfo().lastIndexOf('/')+1) : "" : "";

        switch (path){
            case "detail" -> orderDetailOp(req,res, param);
            case "list" -> orderListOp(req,res);
            default -> writeError(req, res, GenericError.PAGE_NOT_FOUND);
        }
    }

    private void orderListOp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<OnlineOrder> list;
        HttpSession session = req.getSession(false);
        UserCredential user = (UserCredential) session.getAttribute(USER_ATTRIBUTE);
        Integer id = user.getId();

        try{
            list = new GetOnlineOrderByCustomerDatabase(getDataSource().getConnection(), id).getOnlineOrderByCustomer();
            Collections.reverse(list);
            logger.info("The user is able to look at its orders");
            writeResource(req,res, "/jsp/orderList.jsp",false, list.toArray(Resource[]::new));

        } catch (SQLException e) {
            logger.error(e.getMessage());
            ErrorMessage errorMessage = new ErrorMessage.OrderNotFoundError(e.getMessage());
            writeError(req, res, errorMessage);
        }
    }

    private void orderDetailOp(HttpServletRequest req, HttpServletResponse res, String path) throws ServletException, IOException {
        OnlineOrder order;
        Integer idCustomer;
        Integer id = null;
        if(path.chars().allMatch(Character::isDigit)){
            id = Integer.parseInt(path);
        }

        try{

            order = new GetOnlineOrderByIdDatabase(getDataSource().getConnection(), id).getOnlineOrder();
            idCustomer = order.getIdCustomer();
            UserCredential user = (UserCredential) req.getSession(false).getAttribute(USER_ATTRIBUTE);
            if(!Objects.equals(idCustomer, user.getId())){
                logger.error("unauthorized");
                writeJsp(req,res,"/jsp/unauthorized.jsp");
            }
            else{
                logger.info("The user is able to look at its order: "+id);
                writeResource(req,res, "/jsp/orderDetails.jsp",true, order);
            }



        } catch (SQLException e) {
            logger.error(e.getMessage());
            ErrorMessage errorMessage = new ErrorMessage.OrderNotFoundError(e.getMessage());
            writeError(req, res, errorMessage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException {
        doGet(req, res);
    }
}
