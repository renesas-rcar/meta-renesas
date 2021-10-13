#!/bin/sh

DIR="/sys/devices/system/cpu/cpufreq/"

COUNT="`ls $DIR |  grep policy  | wc -l`"

var=`ls $DIR | grep policy`
vars=( $var )

while [ $COUNT -gt 0 ]; do

echo performance > /sys/devices/system/cpu/cpufreq/${vars[$COUNT - 1]}/scaling_governor

let COUNT=COUNT-1
done

true
