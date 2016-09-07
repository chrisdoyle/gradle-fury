#!/usr/bin/env bash

sed "s/NEXUS_USERNAME=/NEXUS_USERNAME=admin/g" gradle.properties >  gradle2.properties
rm gradle.properties
mv gradle2.properties gradle.properties

sed "s/NEXUS_PASSWORD\=/NEXUS_PASSWORD=admin123/g" gradle.properties >  gradle2.properties
rm gradle.properties
mv gradle2.properties gradle.properties


sed "s/RELEASE_REPOSITORY_URL\=/RELEASE_REPOSITORY_URL=http:\/\/localhost:8081\/nexus\/content\/repositories\/releases\//g" gradle.properties >  gradle2.properties
rm gradle.properties
mv gradle2.properties gradle.properties

sed "s/SNAPSHOT_REPOSITORY_URL\=/SNAPSHOT_REPOSITORY_URL=http:\/\/localhost:8081\/nexus\/content\/repositories\/snapshots\//g" gradle.properties >  gradle2.properties
rm gradle.properties
mv gradle2.properties gradle.properties
