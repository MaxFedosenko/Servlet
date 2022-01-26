package com.tms.web;

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@WebServlet(value = "/car")
public class CarServletService extends HttpServlet {

    private Map<String, Car> carMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter writer = resp.getWriter();
        if (id.equals("")) {
            Collection<Car> values = carMap.values();
            writer.println(values);
        } else {
            Car car = carMap.get(id);
            writer.println(car);
        }
        Date date = new Date();
        Cookie cookie = new Cookie("cookieDate", DateFormatUtils.format(date, "hh-mm-ss"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        Car car = new Car(name);
        carMap.put(id, car);
        writer.println("new car has been added");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        carMap.get(id).setName(name);
        writer.println("car with id: " + id + " has been renamed to " + name);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter writer = resp.getWriter();
        carMap.remove(id);
        writer.println("car with id: " + id + " has been deleted");

    }
}
