import java.util.*;
import java.net.*;
import java.io.*;

class User {
  private final String id;
  private final String name;
  
  User(String id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public String name() { return name; }
  public String id() { return id; }
}

class Authenticator {
  public User login(String id, String pwd) throws Exception {
    System.out.println("Inside system login");
    // throw new Exception("password mismatch");
    return new User(id, "Dhaval");
  }

  public User gmailLogin(String id, String pwd) throws Exception {
    System.out.println("Inside gmail login");
    // throw new IOException("no network");
    return new User(id, "Dhaval Dalal");
  }

  public void twoFactor(User user, long pwd) {
    System.out.println("Inside twoFactor: " + user.id());
    // throw new RuntimeException("twoFactor Incorrect key");
  }
}

class Dispatcher {
  static void redirect(URL target) {
    System.out.println("Going to => " + target);
  }
}

class TestFromZero {
  public static void main(String[] args) throws Exception {
    URL dashboard  = new URL("http://dashboard");
    URL loginPage  = new URL("http://login");
    
    String userid = "softwareartisan";
    String pwd  = "1234";
    User user;

    Authenticator authenticator = new Authenticator();
    try {
      user = authenticator.login(userid, pwd);
    } catch (Exception es) {
      System.out.println("system login failed = " + es.getMessage());
      try {
        user = authenticator.gmailLogin(userid, pwd);
      } catch(Exception eg) {
        System.out.println("gmail login failed = " + eg.getMessage());
        Dispatcher.redirect(loginPage);
        return;
      }
    }
    URL target;
    try {
      long twoFactorPwd = 167840;
      authenticator.twoFactor(user, twoFactorPwd);
      target = dashboard;
    } catch (Exception tfe) {
      System.out.println("Two factor auth failed = " + tfe.getMessage());
      target = loginPage;
    }
    Dispatcher.redirect(target);
  }
}

