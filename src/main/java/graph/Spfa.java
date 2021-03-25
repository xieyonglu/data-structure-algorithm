package graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Spfa {

	public long[] dist; // 用于得到第s个顶点到其它顶点之间的最短距离

	// 内部类，用于存放图的具体边数据
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

	/*
	 * 参数n:给定图的顶点个数
	 * 参数s:求取第s个顶点到其它所有顶点之间的最短距离 参
	 * 数edge:给定图的具体边
	 * 函数功能：如果给定图不含负权回路，则可以得到最终结果，如果含有负权回路，则不能得到最终结果
	 */
	public boolean getShortestPaths(int n, int s, edge[] edges) {
		ArrayList<Integer> queue = new ArrayList<>();
		dist = new long[n];
		boolean[] visited = new boolean[n];
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		dist[s] = 0; // 第s个顶点到自身距离为0
		visited[s] = true; // 表示第s个顶点进入数组队
		num[s] = 1; // 表示第s个顶点已被遍历一次
		queue.add(s); // 第s个顶点入队
		while (queue.size() != 0) {
			int first = queue.get(0); // 获取数组队中第一个元素
			queue.remove(0); // 删除数组队中第一个元素
			
			for (int i = 0; i < edges.length; i++) {
				// 当list数组队的第一个元素等于边edges[i]的起点时
				if (first == edges[i].from && dist[edges[i].to] > dist[edges[i].from] + edges[i].weight) {
					dist[edges[i].to] = dist[edges[i].from] + edges[i].weight;
					if (!visited[edges[i].to]) {
						queue.add(edges[i].to);
						num[edges[i].to]++;
						if (num[edges[i].to] > n)
							return false;
						visited[edges[i].to] = true; // 表示边edges[i]的终点to已进入数组队
					}
				}
			}
			visited[first] = false; // 顶点first出数组对
		}
		return true;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Spfa test = new Spfa();
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个图的顶点总数n起点下标s和边总数p：");
		int n = in.nextInt();
		int s = in.nextInt();
		int p = in.nextInt();
		edge[] edges = new edge[p];
		System.out.println("请输入具体边的数据：");
		for (int i = 0; i < p; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int value = in.nextInt();
			edges[i] = test.new edge(a, b, value);
		}
		if (test.getShortestPaths(n, s, edges)) {
			for (int i = 0; i < test.dist.length; i++)
				System.out.print(test.dist[i] + " ");
		} else
			System.out.println("给定图存在负环，没有最短距离");
	}
	
/**
 * https://blog.csdn.net/a1439775520/article/details/96904007
请输入一个图的顶点总数n起点下标s和边总数p：
6 1 18
请输入具体边的数据：
0 1 6
0 2 3
1 2 2
1 3 5
2 3 3
2 4 4
3 4 2
3 5 3
4 5 5
1 0 6
2 0 3
2 1 2
3 1 5
3 2 3
4 2 4
4 3 2
5 3 3
5 4 5
5 0 2 5 6 8
*/
}
