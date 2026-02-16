/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.dbutils;

public abstract class GenericDAO<T> {

    public abstract T mapRow(ResultSet rs);

    public List<T> query(String sql, Object... params) {
        List<T> list = new ArrayList<>();

        try ( Connection conn = dbutils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            try ( ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    T object = mapRow(rs);
                    if (object != null) {
                        list.add(object);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int Count(String sql, Object... params) {
        try ( Connection conn = dbutils.getConnection();  PreparedStatement pst = conn.prepareStatement(sql)) {

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }

            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
