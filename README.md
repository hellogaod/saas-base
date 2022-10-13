# 前言

saas基础版本，前后端分离技术


# saas基础版本代码规范整合计划（后面会删除这段的）

**原则1：先框架，后细节；再先后向前**

**原则2：先易后难**

## 架构整合

主要是大框架的整合 - 由内向外

1. web后管整合，每个文件夹中的代码都应该理解基础上确定是否需要整合

 - （1）路由代码整合；
 - （2）业务代码整合；
 - （3）其他文件夹可以一个个去理解，确定如何规整；
 - （4）ts下面的js删掉

2. web service整合,同样的每个文件都应该理解的基础上在进行整合

 - （1）zuul是否需要整理，并且把resources中改成local、dev、prod三种：理解一下yml文件
 - （2）重点：把 manager和sysem-web 两个模块整合成一个，叫manage-service；
 - （3）把util和common整合成一个，具体如何整合自行去理解
 - （4）整理出来一个common-service服务，用于管理一些通用的服务：common-service中不需要处理日志，记得过滤一下；
 - （5）看下fastdfs是否可用，如果不可用就算了，改成一般服务即可，否则直接使用fastdfs即可；
 - （6）不要出现jar冲突情况；并且如何查看哪些jar实际并没有被用到；

## 细节整合

主要是细节处的整合

1. 数据库整合；

 - （1）表命名：①system，enterprise，customer（user）；还有其他再斟酌; ②表主键可以直接使用id，引用可以使用当前表简称（去掉开头e.g.sys）_id这样理解起来也非常方便；③表名不要使用_info子类的，应该使用_detail，防止过于俗套话；

 - （2）为啥ent_user,ent_role,ent_menu都有org_id组织架构，不合理

2. web service整合

 - （1）比较简单的sql可以参考商户号管理的写法去写；
 - （2）里面很多个api/login/doLogin，这样的写法，把它整合到一个文件中，不然写了很多个地方；主要是AccessFilter和PostFiler两个文件中；
 

3. web后管整合

 - （1）后管vue和service端的返回数据统一处理部分写的贼烂；
 - （2）多模块，如果已经解决就算了；

4. 还差一个fastdfs

5. 加解密

 -  [SpringBoot 通过Filter与AOP实现请求加密解密功能](https://blog.csdn.net/afgasdg/article/details/120572937)
 
 - 在filter中实现加解密功能；


# 开发工具及版本



# 技术简介

## saas简介

## 微服务

## nocas和eureka


# 开发阶段

##需求

## 数据库设计

##功能简介


#总结

别忘了消去密码啊：GAO2651qiang之类的


（会删）这个项目的后续：后续是开发一个资讯类app，收费的兄弟。





