#! /bin/bash

function AutomaticDepoly(){

    echo "开始自动部署 hello 项目！";
    # 1. 杀死已经运行的线程
    ps -ef | grep hello-0.0.1-SNAPSHOT.jar | grep -v "grep" | cut -c 7-15 |xargs kill -9;
    # 2. 进入到目标目录
    echo "进入到目标项目目录" ;
    cd /Users/crazy/Documents/GitHub/WorkSpace/hello/;
    echo "git 开始拉取代码";
    # 4. git 拉取新的代码
    git pull ;
    # 5. maven 打包新的程序
    echo "maven 开始打包新的程序";
    mvn clean install -B ;
    # 6. 进入到 target 目录运行程序
    echo "进入到 target 目录下开始执行 java 程序";
    cd target/;
    java -jar hello-0.0.1-SNAPSHOT.jar &
}


AutomaticDepoly;
