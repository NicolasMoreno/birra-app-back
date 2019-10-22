#!/usr/bin/env bash
./mvnw install dockerfile:build && docker push nicolasmoreno/birraapp-backend:latest
