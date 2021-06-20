# 创建主题courses-evaluation
kafka-topics.sh --zookeeper localhost:2181,localhost:2182,localhost:2183/kafka --create --topic courses-evaluation --partitions 3 --replication-factor 2