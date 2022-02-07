package torvalds.WarehouseManager.utils;


public class ExceptionHandlingUtil {
	public static void check (boolean condition, String message ) {
		if (!condition)
			throw new IllegalArgumentException(message); 
	}
}
