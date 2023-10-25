SUMMARY = "CPURTT drive Module"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = " \
    file://${S}/GPL-COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://${S}/MIT-COPYING;md5=fea016ce2bdf2ec10080f69e9381d378 \
"

inherit module

RENESAS_CPURTTDRV_URL ?= "git://github.com/renesas-rcar/cpurttdrv3.git;protocol=https"
SRCREV = "898c8dbceed72da895db4071c0e42afe7885b1eb"

SRC_URI = "${RENESAS_CPURTTDRV_URL};nobranch=1"

S = "${WORKDIR}/git"

PR = "r0"
PV = "0.1"

libmoduledir = "${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/"

do_install () {
    install -d ${D}${libmoduledir}
    install -d ${D}${includedir}/kernel-module-cpurttdrv3/v4h
    install -d ${D}${includedir}/kernel-module-cpurttdrv3/v4m

    install -m 644 ${S}/v4h/cpurttmod3_v4h.ko ${D}${libmoduledir}
    install -m 644 ${S}/v4m/cpurttmod3_v4m.ko ${D}${libmoduledir}

    install -m 644 ${S}/v4h/Module.symvers ${D}${includedir}/kernel-module-cpurttdrv3/v4h
    install -m 644 ${S}/v4m/Module.symvers ${D}${includedir}/kernel-module-cpurttdrv3/v4m

    install -d ${D}${prefix}/local/include/cpurttmod3_v4h
    install -d ${D}${prefix}/local/include/cpurttmod3_v4m

    install -m 644 ${S}/v4h/cpurtt_common.h ${D}${prefix}/local/include/cpurttmod3_v4h
    install -m 644 ${S}/v4m/cpurtt_common.h ${D}${prefix}/local/include/cpurttmod3_v4m
}

FILES_${PN}-dev = " \
    ${includedir}/kernel-module-cpurttdrv3 \
    ${prefix}/local/include/cpurttmod3_v4h \
    ${prefix}/local/include/cpurttmod3_v4m \
"

KERNEL_MODULE_PROBECONF += "cpurttmod3_v4h cpurttmod3_v4m"
module_conf_cpurttmod3_v4h = "blacklist cpurttmod3_v4h"
module_conf_cpurttmod3_v4m = "blacklist cpurttmod3_v4m"
