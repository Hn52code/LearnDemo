package com.zhn.demo.baseweb.dao;

import com.zhn.demo.baseweb.entity.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
    private final String NAME = "root";
    private final String PASSWRD = "123456";

    public boolean insert(Student student) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
            String sql = "insert into t_student(sno,sname,age,sex,grade,birthday) values(?,?,?,?,?,?);";
            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, student.getSno());
            psmt.setString(2, student.getSname());
            psmt.setInt(3, student.getAge());
            psmt.setString(4, student.getSex());
            psmt.setString(5, student.getGrade());
            psmt.setDate(6, new Date(student.getBirthday().getTime() - 5000));
            int result = psmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(Integer sno, String sname, Integer age) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
            String sql = "update t_student set sname = ?,age = ? where sno = ?";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1, sname);
            psmt.setInt(2, age);
            psmt.setInt(3, sno);
            int result = psmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(Integer sno) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
            String sql = "delete from t_student WHERE sno = ?;";
            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, sno);
            int result = psmt.executeUpdate();
            if (result > 0)
                return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Student queryBySno(Integer sno) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
            String sql = " select sno,sname,age,sex,grade,birthday from t_student where sno = ?;";
            psmt = connection.prepareStatement(sql);
            psmt.setInt(1, sno);
            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                int sno2 = resultSet.getInt(1);
                String sname = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String sex = resultSet.getString(4);
                String grade = resultSet.getString(5);
                Date birthday = resultSet.getDate(6);
                return new Student(sno2, sname, age, birthday, sex, grade);
            }
            return null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Student> queryAll() {
        Connection connection = null;
        PreparedStatement psmt = null;
        List<Student> list = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, NAME, PASSWRD);
            String sql = " select sno,sname,age,sex,grade,birthday from t_student;";
            psmt = connection.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();
            list = new LinkedList<>();
            while (resultSet.next()) {
                int sno2 = resultSet.getInt(1);
                String sname = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String sex = resultSet.getString(4);
                String grade = resultSet.getString(5);
                Date birthday = resultSet.getDate(6);
                list.add(new Student(sno2, sname, age, birthday, sex, grade));
            }
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) psmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {

        StudentDao dao = new StudentDao();
//        Student student = new Student(131305, "ZhouHn", 12, new java.util.Date(), "man", "一年级");
//        boolean result = dao.insert(student);
//        if (result) System.out.println("添加成功！");
//        else System.out.println("添加失败！");

        Student student = new Student(131302, "ZhouHj", 13, new java.util.Date(), "man", "二年级");
        boolean insert = dao.insert(student);
        if (insert) System.out.println("添加成功！");
        else System.out.println("添加失败！");


        Student student1 = dao.queryBySno(131305);
        if (student1 != null) System.out.println(student1.toString());
        else System.out.println("不存在这个学生");

//        boolean delete = dao.delete(131302);
//        if (delete) System.out.println("删除成功！");
//        else System.out.println("删除失败！");

        boolean update = dao.update(131305, "周海南", 10);
        if (update) System.out.println("修改成功！");
        else System.out.println("修改失败！");

        List<Student> list = dao.queryAll();
        if (list != null)
            for (Student stu : list) {
                System.out.println(stu.toString());
            }

    }


}
