package c_collection;
import java.util.*;

public class bHashSetLottoEx 
{
	public static void main(String[] args) 
	{
		HashSet<Integer> lottos = new HashSet<Integer>();
		
		while(lottos.size()!=6)
			lottos.add((int)(Math.random()*45+1));
		
//		System.out.println(lottos.toString());
		
		ArrayList<Integer> list = new ArrayList<Integer>(lottos);
		Collections.sort(list);
		System.out.println(list);
			
		
	}
}
