package com.ilme.t20.t2001;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.collections.Ints;

import java.util.*;

/**
 * 谷歌工具集guava
 *
 * @author ilme
 * @date 2020/1/6 10:59 上午
 **/
@Slf4j
@Test
public class T061059 {

	public void test() {
		Table<String, String, Integer> table = HashBasedTable.create();

		table.put("张三", "语文", 55);
		table.put("张三", "英语", 87);
		table.put("里斯", "语文", 22);
		table.put("王武", "数学", 44);
		table.put("李四", "数学", 76);

		log.info("{}", table);

		Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
		cells.forEach(e -> {
			log.info("|{}\t|{}\t|{}|", e.getRowKey(), e.getColumnKey(), e.getValue());
			log.info("├─────┼───────┼──┤");
		});

	}

	// 自定义ListMultimap
	public void testCustomListMultimap() {
		HashMap<String, Collection<Integer>> map = new HashMap<>();
		Supplier<List<Integer>> supplier = LinkedList::new;

		ListMultimap<String, Integer> listMultimap = Multimaps.newListMultimap(map, supplier);

		listMultimap.putAll("A", Ints.asList(1, 1, 2));
		listMultimap.putAll("A", Ints.asList(1, 1, 2));
		listMultimap.putAll("B", Ints.asList(2, 1, 3));
		listMultimap.putAll("C", Sets.newHashSet(3, 1, 4));
	}

	// 可重复key和value的map集合
	public void testListMultimap() {
		ListMultimap<String, Integer> listMultimap = MultimapBuilder
			.hashKeys()
			.arrayListValues()
			.build();

		// 向集合中添加重复键和重复值
		listMultimap.putAll("A", Ints.asList(1, 1, 2));
		listMultimap.putAll("A", Ints.asList(1, 1, 2));
		listMultimap.putAll("B", Ints.asList(2, 1, 3));
		listMultimap.putAll("C", Ints.asList(3, 1, 4));
		listMultimap.put("D", 0);
		listMultimap.put("D", 0);

		log.info("listMultimap: {}", listMultimap);

		// 反转
		SetMultimap<Integer, String> setMultimap = Multimaps.invertFrom(
			listMultimap,
			MultimapBuilder.hashKeys().hashSetValues().build()
		);

		log.info("listMultimap 反转: {}", setMultimap);

		Assert.assertEquals(listMultimap.get("D").size(), 2);
	}

	// 一组字符串集合按长度分组
	public void testMultimaps_index() {
		Set<String> digits = ImmutableSet.of(
			"zero", "one", "two", "three", "four",
			"five", "six", "seven", "eight", "nine"
		);
		log.info("原始数据: {}", digits);

		Function<String, Integer> keyFunction = String::length;
		ImmutableListMultimap<Integer, String> result = Multimaps.index(digits, keyFunction);

		log.info("按照字符串长度分组后的数据: {}", result);
	}

	// 可重复Set集合
	public void testMultiSet() {
		Multiset<String> multiset_1 = HashMultiset.create();
		multiset_1.add("A");
		multiset_1.add("B");
		multiset_1.add("A");
		multiset_1.add("A", 2);

		// 创建不可变的可重复Set集合
		ImmutableMultiset<String> multiset_2 = ImmutableMultiset
			.<String>builder()
			.addCopies("A", 5)
			.build()
			;

		log.info("multiset_1: {}", multiset_1);
		log.info("multiset_2: {}", multiset_2);
		log.info("multiset_1 里有{}个\"A\"", multiset_1.count("A"));

		log.info(
			"multiset_1 包含 multiset_2 : {}",
			multiset_1.containsAll(multiset_2)
		);

		log.info(
			"multiset_1 完全包含 multiset_2 : {}", // multiset_1里面只有4个A、而multiset_2里面有5个A
			Multisets.containsOccurrences(multiset_1, multiset_2)
		);

		Multisets.copyHighestCountFirst(multiset_1);
	}

	public void testSets() {
		// 开始

		// 准备两个Set集合
		Set<Integer> set_1 = Sets.newHashSet(1, 2, 3);
		Set<Integer> set_2 = Sets.newHashSet(2, 3, 4);
		// 输出集合内容
		log.info("set_1: {}", set_1);
		log.info("set_2: {}", set_2);
		// 取交集
		Sets.SetView<Integer> integers = Sets.intersection(set_1, set_2);
		log.info("交集: {}", integers);
		// 取并集
		Sets.SetView<Integer> union = Sets.union(set_1, set_2);
		log.info("并集: {}", union);
		// 取差集
		Sets.SetView<Integer> difference = Sets.difference(set_1, set_2);
		log.info("差集: {}", difference);
		// 对等差分
		Sets.SetView<Integer> symmetricDifference = Sets.symmetricDifference(set_1, set_2);
		log.info("对等差分: {}", symmetricDifference);

		// 结束
	}

	// 字符串判空
	public void testStrings() {
		log.info("\n瓜娃的Strings对字符串判空"
			+ "\nnull:\t{}"
			+ "\nblank:\t{}"
			+ "\nspace:\t{}"
			+ "\n\"Java\":\t{}",
			Strings.isNullOrEmpty(null),
			Strings.isNullOrEmpty(""),
			Strings.isNullOrEmpty(" "),
			Strings.isNullOrEmpty("Java")
		);

	}

	// 不可变集合
	public void testImmutableList() {
		// 特殊写法
		List<Integer> build = ImmutableList.<Integer>builder().build();

		List<Integer> immutableList = ImmutableList.of(1, 2, 3);

		log.info("immutableList: {}", immutableList);

		try {
			// 移除元素会抛出 UnsupportedOperationException 异常
			immutableList.remove(0);
		} catch (UnsupportedOperationException ex) {
			log.warn(ex.getMessage());
		}

		log.info("immutableList: {}", immutableList);
	}
}
