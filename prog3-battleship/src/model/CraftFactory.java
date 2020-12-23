package model;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Craft objects.
 */
public class CraftFactory 
{
	
	/**
	 * Creates a new Craft object.
	 *
	 * @param type the type
	 * @param o the o
	 * @return the craft
	 */
	public static Craft createCraft(String type, Orientation o)
	{
		
		Class<?> tiponave = null;
		Craft nave = null;
		try 
		{
			tiponave = Class.forName("model."+type);
			nave = (Craft) tiponave.getConstructor(Orientation.class).newInstance(o);
		}
		catch (Throwable e) 
		{
			
		}		
		
		
		return nave;
	}
}
