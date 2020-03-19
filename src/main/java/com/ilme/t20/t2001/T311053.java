package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * K邻近算法(K nearest neighbours) - 推荐系统
 *
 * 值越小越相近
 *
 * @author ilme
 * @date 2020/1/31 10:53 上午
 **/
@Slf4j
public class T311053 {

	static MovieRating zsMovie = new MovieRating();
	static MovieRating lsMovie = new MovieRating();
	static MovieRating wwMovie = new MovieRating();
	static MovieRating zlMovie = new MovieRating();

	static {
		zsMovie.userName = "张三";
		zsMovie.movie.put("复仇者联盟", 8);
		zsMovie.movie.put("绿巨人", 7);
		zsMovie.movie.put("囧妈", 4);

		lsMovie.userName = "李四";
		lsMovie.movie.put("复仇者联盟", 8);
		lsMovie.movie.put("绿巨人", 8);
		lsMovie.movie.put("囧妈", 8);

		wwMovie.userName = "王五";
		wwMovie.movie.put("复仇者联盟", 5);
		wwMovie.movie.put("绿巨人", 4);
		wwMovie.movie.put("囧妈", 8);

		zlMovie.userName = "赵六";
		zlMovie.movie.put("复仇者联盟", 9);
		zlMovie.movie.put("绿巨人", 8);
		zlMovie.movie.put("囧妈", 8);
	}

	public static void main(String[] args) {
		log.info("{}和{}相似度: {}", zlMovie.userName, lsMovie.userName, knn(zlMovie, lsMovie));
		log.info("{}和{}相似度: {}", zlMovie.userName, wwMovie.userName, knn(zlMovie, wwMovie));
		log.info("{}和{}相似度: {}", zlMovie.userName, zlMovie.userName, knn(zlMovie, zlMovie));
		log.info("{}和{}相似度: {}", lsMovie.userName, zlMovie.userName, knn(lsMovie, zlMovie));
	}

	static Double knn(MovieRating source, MovieRating target) {
		Set<String> movieNames = source.movie.keySet();

		Integer sum = 0;

		Iterator<String> iterator = movieNames.iterator();
		while (iterator.hasNext()) {
			String movieName = iterator.next();

			Integer sourceRating = source.movie.get(movieName);
			Integer targetRating = target.movie.get(movieName);

			if (null == targetRating) {
				continue;
			}

			sum += (sourceRating - targetRating) * (sourceRating - targetRating);
		}

		return Math.sqrt(sum);
	}

	static class MovieRating {
		String userName;

		/**
		 * k : 电影名称
		 * V : 电影评分
		 */
		Map<String, Integer> movie = new HashMap<>();
	}
}
