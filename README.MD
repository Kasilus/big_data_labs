#How to run lab2 in docker
1. Download docker image for hadoop. For example, from https://github.com/big-data-europe/docker-hadoop. Do some configuration (or use default) and start it with
    ```
    # docker-compose up -d  
    ```
    P.S. There is problem for 3.1.2 version. Resource manager crashes on startup, because it should be started in non-safe mode. So, wait while other containers will be started and then restart it.
    ```
    # docker restart resourcemanager
    ```
2. Prepare fs in namenode.
    ```
    # docker exec -it namenode bash
    # mkdir -p lab2/input
    # cd lab2
    # mkdir jar
    ```
3. Build your app in jar.
    Move hadoop-common-2.7.3.jar, hadoop-mapreduce-client-core-2.7.3.jar, org.apache.commons.cli-1.2.0.jar in lib/
    Command for edu.stanislav.bigdata.lab2.AverageCount.java (if you are in a root folder of project)
    ```
    # javac -cp "lib/*" -d out\bin src\edu\stanislav\bigdata\lab2\AverageCount.java
    # jar cvf HadoopMarketAverage.jar -C out/bin/ . 
    # mv .\HadoopMarketAverage.jar out\jar
    ```
4. Copy input file and jar to hadoop.
    ```
    # docker cp .\out\jar\HadoopMarketAverage.jar <namenode_container_hash>:lab2\jar\HadoopMarketAverage.jar
    # docker cp .\EURUSD_GBP_CHF.csv <namenode_container_hash>:lab2\input\EURUSD_GBP_CHF.csv 
    ```
5. Move input file to hdfs
    ```
    # docker exec -it namenode bash
    # hdfs dfs -put ./lab2/input/* lab2_input
    ```
6. Run app
    ```
    # hadoop jar lab2/jar/HadoopMarketAverage.jar edu/stanislav/bigdata/lab2/AverageCount hdfs:/// lab2_input/EURUSD_GBP_CHF.csv lab2_output
    ```
7. Check result
    ```
    # hadoop fs -cat lab2_output/*
    ```
