package atn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class atnproject1 
{

 public static int[][] trafficDemand(int N)
  {
	int b[][]= new int[N][N] ; 

	 for(int i=0;i<N;i++) 
	   for(int j=0;j<N;j++)
	    {	
	     b[i][j]=getRandomNumber(0,4);
	    
	     }
	      return b;
	}
  private static int getRandomNumber(int min, int max)
  {
	  if(min>=max)
	  {
		  throw new IllegalArgumentException("max is less than min number"); 
	  }
	  else
	  {
		  return(int) ((Math.random() * (max - min)) + 1)+1;
	  }
	  
  }

  public static int[][] adjacencyMatrix(int N , int k)
   {
	int a[][] = new int[N][N]; 
	List<Integer> list = new ArrayList<Integer>();
	
	for(int i=0 ;i<N ;i++)
	list.add(i);
	int index[] = new int[k] ;
	
	/*Initializing the Cost Matrix */
	for(int i=0;i<N;i++)
	{
		
		Collections.shuffle(list);

		for(int l=0;l<k;l++){
			if(list.get(l) == i)
				index[l] = list.get(N-k);
			else
				index[l] = list.get(l);
		}
		
		for(int j=0; j<N;j++)
		{
		    if (i==j)
		    	a[i][j] = 0;
		    else
		      for(int l=0 ;l<k ;l++)
		      {
		    	if(index[l] == j)
		    	{
		    		a[i][j] = 1;
		    	    break ;
		    	}
		    	else
		    		a[i][j] = 250;
		      }
		    
		}
	}
	return a;	
	
}
  static final int V=21;
  
	public static int minDistance(int dist[], Boolean sptSet[])
  {

      int min = Integer.MAX_VALUE, min_index=-1;

      for (int v = 0; v < V; v++)
          if (sptSet[v] == false && dist[v] <= min)
          {
              min = dist[v];
              min_index = v;
          }

      return min_index;
  }

  public static int[] dijkstra(int graph[][], int src)
  {
      int dist[] = new int[V]; 
      Boolean sptSet[] = new Boolean[V];

      for (int i = 0; i < V; i++)
      {
          dist[i] = Integer.MAX_VALUE;
          sptSet[i] = false;
      }

      dist[src] = 0;

      for (int count = 0; count < V-1; count++)
      {

          int u = minDistance(dist, sptSet);
          sptSet[u] = true;

          for (int v = 0; v < V; v++)

              if (!sptSet[v] && graph[u][v]!=0 &&
                      dist[u] != Integer.MAX_VALUE &&
                      dist[u]+graph[u][v] < dist[v])
                  dist[v] = dist[u] + graph[u][v];
      }return dist;
  }
  
  public static int costCalculation(int b[][], int g[][] ,int N){
		int cost= 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N ;j++){
				cost = cost+( b[i][j]*g[i][j]) ;
			}
		return cost;
	}
	
	public static float densityCalculation(int[][] cost, int N){
		float density;
		float possibleConnection = N*(N-1) ;
		int actualConnection = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<N ;j++){
				if(cost[i][j] == 1)
					actualConnection++;
			}
		}
		density = (float)(actualConnection/possibleConnection) ;
		return density;
	}
	public static void main(String[] args) {
   
		
		int N = 21;
		for(int k=3; k<=15; k++){
		int cost = 0;
		int B[][] = trafficDemand(N);
		int A[][] = adjacencyMatrix(N,k);
		int distance[] = new int[N];
		int graph[][] = new int[N][N];
	    for(int source =0; source<N ;source++){
			distance = dijkstra(A, source);
			graph[source] = distance;
		}
		cost = costCalculation(B,graph,N);
		float density = densityCalculation(A ,N);
		
		System.out.println(" K = " + k + "\n Cost = " +cost + "    Density = " + density + "\n");
	}
}

}

