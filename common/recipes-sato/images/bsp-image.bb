WAYLAND_ENABLE ?= "0"
require ${@base_conditional("WAYLAND_ENABLE", "1", "recipes-core/images/core-image-minimal.bb", "recipes-sato/images/core-image-sato.bb", d)}

include bsp-image.inc

IMAGE_INSTALL += " \
        libdrm-kms \
        alsa-utils alsa-tools \
"

# for Middle software                                                                                                                                                                        
CHECK_OMXIL_SH_MW = "${USE_RENESAS_MW_VCP1}"                                                                                                                                                 
CHECK_OMXIL_SH_MW += "${USE_RENESAS_MW_VPU5}"                                                                                                                                                
IMAGE_INSTALL_append_armadillo800eva = ' ${@base_contains("CHECK_OMXIL_SH_MW", "1", "omxil-sh", "", d)} \
	gst-plugins-bad-dfbvideosink \
	v4l-utils \
	libshvio \
	libshmeram \
'

