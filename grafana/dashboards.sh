#!/bin/bash
curl -X POST 192.168.99.100:31000/api/datasources \
  -d'{"name":"prometheus","type":"prometheus","url":"http://prometheus:9090","access":"proxy","basicAuth":true}' \
  -H "Authorization: Basic YWRtaW46YWRtaW4=" \
  -H "Content-Type: application/json" 
