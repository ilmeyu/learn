# IOC-默认标签解析-上

## 概述

> 接前两篇文章[整体架构和环境搭建](../整体架构和环境搭建/整体架构和环境搭建.md)和[IOC-容器的基本实现](../IOC-容器的基本实现/IOC-容器的基本实现.md)

本文主要研究Spring标签的解析，Spring的标签中有默认标签和自定义标签，两者的解析有着很大的不同，这次重点说默认标签的解析过程。

默认标签的解析是在`DefaultBeanDefinitionDocumentReader.parseDefaultElement`函数中进行的，分别对4种不同的标签（import,alias,bean和beans）做了不同处理。我们先看下此函数的源码:

```java
private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
    if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
        importBeanDefinitionResource(ele);
    }
    else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
        processAliasRegistration(ele);
    }
    else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
        processBeanDefinition(ele, delegate);
    }
    else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
        // recurse
        doRegisterBeanDefinitions(ele);
    }
}
```