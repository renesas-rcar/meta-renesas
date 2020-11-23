DESCRIPTION = "BSP packages (explicitly requested by Renesas)"

LICENSE = "BSD-3-Clause & GPLv2+ & LGPLv2+"

inherit packagegroup

PACKAGES = " \
    packagegroup-bsp-custom \
    packagegroup-bsp-devdbg \
    packagegroup-bsp-utest \
"

# Packages mandatory for BSP (useful for development/debug)
RDEPENDS_packagegroup-bsp-devdbg = " \
    openssh-sftp \
    openssh-sftp-server \
"

# Various packages needed for all boards
RDEPENDS_packagegroup-bsp-custom = " \
    ${@bb.utils.contains("IMAGE_FEATURES", "ssh-server-openssh", "", "dropbear",d)} \
"

# Utest (IMR, IMP, etc demos) related packages
RDEPENDS_packagegroup-bsp-utest = " \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-base-app \
    libdrm \
    libinput \
"
