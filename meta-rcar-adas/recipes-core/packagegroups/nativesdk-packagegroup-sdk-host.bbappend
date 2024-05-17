RDEPENDS_${PN} += " \
    nativesdk-cmake \
    nativesdk-bmap-tools \
"

# Do not install QEMU packages, it reduces SDK size
RDEPENDS_${PN}_remove = " \
    nativesdk-qemu \
    nativesdk-qemu-helper \
"
