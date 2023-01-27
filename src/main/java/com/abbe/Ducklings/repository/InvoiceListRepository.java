package com.abbe.Ducklings.repository;

import com.abbe.Ducklings.db.MysqlDatabase;
import com.abbe.Ducklings.model.Invoice;
import com.abbe.Ducklings.model.InvoicePost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceListRepository {

    private MysqlDatabase db;

    private int currentPostId;

    public InvoiceListRepository() {
        db = MysqlDatabase.getInstance();
    }

    public Invoice getInvoiceList(String username) {
        Connection conn = db.getConnection();
        Invoice list = new Invoice(username);
        String sql = "" +
                "SELECT * FROM invoices " +
                "JOIN workers " +
                "ON invoices.ownerID=workers.id " +
                "WHERE workers.uname = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            do {
                InvoicePost item = new InvoicePost();
                item.setTitle(rs.getString("title"));
                item.setContent(rs.getString("content"));
                item.setCategory(rs.getString("category"));
                item.setPrice(rs.getInt("price"));
                item.setUser(rs.getString("uname"));
                item.setPdate(rs.getDate("pdate"));
                item.setPostId(rs.getInt("id"));
                list.getInvoiceList().add(item);
            } while (rs.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void addPostItem(String username, InvoicePost item) {
        Connection conn = db.getConnection();
        String sql = "SELECT id FROM workers WHERE uname=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            int ownerId;
            if (!rs.next()) {
                ownerId = this.createNew(username);
            } else {
                ownerId = rs.getInt("id");
            }
            sql = "INSERT INTO invoices (ownerID, title, content, category, price, pdate)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ownerId);
            pstmt.setString(2, item.getTitle());
            pstmt.setString(3, item.getContent());
            pstmt.setString(4, item.getCategory());
            pstmt.setInt(5, item.getPrice());
            pstmt.setDate(6, item.getPdate());
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createNew(String username) {
        Connection conn = db.getConnection();
        String sql = "INSERT INTO workers (uname) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public InvoicePost getInvoice(int id) {
        currentPostId = id;
        InvoicePost item = new InvoicePost();
        Connection conn = db.getConnection();
        String sql = "" +
                "SELECT * FROM invoices " +
                "WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            item = new InvoicePost();
            item.setPostId(rs.getInt("id"));
            item.setPdate(rs.getDate("pdate"));
            item.setTitle(rs.getString("title"));
            item.setContent(rs.getString("content"));
            item.setCategory(rs.getString("Category"));
            item.setPrice(rs.getInt("price"));
            System.out.println(item.getPdate());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public void updateInvoice(InvoicePost item) {
        Connection conn = db.getConnection();
        String sql = "UPDATE invoices SET title = ?, content = ?, category = ?, price = ?"
                + " WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getContent());
            pstmt.setString(3, item.getCategory());
            pstmt.setInt(4, item.getPrice());
            pstmt.setInt(5, currentPostId);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
