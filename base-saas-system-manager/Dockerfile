FROM zct1989/nginx-node:latest

COPY . /usr/local/code/
WORKDIR /usr/local/code

# 设置当前编译模式
ARG BUILD_ENV=prod
ENV env=$BUILD_ENV

# 修改nginx配置-添加html5 history模式支持
RUN sed -i 's/index  index.html index.htm;/try_files $uri $uri\/ \/index.html;/' /etc/nginx/conf.d/default.conf

# 项目编译
RUN npm install \
  --registry=http://nexus.daocloud.io/repository/daocloud-npm \
  --disturl=https://npm.taobao.org/dist \
  --sass-binary-site=http://npm.taobao.org/mirrors/node-sass \
  --ignore-scripts && \
  env=$BUILD_ENV npm run build && \
  rm -rf /usr/share/nginx/html/* && \
  cp -rf /usr/local/code/dist/* /usr/share/nginx/html/ && \
  rm -rf /usr/local/code

EXPOSE 80
