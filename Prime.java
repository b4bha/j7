package Series;
public class Prime
{
	public static void primeseries(int n)
	{
		int flag=0,no;
		for(no=1;no<=n;no++)
		{
			flag=0;
			for(int i=2;i<no;i++)
			{
				if(no%i==0)
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			System.out.print(no+","); 
		}
	}
}