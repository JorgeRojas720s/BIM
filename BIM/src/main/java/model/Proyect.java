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
    private String engineer;
    private String designer;

    public Proyect(String code, String name, String startDate, String finishDate, String engineer, String designer) {
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.engineer = engineer;
        this.designer = designer;
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

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
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
        return code + "|" + name + "|" + startDate + "|" + finishDate + "|" + engineer + "|" + designer;
    }

}
