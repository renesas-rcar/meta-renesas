# In YP2.1.2, nativesdk-wayland is lacking.
RDEPENDS:${PN}:append = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-wayland nativesdk-wayland-dev', '', d)} \
    nativesdk-perl nativesdk-perl-dev \
    nativesdk-perl-modules \
    nativesdk-bmap-tools \
"
