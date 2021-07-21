SUMMARY = "CPURTT drive Module"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://${S}/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${S}/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

RENESAS_CPURTTDRV_URL ?= "git://github.com/renesas-rcar/cpurttdrv2.git"
SRCREV = "ae4e9cddfa4c9673f9c96c85d5ffdf4d2e48ed00"

SRC_URI_append = " file://change-module-name.patch"

PR = "r0"
PV = "0.1"

require kernel-module-cpurttdrv.inc

KERNEL_MODULE_PROBECONF += "cpurttmod2"
module_conf_cpurttmod2 = "blacklist cpurttmod2"
