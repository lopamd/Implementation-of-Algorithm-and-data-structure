
package cs6301.g45;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import cs6301.g45.Graph.Edge;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Java Program to implement Prim1 Algorithm
 * @author Lopamudra 
 */

public class PrimMST extends GraphAlgorithm<PrimMST.PrimVertex> {
    static final int Infinity = Integer.MAX_VALUE;
	Graph.Vertex src;							//source vertex to start from
	
	static class PrimVertex {					//class to store information about a vertex in this algorithm
		boolean seen;							//to mark a vertex as seen/visited
		Graph.Vertex parent;					//to mark the parent of a vertex
		
		public PrimVertex(Graph.Vertex u) {
			seen = false;
			parent = null;
		}
	}
	
    public PrimMST(Graph g) {
    	super(g);
		node = new PrimVertex[g.size()];
    	
    	for(Graph.Vertex u: g) {				// Create array for storing vertex properties
    	    node[u.getName()] = new PrimVertex(u);
    	}
    }

    /**
     * Method to find MST using Prim's algorithm with PriorityQueue<Edge>
     * @param s: Graph.Vertex - source vertex to start from
     * @return wmst: int - minimum weight of MST
     */
    public int prim1(Graph.Vertex s) {
        int wmst = 0;									//minimum weight of MST

        for(Graph.Vertex u: g) {						//initially initialize all vertices as not seen and parent as null
			getVertex(u).seen = false;
			getVertex(u).parent = null;
		}
		
        src = s;										//source vertex
		getVertex(src).seen = true;						//source is seen
		Graph.Vertex u,v;								//to represent adjacent vertices of an edge
		
		PriorityQueue<Graph.Edge> pQEdge = new PriorityQueue<Graph.Edge>(new Comparator<Graph.Edge>() {		//Comparator for PriorityQueue<Edges> to compare edge weights 
	        public int compare(Graph.Edge edge1, Graph.Edge edge2) {
	            if(edge1.weight > edge2.weight) return 1;
	            else if(edge1.weight < edge2.weight) return -1;
	            else return 0;
	        }
	    });
		
		for(Graph.Edge e: src) {						//add source vertex edge into PriorityQueue
			pQEdge.add(e);
		}
		
		while(!pQEdge.isEmpty()) {						//until the PriorityQueue is not empty 
			Graph.Edge e = pQEdge.remove();				//Edge that has minimum weight is removed
			u = e.from;									//vertices of an edge
			v = e.to;
			
			if(getVertex(v).seen && getVertex(u).seen) 	//if both edge vertices are seen then skip that edge
				continue;
						
			if (getVertex(u).seen) {					//if only one vertex is seen
				wmst = wmst + e.weight;					//add its minimum weight to wmst
				getVertex(v).seen = true;				//mark it as seen
				getVertex(v).parent = u;				//MST is implicitly stored by parent pointers
				System.out.println("("+(u.name+1)+","+(v.name+1)+")");
				for (Edge e2 : v.adj) {					
					pQEdge.add(e2);						//add its edge to PriorityQueue
				}
			} 
			else {
				wmst = wmst + e.weight;					//add its minimum weight to wmst
				getVertex(u).seen = true;				//mark it as seen
				getVertex(u).parent = v;				//MST is implicitly stored by parent pointers
				System.out.println("("+(u.name+1)+","+(v.name+1)+")");
				for (Edge e2 : u.adj)
					pQEdge.add(e2);						//add its edge to PriorityQueue
			}
		}
        return wmst;									//
    }

    public static void main(String[] args) throws FileNotFoundException {
    	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }

		Graph g = Graph.readGraph(in);
	    Graph.Vertex s = g.getVertex(1);
	
		Timer timer = new Timer();
		PrimMST mst = new PrimMST(g);
		int wmst = mst.prim1(s);
		System.out.println("MST weight: "+wmst);
		System.out.println(timer.end());
    }
}
