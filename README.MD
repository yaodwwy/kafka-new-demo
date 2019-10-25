>使用 kafka-topics.sh 创建单分区单副本的 topic test：

    bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

>查看 topic 列表：
 
    bin/kafka-topics.sh --list --zookeeper localhost:2181

>使用 kafka-console-producer.sh 生产消息：
 
    bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

>使用 kafka-console-consumer.sh 接收消息并在终端打印：
 
    bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
