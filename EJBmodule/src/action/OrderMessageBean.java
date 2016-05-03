package action;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by lyn on 16-5-3.
 */
@MessageDriven(name = "OrderMessageEJB",
        activationConfig =
                {
                        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue")
                }
)
public class OrderMessageBean implements MessageListener {
    @EJB(name = "CartEJB")
    Cart cart;

    public OrderMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof TextMessage)
            {
                String text=((TextMessage)message).getText();
                String[] tmp=text.split("@");
                cart.buyByMsg(tmp[0],tmp[1]);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
