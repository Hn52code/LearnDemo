package com.zhn.demo.baseweb.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
        String NAME = "root";
        String PASSWRD = "123456";
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
        Statement statement = connection.createStatement();
        String sql = "select sno,sname,age,sex  from t_student";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("sno");
            String name = resultSet.getString("sname");
            int age = resultSet.getInt("age");
            String sex = resultSet.getString("sex");
            System.out.println("id " + id);
            System.out.println("名称 " + name);
            System.out.println("年纪 " + age);
            System.out.println("性别 " + sex);
        }
        statement.close();
        connection.close();



    }


}
