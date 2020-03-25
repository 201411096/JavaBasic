package d_constructor;

public class GradeExpr {
	private int jumsu [];
	
	public GradeExpr(){}
	
	public GradeExpr(int[] jumsu) {
		super();
		this.jumsu = jumsu;
	}
	
	public int getTotal() {
		int sum=0;
		for(int i=0; i<jumsu.length; i++)
			sum+=jumsu[i];
		return sum;
	}
	public double getAverage() {
		int sum =0;
		for(int i=0; i<jumsu.length; i++)
			sum+=jumsu[i];
		return (double)sum/jumsu.length;
	}
	public int getGoodScore() {
		int max_idx = 0;
		for(int i=1; i<jumsu.length; i++)
		{
			if(jumsu[max_idx]<jumsu[i])
				max_idx=i;
		}
		return jumsu[max_idx];
	}
	public int getBadScore() {
		int min_idx = 0;
		for(int i=1; i<jumsu.length; i++)
		{
			if(jumsu[min_idx]>jumsu[i])
				min_idx=i;
		}
		return jumsu[min_idx];
	}
	public int[] getJumsu() {
		return jumsu;
	}

	public void setJumsu(int[] jumsu) {
		this.jumsu = jumsu;
	}	
}
