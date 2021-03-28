# 第2周作业


#### Week02 作业题目（周四）：
>4.（必做） 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。
>#### 总结:
>1. 如果内存小于100M，使用串行收集器Serial 
>2. 如果是单核，并且没有停顿时间的要求，串行收集器Serial
>3. 如果允许停顿时间超过1秒，选择并行收集器Parallel
>4. 如果响应时间最重要，并且不能超过1秒，使用并发收集器ParNew+CMS
>5. 4G以下可以用parallel，4-8G可以用ParNew+CMS，8G以上可以用G1

#### Week02 作业题目（周六）：
>2.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 Github。
>#### 操作步骤:
>1. 打开 [HttpServer01](src/main/java/HttpServer01.java) 先运行 main 方法
>2. 其次打开 [OkHttpUtils](src/main/java/OkHttpUtils.java) 运行 main 方法
