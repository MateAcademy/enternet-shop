package exception;

/**
 * @author Sergey Klunniy
 */
public class TEAppException extends RuntimeException {

    private static final long serialVersionUID = 5415248000713941020L;

    public TEAppException(String msg) {
        super(msg);
    }

    public TEAppException(Throwable t) {
        super(t);
    }

    public TEAppException() {
        super();
    }

    public TEAppException(String message, Throwable cause) {
        super(message, cause);
    }

//    public String getError() {
//        Throwable rootCause = ExceptionUtils.getRootCause(this);
//        if (rootCause != null && rootCause.getMessage() != null) {
//            return rootCause.getMessage();
//        }
//        return getMessage();
//    }
}