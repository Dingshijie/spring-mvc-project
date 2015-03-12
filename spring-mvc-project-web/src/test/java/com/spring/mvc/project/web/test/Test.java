package com.spring.mvc.project.web.test;

/**
 * &运算算法
 * @author dingshijie
 *
 */
public class Test {

	public static void main(String[] args) {

		for (int i = 0; i < 64; i++) {
			String s = i + "";
			for (int j = 1; j <= 64; j = j * 2) {
				String tag = new Test().explainTags(i, j);
				s += ("".equals(tag) ? "" : "," + tag);
			}
			s += ")";
			s = s.replaceFirst(",", "(");
			System.out.println(s);
		}

	}

	public String explainTags(int i, int tag) {
		//其内部是将结果转换成二进制后进行“与”运算， 1&1结果为1，其余为0，如下：
		// 值1： 001100
		// 值2： 010100
		// 结果：000100 
		if ((i & tag) == tag) {
			return String.valueOf(tag);
		}
		return "";
	}
}
