package com.everseeker.serializable;

import java.io.*;

/**
 * Created by everseeker on 2016/11/23.
 */
public class SerializableDemo implements Serializable {
    User user;
    //Password password;    //Password类没有实现Serializable接口，无法序列化
    Email email;
    String address;
    static int zipcode = 214000;    //序列化是针对对象实例来说的，static类型无法序列化
    transient int phone;            //transient类型无须序列化

    public SerializableDemo() {
        this.user = new User();
        //this.password = new Password("password");
        this.email = new Email("email@123.com");
        this.address = "address";
        this.phone = 100100;
    }

    public static void main(String[] args) {
        try {
            //write
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/everseeker/Projects/Intellij/java-demo/files/result.obj"));
            out.writeObject(new SerializableDemo());
            out.close();
            SerializableDemo.zipcode = 200200;
            //read
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("/Users/everseeker/Projects/Intellij/java-demo/files/result.obj"));
            SerializableDemo demo = (SerializableDemo)in.readObject();
            in.close();
            //print
            System.out.println("username = " + demo.user.username +
                    //"\npassword = " + demo.password.password +
                    "\naddress = " + demo.address +
                    "\nemail = " + demo.email.email +
                    "\nzipcode = " + demo.zipcode +
                    "\nphone = " + demo.phone);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    public String username = "username";
}

class Password {
    public String password;

    public Password(String password) {
        this.password = password;
    }
}

class Email implements Serializable {
    public String email;

    public Email(String email) {
        this.email = email;
    }
}