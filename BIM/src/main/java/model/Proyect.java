/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jitor
 */
public class Proyect {
    
    private String code;
    private String name;
    private String startDate;
    private String finishDate;

    public Proyect(String code, String name, String startDate, String finishDate) {
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return  code + "|" + name +  "|" + startDate + "|" + finishDate;
    }
    
    
    
    
    
    
}
