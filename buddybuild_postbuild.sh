#!/usr/bin/env bash

if [[ "$BUDDYBUILD_BRANCH" = "develop" ]]; then
  # generate code coverage
  ./gradlew clean jacocoTestReport

  # upload jacoco report to codecov
  bash <(curl -s https://codecov.io/bash) 
fi
