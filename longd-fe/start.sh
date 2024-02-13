#!/bin/bash

# npm start 실행
npm run build &

# nginx 실행
nginx -g "daemon off;"
