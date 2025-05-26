#!/bin/bash

echo "Gradle 빌드 중..."
./gradlew build || { echo "빌드 실패"; exit 1; }

echo "Docker Compose 재빌드 및 재시작 중..."
docker compose down
docker compose up --build -d

echo "완료!"