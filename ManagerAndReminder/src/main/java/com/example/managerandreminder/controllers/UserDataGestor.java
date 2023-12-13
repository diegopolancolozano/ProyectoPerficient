package com.example.managerandreminder.controllers;
import com.example.managerandreminder.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDataGestor {

    private static UserDataGestor instance;

    private ArrayList<User> users;

    private UserDataGestor(){
        readData();
    }

    public static UserDataGestor getInstance(){
        if(instance == null){
            instance = new UserDataGestor();
        }
        return instance;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    private ArrayList<User> readData(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Information.dat"))) {
            users = (ArrayList<User>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            users = new ArrayList<User>();
        }finally {
            return users;
        }
    }

    public void overrideSingleUser(User original, User newUser){
        readData(); //por si acaso lee denuevo la información si es que hubó algún
                    //cambio entre que se leyó por primera vez hasta el guardar cambios
        boolean done = false;
        for(int i=0; i<users.size() && !done; i++){
            if(users.get(i).equals(original)){
                users.set(i, newUser);
                done = true;
            }
        }

        overrideFile();
    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    public void overrideFile(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("information.dat"))) {
            outputStream.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(User e : users){
            System.out.println(e);
        }
    }


}
