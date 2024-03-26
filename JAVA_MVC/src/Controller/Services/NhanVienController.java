/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Services;

import Controller.DbConnection.DataConnection;
import View.NhanVienView;

/**
 *
 * @author admin
 */
public class NhanVienController {
    
    private NhanVienView view;
    
    private DataConnection databaseConnection;
    
    public NhanVienController(NhanVienView nvView) {
        databaseConnection = DataConnection.getInstance();
        this.view = nvView;
    }
}