# In YP2.1.2, nativesdk-wayland is lacking.
RDEPENDS_${PN}_append = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-wayland nativesdk-wayland-dev', '', d)} \
    nativesdk-perl nativesdk-perl-dev \
"
