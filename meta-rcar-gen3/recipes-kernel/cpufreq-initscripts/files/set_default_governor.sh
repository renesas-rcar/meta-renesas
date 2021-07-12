#!/bin/sh

timeout="10"
i="0"

while [ $i -lt $timeout ]
do
	if [ -e  /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor ]
	then
		echo performance > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor
		break
	else
		i=$((i + 1))
		sleep 1
	fi
done
