
public class Identifier 
{
	private String name;
	private String className;
	
	public Identifier(String name, String className)
	{
		super();
		this.name = name;
		this.className = className;
	}
	
	public String getName() { return this.name; }
	public String getClassName() { return this.className; }
}
