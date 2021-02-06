package com.example.logic;

import com.example.logic.connection.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

@WebServlet(name = "SimpleServlet", value = "/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        String studentID = request.getParameter("studentID");
        PrintWriter out = response.getWriter();

        String studentNameCheck = null;
        String studentIdCheck = null;
        try {
            Statement statementQuerry = DatabaseConnection.getConnection().createStatement();
            String querry = "select * from student ";
            ResultSet getStudents = statementQuerry.executeQuery(querry);

            studentNameCheck = null;
            studentIdCheck = null;
            while(getStudents.next()){

                studentNameCheck = getStudents.getString("name");
                studentIdCheck = getStudents.getString("indeks");

                if(studentName.equals(studentNameCheck) && studentID.equals(studentIdCheck)){
                    out.println("Welcome " + studentName);
                    return;
                }
            }
            statementQuerry.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        out.println("Not valid");
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called");

    }
}
