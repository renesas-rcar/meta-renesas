#!/bin/bash

if find /sys/devices/platform/soc/e65d0000.pcie/ -name nvme* 2> /dev/null | read || find /sys/devices/platform/soc/e65d8000.pcie/ -name nvme* 2> /dev/null | read; then
# Don't use same CPU for HW interrupt and SW read/write
	echo 0 > /sys/block/nvme*/queue/rq_affinity
fi
