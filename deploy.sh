#gradle clean
gradle war -x test -Dorg.gradle.java.home=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
rm -rf /Users/engine/webproject/release-sdk-mnanger/*
unzip -o -d /Users/engine/webproject/release-sdk-mnanger/ build/libs/release_manager.war
docker restart sdk-manager
