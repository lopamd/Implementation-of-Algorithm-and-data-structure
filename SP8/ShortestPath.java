/**
* Implementation of ShortestPath algorithms.

package cs6301.g45;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import cs6301.g45.Graph.Edge;
import cs6301.g45.Graph.Vertex;

/**
 * @param  Class declaration for VertexProperty
 * seen : boolean : says if a vertex is visited or not.
 * 
 */
class VertexProperty{
	boolean seen;
	Vertex parent;
	int distance;
	int count;
	
	public VertexProperty() {
		seen = false;
		parent = null;
		distance = Integer.MAX_VALUE;
		count = 0;
	}
	
}

/**
 * @param  Class declaration for ShortestPath which extends GraphAlgorithm<VertexProperty>
 * start : Vertex : start Vertex object
 */
public class ShortestPath extends GraphAlgorithm<VertexProperty>{
	
	Vertex start;
	int[] path;
	//Constructor
	public ShortestPath(Graph g, Vertex s){
		super(g);
		node=new VertexProperty[g.size()];
		for(int i=0;i<g.size();i++){
			node[i]=new VertexProperty();
		}
		start=s;
	}
	/**
	 * BFS function 
	 */
	public void BFS(){
		LinkedList<Graph.Vertex> travQueue = new LinkedList<Graph.Vertex>();
		//VertexProperty[] seen=new VertexProperty[g.size()];
		path=new int[g.size()];
		int index=0;
		getVertex(start).seen = true;
		path[index++]=start.name+1;
		travQueue.add(start);// add the first vertex to the queue
		Vertex v;
		while (!travQueue.isEmpty()) { // loop till queue is empty
			v = travQueue.remove();
			Iterator<Edge> edges = v.adj.iterator();
			while (edges.hasNext()) { // loop through all edges for every vertex in the queue
				Edge next = edges.next();
				Vertex temp = next.otherEnd(v); // get the other end of the current vertex and edge
				if ((getVertex(temp).seen == false)) { // if it is seen, do not do anything
					travQueue.add(temp); // put the vertex in the queue and set seen for the node to true
					getVertex(temp).seen = true;
					path[index++]=temp.name+1;
				}	
			}
		}
	}
	
	/**
	 * Bellman Ford function : returns false if the given graph has negative cycles
	 */
	public boolean bellmanFord(){
		
		int sizeOfGraph = g.n;
		LinkedList<Vertex> spQueue = new LinkedList<Vertex>();
		VertexProperty startVertex = getVertex(start);
		startVertex.distance = 0;
		startVertex.seen = true;
		spQueue.add(start);	// add the source vertex in queue
		while(spQueue.size()!=0){
			Vertex temp = spQueue.removeFirst();
			VertexProperty tempProp =getVertex(temp);
			tempProp.count++;
			if (tempProp.count>sizeOfGraph){// to check if the graph has negative cycles
				return false;
			}
			for (Edge e : temp.adj){
				Vertex otherEndVertex = e.otherEnd(temp);
				VertexProperty otherEndVertexProp =getVertex(otherEndVertex);
				if(otherEndVertexProp.distance>(tempProp.distance+e.weight)){// update the distance if the current distance is smaller than its previous distance
					otherEndVertexProp.distance = tempProp.distance+e.weight;
					otherEndVertexProp.parent = temp;//update predecessor to the node from where it is getting its shorter distance
					if(!otherEndVertexProp.seen){
						spQueue.add(otherEndVertex);
						otherEndVertexProp.seen = true;
					}
				}
			}
		}
		
		return true;
	}
	
	
	//Main function to test the graph
	public static void main(String[] args){
		System.out.println("Please enter the graph for BFS: "); // format is same as in graph class given by professor
		Scanner sc = new Scanner(System.in);
		Graph g = Graph.readGraph(sc,false);
		ShortestPath shPath=new ShortestPath(g,g.getVertex(1));
		shPath.BFS();
		System.out.print("BFS path: ");
		for(int i=0;i<shPath.path.length;i++){
			System.out.print(shPath.path[i]+" ");
		}
		System.out.println();
		System.out.println("Please enter the graph for Bellman Ford: "); 
		Graph g1 = Graph.readGraph(sc,true);
		ShortestPath sp = new ShortestPath(g1, g1.getVertex(1));
		if(!sp.bellmanFord())
			System.out.println("Graph has negative cycles");
		else{
			System.out.println("Bellman Ford:");
			System.out.println("Vertex\tDistance\tPredecessor");
			for(int i=0;i<sp.node.length;i++){
				if(sp.node[i].parent!=null)
					System.out.println((i+1)+"\t"+sp.node[i].distance+"\t"+(sp.node[i].parent.getName()+1));
				else
					System.out.println((i+1)+"\t"+sp.node[i].distance+"\t- ");
			}
		}
	}
}

/*
 * Sample test output with start vertex as 1:
Please enter the graph for BFS: 
6 8
1 2 1
1 3 1
2 4 1
2 5 1
3 5 1
4 5 1
4 6 1
5 6 1
BFS path: 1 2 3 4 5 6 
Please enter the graph for Bellman Ford: 
5 8
1 2 -1
1 3 4
2 3 3
2 4 2
4 2 1
4 3 5
2 5 2
5 4 -3
Bellman Ford:
Vertex	Distance	Predecessor
1	0	- 
2	-1	1
3	2	2
4	-2	5
5	1	2

*/
