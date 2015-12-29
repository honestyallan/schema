# schema

根据pangu项目实现了自定义的schema。
mina 实例
netty 实例
Dubbo 实例
spring mail 实例
自定义注解 实例

运行的时候可能会遇到解析不了XML文件的错误SAXException：

在maven的本地仓库下找到maven->com->alibaba->dubbo->2.5.3->dubbo-2.5.3.jar (具体的看自己的本地仓库) 解压它获得dubbo.xsd文件

然后打开Eclipse：1. Window->Preferences->XML->XML Catalog->User Specified Entries窗口中,选择Add 按纽

注册服务选择：

生产用：<!--dubbo:registry  protocol="zookeeper" address="172.17.0.119:2181,172.17.0.120:2181,172.17.0.121:2181,172.17.0.122:2181,172.17.0.123:2181" -->  
 ps：这里是把服务器方和提供方都注册到了zookeeper上统一管理。上面的IP为安装了zookeeper的服务器地址。如果不想用zookeeper管理的话，可以改为
 <!-- 使用multicast广播注册中心暴露发现服务地址 -->  
 测试用：<!--dubbo:registry address="multicast://224.5.6.7:1234" --> 


thrift
 数据类型
     * Base Types：基本类型
     * Struct：结构体类型
     * Container：容器类型，即List、Set、Map
     * Exception：异常类型
     * Service： 定义对象的接口，和一系列方法

协议
  Thrift可以让你选择客户端与服务端之间传输通信协议的类别，在传输协议上总体上划分为文本(text)和二进制(binary)传输协议, 为节约带宽，提供传输效率，一般情况下使用二进制类型的传输协议为多数，但有时会还是会使用基于文本类型的协议，这需要根据项目/产品中的实际需求：
    * TBinaryProtocol – 二进制编码格式进行数据传输。
    * TCompactProtocol – 这种协议非常有效的，使用Variable-Length Quantity (VLQ) 编码对数据进行压缩。
    * TJSONProtocol – 使用JSON的数据编码协议进行数据传输。
    * TSimpleJSONProtocol – 这种节约只提供JSON只写的协议，适用于通过脚本语言解析
    * TDebugProtocol – 在开发的过程中帮助开发人员调试用的，以文本的形式展现方便阅读。

传输层
    * TSocket- 使用堵塞式I/O进行传输，也是最常见的模式。
    * TFramedTransport- 使用非阻塞方式，按块的大小，进行传输，类似于Java中的NIO。
    * TFileTransport- 顾名思义按照文件的方式进程传输，虽然这种方式不提供Java的实现，但是实现起来非常简单。
    * TMemoryTransport- 使用内存I/O，就好比Java中的ByteArrayOutputStream实现。
    * TZlibTransport- 使用执行zlib压缩，不提供Java的实现。

服务端类型
    * TSimpleServer -  单线程服务器端使用标准的堵塞式I/O。
    * TThreadPoolServer -  多线程服务器端使用标准的堵塞式I/O。
    * TNonblockingServer – 多线程服务器端使用非堵塞式I/O，并且实现了Java中的NIO通道。




