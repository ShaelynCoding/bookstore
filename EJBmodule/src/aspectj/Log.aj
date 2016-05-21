package aspectj;

import org.aspectj.lang.JoinPoint;

import java.util.logging.Logger;

/**
 * Created by lyn on 16-5-20.
 */
public aspect Log {
    private static final Logger logger;

    static {
        logger = Logger.getLogger(Log.class.getName());
    }
    pointcut actionCall(): execution(* action..*(..) );

    before(): actionCall()
            {
                logger.info(getlocation(thisJoinPoint)+" : "+getCallName(thisJoinPoint));
            }
    after() returning (Object object): actionCall()
            {
                String res="";
                if(object==null)
                    res="void";
                else res=object.toString();

                logger.info("return: "+res);
            }
    private String getCallName(JoinPoint point)
    {
        return point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();

    }
    private String getlocation(JoinPoint point)
    {
        return point.getSourceLocation().toString();
    }


}
