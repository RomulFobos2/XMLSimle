package org.example;

public class StartApp {
    public static void main(String[] args) throws Exception{
        ManageDB.connectToDB();
        Worker.createXML();
    }
}
