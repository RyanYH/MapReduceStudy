package com.sist.mapreduce;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;

public class MyMainDriver {
	public static void main(String[] args)
	throws Exception
	{
		//hadoop
		File dir = new File("./output");
		if(dir.exists())
		{
			File[] files = dir.listFiles();
			for(File f:files)
			{		
				f.delete();
			}
			dir.delete();
		}else{
			
		}
		Configuration conf =
				new Configuration();
		Job job = new Job(conf,"TwitterCount");
		job.setJarByClass(MyMainDriver.class);
		job.setMapperClass(MyMapper.class);
		// sort, suffle
		job.setReducerClass(MyReducer.class);
		
		
		// 출력형태
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 경로명
		FileInputFormat.addInputPath(job, new Path("./app.log"));
		FileOutputFormat.setOutputPath(job, new Path("./output"));
		
		//실행요청
		job.waitForCompletion(true);
		rGraph();
	}
	public static void rGraph()
	{
		try
		{
			RConnection rc=new RConnection();
			rc.voidEval("data<-read.table(\"/home/sist/bigdataStudy/MapreduceProject/output/part-r-00000\")");
			rc.voidEval("png(\"/home/sist/bigdataStudy/MapreduceProject/data.png\")");
			rc.voidEval("barplot(data$V2,names.arg=data$V1,col=rainbow(10))");
			rc.voidEval("dev.off()");
			/*REXP p=rc.eval("data$V1");
			String[] names=p.asStrings();
			
			for(String n:names)
			{
				System.out.println(n);
			}
			
			p=rc.eval("data$V2");
			int[] d=p.asIntegers();
			for(int i:d)
			{
				System.out.println(i);
			}
			for(int i=0;i<names.length;i++)
			{
				System.out.println(names[i]+" "+d[i]);
			}
			rc.close();*/
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
