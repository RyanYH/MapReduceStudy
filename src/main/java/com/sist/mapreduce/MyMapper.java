package com.sist.mapreduce;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * @ysdyka: 안철수 ˝´강남역 묻지마 살인사건´ 정치권이 나서야…증오범죄 싹 근절토록 노력˝ https://t.co/4crSFFNcXz
 * @ysdyka: 안철수 ˝´강남역 묻지마 살인사건´ 정치권이 나서야…증오범죄 싹 근절토록 노력˝ https://t.co/4crSFFNcXz
 * @ysdyka: 안철수 ˝´강남역 묻지마 살인사건´ 정치권이 나서야…증오범죄 싹 근절토록 노력˝ https://t.co/4crSFFNcXz
 * 
 * 안철수 1
 * 안철수 1	 -> 안철수[1,1,1] => 안철수 3
 * 안철수 1
 */

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one =
			new IntWritable(1);
	private Text result = new Text();
   String[] data = {"문재인","안철수","김무성",
			   "오세훈","박원순","유승민",
			   "김부겸","정의화","반기문",
			   "안희정"};
	Pattern[] pattern = new Pattern[data.length];
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text , IntWritable>.Context context)
			throws IOException, InterruptedException {
		for(int i=0;i<data.length;i++)
		{
			pattern[i] = Pattern.compile(data[i]);
		}
		Matcher[] matcher = new Matcher[data.length];
		for(int i=0;i<data.length;i++)
		{
			matcher[i] = pattern[i].matcher(value.toString());
			while(matcher[i].find())
			{
				result.set(data[i]);
				context.write(result, one);
			}
		}
		
	}
}

