package com.sist.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one =
			new IntWritable(1);
private Text result = new Text();
public Text getResult() {
	return result;
}
public void setResult(Text result) {
	this.result = result;
}
public IntWritable getOne() {
	return one;
} 

}
