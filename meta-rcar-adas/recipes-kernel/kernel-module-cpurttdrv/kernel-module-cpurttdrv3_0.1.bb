SUMMARY = "CPURTT drive Module"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://${S}/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${S}/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module

RENESAS_CPURTTDRV_URL ?= "git://github.com/renesas-rcar/cpurttdrv3.git;protocol=https"
SRCREV = "0bd842e7233bb91b4fd4e29d84cf2b6b43dd4475"

SRC_URI = "${RENESAS_CPURTTDRV_URL};nobranch=1"

S = "${WORKDIR}/git"

PR = "r0"
PV = "0.1"

libmoduledir = "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/"

do_install () {
    install -d ${D}${libmoduledir}
    install -d ${D}${includedir}/kernel-module-cpurttdrv3/v4h
    install -m 644 ${S}/v4h/cpurttmod3_v4h.ko ${D}${libmoduledir}
    install -m 644 ${S}/v4h/Module.symvers ${D}${includedir}/kernel-module-cpurttdrv3/v4h

    install -d ${D}${prefix}/local/include/cpurttmod3_v4h
    install -m 644 ${S}/v4h/cpurtt_common.h ${D}${prefix}/local/include/cpurttmod3_v4h
}

FILES_${PN}-dev = " \
    ${includedir}/kernel-module-cpurttdrv3 \
    ${prefix}/local/include/cpurttmod3_v4h \
"

KERNEL_MODULE_PROBECONF += "cpurttmod3_v4h"
module_conf_cpurttmod3_v4h = "blacklist cpurttmod3_v4h"
