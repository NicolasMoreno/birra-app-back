#!/usr/bin/env bash
./mvnw install dockerfile:build && docker-compose up
