package com.sist.mapreduce;

import twitter4j.FilterQuery;
import twitter4j.Twitter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/*
 * 1. Mapper(단어분석) => 형태소 분석
 * 2. Reducer -> 분석결과누적
 * 3. Driver(실행파일)
 * ====================================
 * 분석
 *   = 단순분석(구조가 있는 경우)
 *   = 복합분석(정규식)
 */
public class MainClass {	
	public static void main(String[] args)
	throws Exception
	{
		TwitterStream ts = new TwitterStreamFactory().getInstance();
		TwitterListener list = new TwitterListener();
		ts.addListener(list);
		String[] data = {"문재인","안철수","김무성",
						   "오세훈","박원순","유승민",
						   "김부겸","정의화","반기문",
						   "안희정"};
		FilterQuery fq = new FilterQuery();
		fq.track(data);
		ts.filter(fq);
		
	}
}
