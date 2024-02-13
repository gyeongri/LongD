#!/bin/bash

# npm start 실행
npm start &

# nginx 실행
nginx -g "daemon off;"
