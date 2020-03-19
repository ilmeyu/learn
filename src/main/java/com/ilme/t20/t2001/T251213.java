package com.ilme.t20.t2001;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

/**
 * 广度优先搜索
 *
 * @author ilme
 * @date 2020/1/25 12:13 下午
 **/
@Slf4j
public class T251213 {

	public static void main(String[] args) {
		Graph graph = new Graph("ilme");
		init(graph);

		bfs(graph, "小红");
	}

	public static void bfs(Graph graph, String dest) {
		Queue<Graph> searchQueue = new LinkedList<>();
		Queue<Graph> searchedQueue = new LinkedList<>();

		searchQueue.offer(graph);

		do {
			graph = searchQueue.poll();
			if (searchedQueue.contains(graph)) {
				continue;
			}
			log.info("当前位置: {}, 朋友: {}", graph.name, graph.friends);
			if (Objects.equals(dest, graph.getName())) {
				log.info("已找到: {}", graph.name);
			}
			Set<Graph> friend = graph.getFriends();
			if (null != friend && !friend.isEmpty()) {
				friend.forEach(e -> searchQueue.offer(e));
			}
			searchedQueue.offer(graph);
		} while (!searchQueue.isEmpty());
	}

	static void init(Graph graph) {
		Graph zs = new Graph("张三");
		Graph ls = new Graph("李四");
		Graph wmz = new Graph("王麻子");
		Graph zl = new Graph("赵六");
		Graph tq = new Graph("田七");
		Graph hmm = new Graph("韩梅梅");
		Graph ll = new Graph("李雷");
		Graph xh = new Graph("小红");


		Set<Graph> friends = new HashSet<>();
		graph.setFriends(friends);
		friends.add(zs);
		friends.add(ls);
		friends.add(wmz);

		Set<Graph> zsFriends = new HashSet<>();
		zs.setFriends(zsFriends);
		zsFriends.add(wmz);
		zsFriends.add(zl);
		zsFriends.add(ls);

		Set<Graph> zlFriends = new HashSet<>();
		zl.setFriends(zlFriends);
		zlFriends.add(tq);
		zlFriends.add(hmm);

		Set<Graph> hmmFriends = new HashSet<>();
		hmm.setFriends(hmmFriends);
		hmmFriends.add(ll);
		hmmFriends.add(xh);
	}

	@Getter
	@Setter
	static class Graph implements Comparable<Graph> {

		String name;

		Set<Graph> friends;

		private Graph() {   }

		Graph(String name) {
			this.name = name;
		}

		@Override
		public int compareTo(Graph graph) {
			return this == graph ? 1 : name.equals(graph.getName()) ? 1 : 0;
		}

		@Override
		public String toString() {
			return "Graph{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
