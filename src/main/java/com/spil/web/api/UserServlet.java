package com.spil.web.api;

import com.spil.web.business.BOFactory;
import com.spil.web.business.BOTypes;
import com.spil.web.business.custom.UserBO;
import com.spil.web.dto.UserDTO;
import com.spil.web.exception.HttpResponseException;
import com.spil.web.exception.ResponseExceptionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(urlPatterns = "/api/v1/users/*")
public class UserServlet extends HttpServlet {

    final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final SessionFactory sf = (SessionFactory) getServletContext().getAttribute("sf");

        try (Session session = sf.openSession()) {

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                System.out.println(req.getPathInfo());

                throw new HttpResponseException(400, "Invalid user id", null);
            }

            String id = req.getPathInfo().replace("/", "");

            UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
            userBO.setSession(session);
            userBO.deleteUser(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final SessionFactory sf = (SessionFactory) getServletContext().getAttribute("sf");

        try (Session session = sf.openSession()) {

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid user id", null);
            }

            String id = req.getPathInfo().replace("/", "");
            Jsonb jsonb = JsonbBuilder.create();
            UserDTO dto = jsonb.fromJson(req.getReader(), UserDTO.class);

            if (dto.getId() != null || dto.getName().trim().isEmpty() || dto.getAddress().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid details", null);
            }

            UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
            userBO.setSession(session);
            dto.setId(id);
            userBO.updateUser(dto);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();

        final SessionFactory sf = (SessionFactory) getServletContext().getAttribute("sf");

        try (Session session = sf.openSession()) {
            resp.setContentType("application/json");
            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
                userBO.setSession(session);
                resp.getWriter().println(jsonb.toJson(userBO.findAllUsers()));
            }
            else {
                String id = req.getPathInfo().replace("/", "");

                UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
                userBO.setSession(session);
                UserDTO user = userBO.findUser(id);
                if(user==null){
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                else{
                    resp.getWriter().println(jsonb.toJson(user));
                }
            }


        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        final SessionFactory sf = (SessionFactory) getServletContext().getAttribute("sf");
        try (Session session = sf.openSession()) {
            UserDTO dto = jsonb.fromJson(req.getReader(), UserDTO.class);

            if (dto.getId() == null || dto.getId().trim().isEmpty() || dto.getName() == null || dto.getName().trim().isEmpty() || dto.getAddress() == null || dto.getAddress().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid user details", null);
            }

            UserBO userBO = BOFactory.getInstance().getBO(BOTypes.USER);
            userBO.setSession(session);
            userBO.saveUser(dto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.getWriter().println(jsonb.toJson(dto));
        } catch (SQLIntegrityConstraintViolationException exp) {
            throw new HttpResponseException(400, "Duplicate entry", exp);
        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
