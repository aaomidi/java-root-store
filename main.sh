#!/usr/bin/env bash

images=("openjdk" "amazoncorretto" "eclipse-temurin")
versions=("19" "20" "21")

for image in "${images[@]}"; do
  for version in "${versions[@]}"; do
    echo "Doing $image:$version"
    docker run --rm -it $(docker build -q --build-arg TYPE=${image} --build-arg VERSION=${version} .)
  done
done
