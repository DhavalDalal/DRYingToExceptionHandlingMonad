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
    throw new RuntimeException("twoFactor Incorrect key");
  }
}

class Dispatcher {
  static void redirect(URL target) {
    System.out.println("Going to => " + target);
  }
}

@FunctionalInterface
interface SupplierThrowsException<T, E extends Throwable> {
  T get() throws E;
}

abstract class Try<T> {
  public static <T, E extends Throwable> Try<T> with(SupplierThrowsException<T, E> s) {
    try {
      return new Success<T>(s.get());
    } catch (Throwable t) {
      return new Failure<T>(t);
    }
  }  
  abstract <U> Try<U> chain(Function<T, Try<U>> f);
  abstract T get();
  abstract <U> Try<U> recoverWith(Function<Throwable, Try<U>> f);
  abstract <U> Try<U> orElse(Try<U> that);
  abstract void forEach(Consumer<T> c);
}

class Success<T> extends Try<T> {
  private final T value;
  Success(final T value) { this.value = value; }
  
  <U> Try<U> chain(Function<T, Try<U>> f) {
    try { return f.apply(value); }
    catch(Throwable t) {
      return new Failure<U>(t);
    }
  }
  T get() { return value; }
  <U> Try<U> recoverWith(Function<Throwable, Try<U>> f) {
    return (Try<U>)this;
  }
  <U> Try<U> orElse(Try<U> that) { return (Try<U>)this; }
  void forEach(Consumer<T> c) { c.accept(value); }
}

class Failure<T> extends Try<T> {
  private final Throwable t;
  Failure(final Throwable t) { this.t = t; }
  <U> Try<U> chain(Function<T, Try<U>> f) {
    return (Try<U>)this;
  }
  T get() { throw new RuntimeException("Failure cannot return"); }
  <U> Try<U> recoverWith(Function<Throwable, Try<U>> f) {
    try {return f.apply(t);}
    catch (Throwable e) { return new Failure<U>(e); }
  }
  <U> Try<U> orElse(Try<U> that) { return that; }
  void forEach(Consumer<T> c) { }
}

class Test {
  
  public static void main(String[] args) {
    String userid = "softwareartisan";
    String pwd  = "1234";
    Authenticator authenticator = new Authenticator();
    
    Try.with(() -> authenticator.login(userid, pwd))
       .recoverWith(e -> Try.with(() -> authenticator.gmailLogin(userid, pwd)))
       .chain(user -> Try.with(() -> {
          long twoFactorPwd = 167840;
          authenticator.twoFactor(user, twoFactorPwd);
          return new URL("http://dashboard");
       }))
       .orElse(Try.with(() -> new URL("http://login")))
       .forEach(Dispatcher::redirect);
    System.out.println("DONE");
  }
}

