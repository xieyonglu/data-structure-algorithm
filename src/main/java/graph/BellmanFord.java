package graph;

import java.util.Scanner;

public class BellmanFord {

	public long[] dist; // 用于存放第0个顶点到其它顶点之间的最短距离

	// 内部类，表示图的一条加权边
	class edge {
		public int from; // 边的起点
		public int to; // 边的终点
		public int weight; // 边的权值

		edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	// 返回第0个顶点到其它所有顶点之间的最短距离
	public boolean getShortestPaths(int n, edge[] edges) {
		dist = new long[n];
		for (int i = 1; i < n; i++)
			dist[i] = Integer.MAX_VALUE; // 初始化第0个顶点到其它顶点之间的距离为无穷大，此处用Integer型最大值表示
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < edges.length; j++) {
				if (dist[edges[j].to] > dist[edges[j].from] + edges[j].weight)
					dist[edges[j].to] = dist[edges[j].from] + edges[j].weight;
			}
		}

		boolean judge = true;
		for (int i = 1; i < n; i++) { // 判断给定图中是否存在负环
			if (dist[edges[i].to] > dist[edges[i].from] + edges[i].weight) {
				judge = false;
				break;
			}
		}
		return judge;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		BellmanFord test = new BellmanFord();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个图的顶点总数n和边总数p：");
		int n = in.nextInt();
		int p = in.nextInt();
		edge[] edges = new edge[p];
		System.out.println("请输入具体边的数据：");
		for (int i = 0; i < p; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int weight = in.nextInt();
			edges[i] = test.new edge(from, to, weight);
		}
		if (test.getShortestPaths(n, edges)) {
			for (int i = 0; i < test.dist.length; i++)
				System.out.print(test.dist[i] + " ");
		} else
			System.out.println("给定图存在负环，没有最短距离");
	}

}

/**
 * https://blog.csdn.net/a1439775520/article/details/96903743
 * 请输入一个图的顶点总数n和边总数p：
 * 6 18 
 * 请输入具体边的数据： 
 * 0 1 6 
 * 0 2 3 
 * 1 2 2 
 * 1 3 5 
 * 2 3 3 
 * 2 4 4 
 * 3 4 2 
 * 3 5 3 
 * 4 5 5 
 * 1 0 6 
 * 2 0 3 
 * 2 1 2 
 * 3 1 5 
 * 3 2 3 
 * 4 2 4 
 * 4 3 2 
 * 5 3 3 
 * 5 4 5 
 * 0 5 3 6 7 9
 */
