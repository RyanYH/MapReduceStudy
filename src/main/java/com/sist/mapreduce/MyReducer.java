package com.sist.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/*
 * 	hello hello hadoop java java hadoop hadoop3
 * 	 
 *  java 1
 *  java 1
 *  
 *  hello 1
 *  hello 1
 *  
 *  hadoop 1
 *  hadoop 1
 *  hadoop 1
 * 
 */
public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable res = new IntWritable();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable v:value)
		{
			sum+=v.get();
		}
		res.set(sum); // int형을 intWritable형변환
		context.write(key, res);
	}

}
