package com.texnologia_logismikou.Cinematrix;

public class DebugHelper {
	private DebugHelper() {}
	
	public static void stopHere()
	{
		try
		{
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
