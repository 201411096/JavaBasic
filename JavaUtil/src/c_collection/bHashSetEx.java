package c_collection;
import java.util.HashSet;

/*
 * 1. 순서가 유지되지 않음
 * 2. 중복을 허용하지 않음
 */

public class bHashSetEx 
{
	public static void main(String[] args)
	{
		HashSet set = new HashSet();
		set.add("rabbit");
		set.add("zebra");
		set.add("fox");
		set.add("zebra");
		set.add("elephant");
		set.add("elephant");	
		
		System.out.println( set );
		
 
	}
}
