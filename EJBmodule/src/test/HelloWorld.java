package test;

import javax.ejb.Remote;

/**
 * Created by lyn on 16-3-29.
 */
@Remote
public interface HelloWorld {
    String sayHello();
}
