import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main
{	
	public static void main(String[] args)
	{
		ArrayList<Identifier> ids = getIdentifiers();
		
		System.out.println("IDENTIFIERS");
		for(Identifier id : ids)
			System.out.println(id.getName());
		
		System.out.println("SETUP");
		setupViews(ids);
		
		System.out.println("LAYOUT");
		layoutSubviews(ids);
		
		System.out.println("DESTROY");
		destroyViews(ids);
		
	}
	
	public static ArrayList<Identifier> getIdentifiers()
	{
		ArrayList<String> lines = getLines();
		ArrayList<Identifier> identifiers = new ArrayList<Identifier>();
		for(String line : lines)
		{
			line = line.trim();
			String[] components = line.split("\\*");
			String className = components[0];
			String name = components[1].trim();
			name = name.substring(0, name.length()-1);
			identifiers.add(new Identifier(name, className));
		}
		return identifiers;
	}
	
	public static void setupViews(ArrayList<Identifier> ids)
	{
		for(Identifier id : ids)
		{
			System.out.println(setupIdentifier(id) + "\n");
		}
	}
	
	public static void layoutSubviews(ArrayList<Identifier> ids)
	{
		for(Identifier id : ids)
		{
			System.out.println(layoutSubview(id));
		}
	}
	
	public static void destroyViews(ArrayList<Identifier> ids)
	{
		for(Identifier id : ids)
		{
			System.out.println(destroyIdentifier(id));
		}
	}
	
	public static String setupIdentifier(Identifier identifier)
	{
		String res = identifier.getName()+" = [["+ identifier.getClassName() + " alloc] initWithFrame:CGRectZero];";
		res += "\n"+identifier.getName() + ".autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleTopMargin;";
		res += "\n[self addSubview:"+identifier.getName()+"];";
		return res;
	}
	
	public static String layoutSubview(Identifier identifier)
	{
		String res = identifier.getName() + ".frame = CGRectMake(0, 0, 0, 0);";
		return res;
	}
	
	public static String destroyIdentifier(Identifier identifier)
	{
		String name = identifier.getName();
		String res = "["+name+" removeFromSuperview], ["+name+" release], "+name+" = nil;";
		return res;
	}
	
	public static ArrayList<String> getLines()
	{
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader("data"));
			String line = reader.readLine();
			while(line != null)
			{
				lines.add(line);
				line = reader.readLine();
			}
			return lines;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
}