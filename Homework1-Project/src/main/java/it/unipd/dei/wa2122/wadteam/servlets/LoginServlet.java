package it.unipd.dei.wa2122.wadteam.servlets;

import it.unipd.dei.wa2122.wadteam.dao.login.CheckUserCredential;
import it.unipd.dei.wa2122.wadteam.resources.Message;
import it.unipd.dei.wa2122.wadteam.resources.UserCredentials;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import static it.unipd.dei.wa2122.wadteam.resources.UserCredentials.*;

public class LoginServlet extends AbstractDatabaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identification = req.getParameter("identification");
        String password = req.getParameter("password");
        TypeUser type = TypeUser.valueOf(req.getParameter("usertype").toUpperCase(Locale.ROOT));

        UserCredentials userCredentialsAttempt = new UserCredentials(identification, password, type, null);
        try {
            UserCredentials userCredentials = new CheckUserCredential(getDataSource().getConnection(), userCredentialsAttempt).getUserCredentials();
            if (userCredentials.getIdentification() != null) {
                writeJson(resp, userCredentials.toJSON());
            } else {
                writeError(resp, new Message("Error login", "EV01", "Username or password aren't correct"),HttpServletResponse.SC_UNAUTHORIZED);
            }

        } catch (SQLException e) {
            writeError(resp, new Message("Error login", "EV02", e.getMessage()),HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }
}
