package sintax;

public class SemanticException extends RuntimeException {
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public SemanticException()                                  { super(); }
		public SemanticException(String message)                    { super(message); }
		public SemanticException(Throwable cause)                   { super(cause) ; }
	    public SemanticException(String message, Throwable cause)   { super(message, cause) ; }
}
