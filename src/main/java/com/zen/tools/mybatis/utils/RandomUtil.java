package com.zen.tools.mybatis.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {


  /**
   * 返回0－Long.MAX_VALUE间的随机Long, 使用ThreadLocalRandom.
   */
  public static long nextLong() {
    return nextLong(ThreadLocalRandom.current());
  }

  /**
   * 返回0－Long.MAX_VALUE间的随机Long, 可传入SecureRandom或ThreadLocalRandom
   */
  public static long nextLong(Random random) {
    long n = random.nextLong();
    if (n == Long.MIN_VALUE) {
      n = 0;
    } else {
      n = Math.abs(n);
    }
    return n;
  }
}
