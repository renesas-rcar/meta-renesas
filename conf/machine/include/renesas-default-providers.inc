# Renesas BSP default providers

PREFERRED_PROVIDER_virtual/kernel ?= "linux-rmobile"
PREFERRED_PROVIDER_linux-libc-headers ?= "linux-libc-headers-rmobile"
PREFERRED_PROVIDER_u-boot ?= "u-boot"

IMAGE_DEVICE_TABLES = "files/device_table-minimal.txt \
		files/device_table_add-rmobile.txt"
