#!/bin/bash

version=1.0.5-SNAPSHOT

declare -a arr=("~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug.aar" \
     "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version.pom" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.pom" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version.pom" )


for i in "${arr[@]}"
do
    echo "Testing for $i"
    if [ ! -f "`eval echo ${i//>}`"  ] ; then
        echo "File $i is not there, aborting."
        exit 1

    else
       echo "$i" "OK";
    fi
done