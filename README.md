# 带着问题看源码

- [ ] BeanFactory和ApplicationContext关系和区别
- [ ] BeanFactory和FactoryBean区别
- [ ] Spring Bean的生命周期
- [ ] Spring如何解决循环依赖
- [ ] ListableBeanFactory、HierarchicalBeanFactory、AutowireCapableBeanFactory和SimpleJndiBeanFactory区别
- [ ] Resource使用了什么策略模式
- [ ] Resource核心类说明


# 解答

## Resource核心类说明

- FileSystemResource：对 java.io.File 类型资源的封装，只要是跟 File 打交道的，基本上与 FileSystemResource 也可以打交道。支持文件和 URL 的形式，实现 WritableResource 接口，且从 Spring Framework 5.0 开始，FileSystemResource 使用 NIO2 API进行读/写交互。
- ByteArrayResource ：对字节数组提供的数据的封装。如果通过 InputStream 形式访问该类型的资源，该实现会根据字节数组的数据构造一个相应的 ByteArrayInputStream。
- UrlResource ：对 java.net.URL类型资源的封装。内部委派 URL 进行具体的资源操作。
- ClassPathResource ：class path 类型资源的实现。使用给定的 ClassLoader 或者给定的 Class 来加载资源。
- InputStreamResource ：将给定的 InputStream 作为一种资源的 Resource 的实现类。
