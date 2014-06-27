public class NonEmptyTreeException extends RuntimeException{
	public NonEmptyTreeException() { super(); }
	public NonEmptyTreeException(String message) { super(message); }
	public NonEmptyTreeException(String message, Throwable cause) { super(message, cause); }
	public NonEmptyTreeException(Throwable cause) { super(cause); }
}