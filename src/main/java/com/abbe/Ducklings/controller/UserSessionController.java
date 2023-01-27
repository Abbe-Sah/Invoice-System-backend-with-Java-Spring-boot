package com.abbe.Ducklings.controller;

import com.abbe.Ducklings.db.MysqlDatabase;
import com.abbe.Ducklings.model.AuthDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequestMapping("/userSession/*")
public class UserSessionController {
    private MysqlDatabase db;

    public UserSessionController() {
        db = MysqlDatabase.getInstance();
    }

    @GetMapping("login")
    public String showLoginPage() {
        return "loginPage";
    }


    @PostMapping("login")
    public String login(HttpSession session, RedirectAttributes redirect, @ModelAttribute AuthDetails auth) {
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM workers";
        session.setAttribute("username", auth.getUsername());
        session.setAttribute("password", auth.getPassword());
        try {
            Statement pstmt = conn.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                String tempUsername = rs.getString("uname");
                String tempPassword = rs.getString("password");
                if (tempUsername.equalsIgnoreCase(auth.getUsername()) && tempPassword.equalsIgnoreCase(auth.getPassword())) {
                    session.setAttribute("username", auth.getUsername());
                    session.setMaxInactiveInterval(60 * 30);
                    return "redirect:/invoice";
                }

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:login";
    }


    @PostMapping("logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate();
        return "redirect:/index.html";
    }

}
