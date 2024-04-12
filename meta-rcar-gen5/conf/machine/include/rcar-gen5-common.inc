SOC_FAMILY =. "rcar-gen5:"
require conf/machine/include/soc-family.inc
LINUXLIBCVERSION = "5.10%"

MACHINE_FEATURES = ""

KERNEL_IMAGETYPE = "Image"
IMAGE_FSTYPES += "tar.bz2 cpio.gz ext4"

SERIAL_CONSOLES = "115200;ttySC0"

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"

# u-boot
EXTRA_IMAGEDEPENDS += " u-boot"

# Add variable to Build Configuration in build log
BUILDCFG_VARS_append = " SOC_FAMILY"

# Set Image & SDK name for Gen5
IMAGE_NAME = "${IMAGE_BASENAME}-x5h${IMAGE_VERSION_SUFFIX}"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}-x5h"
SDK_NAME_x5h = "${DISTRO}-${TCLIBC}-${SDKMACHINE}-${IMAGE_BASENAME}-${TUNE_PKGARCH}-x5h"

# Configuration for Rcar GFX
DISTRO_FEATURES_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', 'opengl', '', d)}"

PREFERRED_PROVIDER_virtual/egl = "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', 'gles-user-module', '', d)}"
PREFERRED_PROVIDER_virtual/libgles2 = "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', 'gles-user-module', '', d)}"
BBMASK += "${@bb.utils.contains('MACHINE_FEATURES', 'gsx', '', 'meta-renesas/meta-rcar-gen5/recipes-graphics/mesa/ \
                                meta-renesas/meta-rcar-gen5/recipes-kernel/kernel-module-gles/ \
                                meta-renesas/meta-rcar-gen5/recipes-graphics/gles-user-module/', d)}"
