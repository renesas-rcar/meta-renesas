SUMMARY = "Cache Memory Primitive Module"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://${S}/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${S}/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
    "

inherit module

PR = "r0"
PV = "0.1"

SRC_URI = " \
    file://cmem.tar.bz2 \
    "

S = "${WORKDIR}/cmem"

KERNEL_MODULE_AUTOLOAD += "cmemdrv"
KERNEL_MODULE_PROBECONF += "cmemdrv"
module_conf_cmemdrv = "options cmemdrv bsize=0x8000000"
