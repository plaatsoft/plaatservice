#!/bin/bash
kill -9 `ps aux | grep plaatservice | awk '{print $2}'`
