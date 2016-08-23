#!/bin/bash

#this file verifies that the expected artifacts are published to maven local

#the version we're expecting
version=""

#the properties file that gradle uses
PROPERTIES_FILE=gradle.properties


# Reads property $2 from properties file $1 and echos the value. To call this method do:
# src http://gothither.blogspot.com/2012/06/read-value-from-java-properties-file-in.html
#     V=$(getProp filename property)
#
function getProp () {
    # ignore lines with '#' as the first non-space character (comments)
    # grep for the property we want
    # get the last match just in case
    # strip the "property=" part but leave any '=' characters in the value

    echo `sed '/^[[:space:]]*\#/d' $1 | grep $2  | tail -n 1 | cut -d "=" -f2- | sed 's/^[[:space:]]*//;s/[[:space:]]*$//'`
}

# get the version number from the gradle build
version=`eval getProp $PROPERTIES_FILE pom.version`


# these are all the files we're testing for the existence of

declare -a arr=(
      "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug.aar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug-sources.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version-debug-javadoc.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-aar/$version/hello-world-aar-$version.pom" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version-sources.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version-javadoc.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-lib/$version/hello-world-lib-$version.pom" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug-sources.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-barDebug-javadoc.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug-sources.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-bazDebug-javadoc.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug.apk" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug-sources.jar" \
      "~/.m2/repository/com/chrisdoyle/hello-world-apk/$version/hello-world-apk-$version-fooDebug-javadoc.jar" \
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




# BEGIN Issue 25

echo "     Issue #25 - verify overrides are correctly applied"

# strings to search for in our aar pom
declare -a strs=(
      "package=\"com.chrisdoyle.alex.wuz.here\"" \
      "android:versionCode=\"9999\"" \
      "android:versionName=\"OU812\"" \
      "android:minSdkVersion=\"16\"" \
       )

for i in "${strs[@]}"
do
    if [ "`eval echo grep -Fxq $i hello-world-apk-overrides/build/intermediates/manifests/full/bar/debug/AndroidManifest.xml`" ];
    then
        # code if found
        echo " PASS - $i found in overrides APK AndroidManifest.xml"
    else
        # code if not found
        echo " FAIL - $i NOT found in overrides APK AndroidManifest.xml"
        exit 1
    fi
done

# END Issue 25


echo "Test passed!"