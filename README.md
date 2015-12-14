# schema

根据pangu项目实现了自定义的schema。
mina 实例
netty 实例
Dubbo 实例

运行的时候可能会遇到解析不了XML文件的错误SAXException：

在maven的本地仓库下找到maven->com->alibaba->dubbo->2.5.3->dubbo-2.5.3.jar (具体的看自己的本地仓库) 解压它获得dubbo.xsd文件

然后打开Eclipse：1. Window->Preferences->XML->XML Catalog->User Specified Entries窗口中,选择Add 按纽

注册服务选择：

生产用：
<dubbo:registry  protocol="zookeeper" address="172.17.0.119:2181,172.17.0.120:2181,172.17.0.121:2181,172.17.0.122:2181,172.17.0.123:2181" /> 
 ps：这里是把服务器方和提供方都注册到了zookeeper上统一管理。上面的IP为安装了zookeeper的服务器地址。如果不想用zookeeper管理的话，可以改为
 <!-- 使用multicast广播注册中心暴露发现服务地址 -->  
 测试用：<dubbo:registry address="multicast://224.5.6.7:1234" />




