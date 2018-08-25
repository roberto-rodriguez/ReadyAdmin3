/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.enums;

/**
 *
 * @author Alejo
 */
public enum APP {
    
    AIRPORT("Airport","x-fa fa-home"),
    BANK("Bank","x-fa fa-home"),
    CARS_SERVICES("Cars services","x-fa fa-home"),
    COMPUTER_SERVICES("Computer services","x-fa fa-home"),
    HUMAN_RESOURCES("Human resources","x-fa fa-home"),
    LIBRARY("Library","x-fa fa-home"),
    SCHOOL("School","x-fa fa-home"),    
    SPORTS_CALENDAR("Sports calendar","x-fa fa-home"),
    SPORTS_CHOP("Sports shop","x-fa fa-home"),
    STORE("Store","x-fa fa-home");     
    
    private String name;
    private String icon;

    private APP(String name,String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getId() {
        return name;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

}