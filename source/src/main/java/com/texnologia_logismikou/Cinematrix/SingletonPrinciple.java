package com.texnologia_logismikou.Cinematrix;

import java.lang.reflect.Constructor;

public abstract class SingletonPrinciple<T> {
	private static final    Object                tLock    = new Object();
	private static volatile SingletonPrinciple<?> instance = null;
	
	protected SingletonPrinciple() {}
	
	@SuppressWarnings("unchecked")
	public static <T extends SingletonPrinciple<T>> T getInstance(Class<T> cc)
	{
		if(instance == null)
		{
			synchronized (tLock)
			{
				try
				{
					Constructor<T> cTor = cc.getDeclaredConstructor();
					cTor.setAccessible(true);
					instance = cTor.newInstance();
				} catch(Exception e)
				{
					throw new RuntimeException("Failed to create singleton instance", e);
				}
			}
		}
		return((T)instance);
	}
}
