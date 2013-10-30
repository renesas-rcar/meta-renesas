DESCRIPTION = "khronos header files (http://www.khronos.org/registry/)"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://CL/cl.h;beginline=1;endline=22;md5=688fcb59ec225081cedfaef77315908f"

PV = "20130424"
PR = "r0"

SRC_URI = " \
    http://www.khronos.org/registry/khronos_headers.tgz \
    file://support_arm.patch \
"

SRC_URI[md5sum] = "56ad0e432eb56ba3ff058595eda7fa52"
SRC_URI[sha256sum] = "0e5a8df9fc34cfe88a76d1796e3b5f7384139448a07f2f8819f3f2ebbcdb223e"

S = "${WORKDIR}/khronos_headers"

do_install () {
    install -d ${D}/usr/include
    cp -rf * ${D}/usr/include/.
}

FILES_${PN} += "usr/include/*"
