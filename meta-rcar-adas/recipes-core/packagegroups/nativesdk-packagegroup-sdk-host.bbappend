RDEPENDS:${PN} += " \
    nativesdk-cmake \
    nativesdk-bmaptool \
"

# Do not install QEMU packages, it reduces SDK size
RDEPENDS:${PN}:remove = " \
    nativesdk-qemu \
    nativesdk-qemu-helper \
"
