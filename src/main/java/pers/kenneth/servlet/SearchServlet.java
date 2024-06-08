package pers.kenneth.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchServlet", urlPatterns = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        // 获取关键词
        String keyword = request.getParameter("keyword");
        // 初始化数据结构
        List<Map<String, Object>> dataList = new ArrayList<>();
        // JDBC操作
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydb";
            String user = "root";
            String pass = "123456";
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            String sql = "select * from student where name like '%" + keyword + "%'";
            // 得到结果集
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                rowData.put("SID", resultSet.getInt("SID"));
                rowData.put("name", resultSet.getString("name"));
                rowData.put("age", resultSet.getInt("age"));
                rowData.put("gender", resultSet.getString("gender"));
                rowData.put("major", resultSet.getString("major"));
                dataList.add(rowData);
                System.out.println(rowData);
            }
            System.out.println(dataList);
            // 释放资源
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        // 存入请求属性
        request.setAttribute("keyword", keyword);
        request.setAttribute("dataList", dataList);
        // 转发
        request.getRequestDispatcher("/show.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}