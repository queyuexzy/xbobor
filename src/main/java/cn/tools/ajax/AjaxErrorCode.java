package cn.tools.ajax;

/**
 * The ajax error code.
 * 
 * @author Luo Yinzhuo
 */
public class AjaxErrorCode {
	/** The code to identify the ajax response status. */
	final int code;
	/** The message for supplementary. */
	final String message;
	/** The flag to identify it's a system error code or an action error code. */
	final boolean system;

	/** The system error code text. */
	static final String SYSTEM_CODE = "System";
	/** The action error code text. */
	public static final String ACTION_CODE = "Action";

	/**
	 * Construct a new instance.
	 * 
	 * @param code
	 * The code.
	 * @param message
	 * The message.
	 * @param system
	 * The code text.
	 */
	public AjaxErrorCode(int code, String message, String system) {
		this.code = code;
		this.message = message;
		this.system = SYSTEM_CODE.equals(system);
	}

	/**
	 * The error code system flag.
	 * 
	 * @return True is a system error code, otherwise false.
	 */
	public boolean isSystem() {
		return system;
	}

	/**
	 * Get the code.
	 * 
	 * @return The code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Get the message.
	 * 
	 * @return The message.
	 */
	public String getMessage() {
		return message;
	}
}
