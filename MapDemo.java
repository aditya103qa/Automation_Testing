import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

	public static void main(String[] args)
	{
		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(1 ,"Akanksha");
		map.put(2 ,"Aman");
		map.put(3 ,"Aditya");
		map.put(4 ,"Vishal");
		map.put(5 ,"Tarun");
		map.put(6 ,"Aditya");
		map.put(7 ,"Kunal");

		System.out.print(map);
		Set<Integer> keys = map.keySet();
		System.out.println(keys);
		
		for(Integer in : map.keySet())
		{
			if(map.get(in).equals("Aditya"))
				System.out.println(in);
			
		}
	}
}
