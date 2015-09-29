import java.util.*;
import java.net.*;
import java.io.*;
import java.util.function.*;

class User {
  private final String id;
  private final String name;
  
  User(String id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public String getName() { return name; }
  public String getId() { return id; }
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
    return new User(id, "Dhaval");
  }

  public void twoFactor(User user, long pwd) {
    System.out.println("Inside twoFactor: " + user.getId());
    // throw new RuntimeException("twoFactor Incorrect key");
  }
}

class Dispatcher {
  static void redirect(URL target) {
    System.out.println("Going to => " + target);
  }
}

@FunctionalInterface
interface SupplierThrowsException<T, E extends Exception> {
  T get() throws E;
}

class Try<T> {
  public static <T, E extends Exception> Try<T> with(SupplierThrowsException<T, E> s) {
    try {
      return new Success<T>(s.get());
    } catch (Exception e) {
      return new Failure<T>(e);
    }
  }  
}

class Success<T> extends Try<T> {
  private final T value;
  Success(final T value) { this.value = value; }
}

class Failure<T> extends Try<T> {
  private final Exception e;
  Failure(final Exception e) { this.e = e; }
}

// /Users/dhavald/Documents/workspace/RefactoringToMonad/src/main/java/second/Test.java:96: 
// error: incompatible types: Try<User> cannot be converted to User
//         authenticator.twoFactor(user, twoFactorPwd);
//                                 ^
// /Users/dhavald/Documents/workspace/RefactoringToMonad/src/main/java/second/Test.java:101: 
// error: incompatible types: URL cannot be converted to Try<URL>
//       target = loginPage;
//                ^
// /Users/dhavald/Documents/workspace/RefactoringToMonad/src/main/java/second/Test.java:103: 
// error: incompatible types: Try<URL> cannot be converted to URL
//     Dispatcher.redirect(target);
//                         ^

class TestFromSecond {
  
  public static void main(String[] args) throws Exception {
    URL dashboard  = new URL("http://dashboard");
    URL loginPage  = new URL("http://login");
    
    String userid = "softwareartisan";
    String pwd  = "1234";
    Try<User> user;

    Authenticator authenticator = new Authenticator();
    try {
      user = Try.with(() -> authenticator.login(userid, pwd));
    } catch (Exception es) {
      System.out.println("system login failed = " + es.getMessage());
      try {
        user = Try.with(() -> authenticator.gmailLogin(userid, pwd));
      } catch(Exception eg) {
        System.out.println("gmail login failed = " + eg.getMessage());
        Dispatcher.redirect(loginPage);
        return;
      }
    }
    Try<URL> target;
    try {
      target = Try.with(() -> {
        long twoFactorPwd = 167840;
        authenticator.twoFactor(user, twoFactorPwd);
        return dashboard;
      });
    } catch (Exception tfe) {
      System.out.println("Two factor auth failed = " + tfe.getMessage());
      target = loginPage;
    }
    Dispatcher.redirect(target);
  }
}

