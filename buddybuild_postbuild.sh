#!/usr/bin/env bash

# generate code coverage
./gradlew clean jacocoTestReport

# upload jacoco report to codecov
bash <(curl -s https://codecov.io/bash)
