package security;

import javax.security.auth.callback.*;
import java.io.IOException;


public class BSCallbackHandler implements CallbackHandler
{
    private String username;
    private String password;


    public BSCallbackHandler(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
    {
        //System.out.println("handle");
        for (Callback callback : callbacks)
        {
            if (callback instanceof NameCallback)
            {
                ((NameCallback) callback).setName(username);
            } else if (callback instanceof PasswordCallback)
            {
                ((PasswordCallback) callback).setPassword(password.toCharArray());
            }
        }
        //System.out.println("handle over");
    }
}