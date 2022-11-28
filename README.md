# 前言

本人安卓开发，成天想着自己弄一全套开发，刚好在公司给了个机会参与了后管开发，虽技术有点羞涩，但还说的过去，并且感觉后管原先代码里面漏洞过多（凡是我不清楚的逻辑都是漏洞！！！），所以在此利用业余时间耗时近3个月对这套saas平台做了梳理和改进，主要吃透了里面的业务逻辑。

>在此感谢自己的务实和努力。更要感谢自己的兴趣爱好就是敲代码！未来的路扑所迷离，但是此时此刻的自己非常充实。

# 简介 #

## saas简介 ##

**官方解释：摘自百度百科**

SaaS即Software-as-a-Service（软件即服务）是随着互联网技术的发展和应用软件的成熟， 在21世纪开始兴起的一种完全创新的软件应用模式。传统模式下，厂商通过License将软件产品部署到企业内部多个客户终端实现交付。SaaS定义了一种新的交付方式，也使得软件进一步回归服务本质。企业部署信息化软件的本质是为了自身的运营管理服务，软件的表象是一种业务流程的信息化，本质还是第一种服务模式，SaaS改变了传统软件服务的提供方式，减少本地部署所需的大量前期投入，进一步突出信息化软件的服务属性，或成为未来信息化软件市场的主流交付模式。 

**个人理解**

一个系统端和多个企业端：

1. 系统端管理企业端，

2. 企业端管理本企业服务，服务包括基础服务、本企业业务服务、app或网站服务

3. 企业端可以归类在同一类企业，例如都是购物类企业，那么系统端对当前类企业配置同一类模块即可，有利于结构的重复利用；

4. 每一个app或网站是针对某一类企业服务的；例如购物类企业，如果该企业有app或网站，那么该app只服务于购物类企业；

 - 注：当然也可以做成一个综合性saas app客户端，但是完全没这个必要，无论从代码还是业务逻辑角度来说都过于复杂。


## 项目简介 ##

baseSaas是一个java ee平台，采用前后端分离技术，基于技术组合Spring Boot、springcloud+nocas微服务、Apache Shiro、MyBatis、Bootstrap等），分两大块：系统端管理平台和企业端管理平台。

1. 系统端管理平台内置模块：就一个系统管理，系统管理下有
 - （1）企业管理、
 - （2）参数维护、
 - （3）模块管理。

2. 企业端管理平台内置模块：

 - （1）基础功能模块：有系统管理和日志管理
 - ①系统管理有用户管理、角色管理、架构管理、公司简介、字典管理；
 - ②日志管理有登录日志和操作日志；
 - （2）其他业务逻辑模块：客户管理，当然这里点击是404的，因为我没有实现；

# 技术选型 #

## 工具选型 ##

1. idea 2019.2

2. webstorm 2018.3.4

3. mysql 8.0.16

4. navicat mysql可视化工具版本随意

## 开发环境 ##

1. jdk1.8；

2. nocas 2.0.3

3. redis 版本随意


## 项目运行 ##

1. 肯定需要安装上面的工具和开发环境；

2. 通过navicat数据库可视化工具新建一个saas-base数据库；然后将[saas-base.sql](https://github.com/hellogaod/saas-base)中的文本复制到当前数据库中的命令行执行（直接运行好像会报错，建议脚本执行sql文件里面的文本内容）；

3. [baas-saas-service](https://github.com/hellogaod/saas-base/tree/nocas/base-saas-service)是服务端代码；[base-saas-manage](https://github.com/hellogaod/saas-base/tree/nocas/base-saas-manage)是后管vue代码；

4. 服务端代码启动，在idea中打开，全局搜索redis修改成自己的密码。全局搜索datasource,修改里面的数据库用户名和密码；

5. 后管vue代码，webstorm打开项目
 - （1）先`npm install`，在执行`npm run dev`
 - （2）[http://localhost:8080/sys-manage](http://localhost:8080/sys-manage)表示系统端登录地址；
 - （3）[http://localhost:8080/](http://localhost:8080/)和[http://localhost:8080/ent-manage](http://localhost:8080/ent-manage)表示企业端登录地址
 - （4）两个登录用户名和密码已默认存在于输入框，修改验证码后可直接用于登录。

# 功能 #

## 服务框架简介 ##

当前存在三个服务，zuul，manager，common：

1. zuul-service是springcloud中的微服务网关，为了微服务架构中的服务提供统一的访问入口；

2. manage-service是当前saas的系统端和企业端提供api接口的服务；

3. common-service通用接口，现在存放提取和校验图形验证码 还有文件上传；


## 基础功能介绍 ##

### 系统端 

就一个系统管理，分为：模块管理，参数维护，企业管理

**模块管理**

![1.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2fbc3e4b4c2441cd9b65b044174f1c9c~tplv-k3u1fbpfcp-watermark.image?)

新建模块，以及当前模块下新建菜单，当前系统架构仅支持二级菜单模式；

**参数维护**

![2.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f793ba4499a2433595868243dc5e6a67~tplv-k3u1fbpfcp-watermark.image?)

分为基础参数和三方参数。

**企业管理**

![3.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/cc3e39ed01db45faa0428c7f4b3bac42~tplv-k3u1fbpfcp-watermark.image?)

新建企业，并且给当前企业分配参数维护模块的参数和模块。


### 企业端

主要讲解两个功能，日志管理和系统管理。日志管理分为登录日志和操作日志，都是针对当前企业的；系统管理分为：字典管理，公司简介，架构管理，角色管理，用户管理。


**字典管理**

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dffa997844e142f191b3955b4dda8677~tplv-k3u1fbpfcp-watermark.image?)

比如配置一个商品类型，商品类型下分为普通商品，昂贵商品（当然完全可以重新生成一张表存储商品类型，方法不是唯一的）。


**公司简介**

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1b2306cec5974955a2f320b78c845943~tplv-k3u1fbpfcp-watermark.image?)

当前公司简介，这里的配置会放到网站相应的公司简介部分显示。

**架构管理**

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3c8f5582e15843b4bd333bf0e0e5cbee~tplv-k3u1fbpfcp-watermark.image?)

当前公司的组织架构。在系统端企业管理中新建企业时，会默认新建当前企业作为第一级企业架构。

**角色管理**

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6a2cb0bb06e24d239f60de5153cde614~tplv-k3u1fbpfcp-watermark.image?)

主要用于创建角色，以及给当前角色分配菜单，这里面还可以选择多个模块下的菜单；

**用户管理**

![](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/75b978d722c84511ac75abc7abe3b3b9~tplv-k3u1fbpfcp-watermark.image?)

创建当前企业下的用户，并且给当前用户分配角色。

这里还有一个脱敏：如果在企业端-模块管理-业务功能下新建的菜单支持脱敏的情况下，那么这里可以对当前用户是否对当前菜单脱敏，如果脱敏，那么当前用户访问当前菜单功能一些重要信息将会以"*"号替代。

当前具体业务逻辑需要自己定，我这里并没有做脱敏处理。

**登录日志**

![](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e9ebeda34f894810a2c31a169d283251~tplv-k3u1fbpfcp-watermark.image?)

当前企业下的用户登录情况；

**操作日志**

![](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a0432b96b8514605a12fe8fe72958a6e~tplv-k3u1fbpfcp-watermark.image?)

当前企业下的用户操作了哪些后管菜单。

#总结

项目[git地址](https://github.com/hellogaod/saas-base)

第一，水平有限，无论是写作水平还是开发水平，所以只能写的这么多了。

第二，感觉写那么多，那么高大上，把自己整的跟个神一样（其实土鳖一枚）也没用，毕竟我对自己负责，而不是其他人；主要还是在于自己的理解。

等你正在想要去理解吃透这里面的技术，沉下心来学习，不需要多少高大上的胡吹。

不多说，不想加班，去吃杂酱面 + 肉夹馍，再来一份面汤，24元吃的爽的一笔，再次贴一个支付宝二维码，看看有没有请我吃，哈哈哈~~~


![12.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b12541043ff2469cbcbea220d2ee2290~tplv-k3u1fbpfcp-watermark.image?)

![23.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/40b999373e8e42c2a1d4d490375bde21~tplv-k3u1fbpfcp-watermark.image?)





