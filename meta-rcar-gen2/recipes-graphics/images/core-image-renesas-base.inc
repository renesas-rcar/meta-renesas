require ../../include/multimedia-control.inc
require ../../include/gles-control.inc
# common
IMAGE_INSTALL_append_rcar-gen2 = " \
   alsa-utils alsa-tools \
   libdrm-tests \
   libdrm-kms \
   bash \
"

IMAGE_INSTALL_append_rcar-gen2 = " \
    gstreamer1.0-plugins-base-videorate \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-base-audiotestsrc \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-good-videocrop \
    gstreamer1.0-plugins-bad-asfmux \
    gstreamer1.0-plugins-bad-fbdevsink \
    gstreamer1.0-plugins-bad-faad \
    gstreamer1.0-plugins-bad-videoparsersbad \
"

IMAGE_INSTALL_append_rcar-gen2 = '${@base_conditional("USE_GLES", "1", \
    "gles-kernel-module gles-user-module", "", d)}'

# pkg groups for multimedia
IMAGE_INSTALL_append_rcar-gen2 = ' ${@base_contains("USE_MULTIMEDIA", "1", " \
	 packagegroup-rcar-gen2-multimedia \
	", "", d)}'
# pkg groups for multimedia test program
IMAGE_INSTALL_append_rcar-gen2 = ' ${@base_contains("USE_MULTIMEDIA_TEST", "1", " \
	packagegroup-rcar-gen2-multimedia-tp \
	", "", d)}'
# pkg groups for dtv
IMAGE_INSTALL_append_rcar-gen2 = ' ${@base_contains("USE_DTV", "1", " \
	 packagegroup-rcar-gen2-dtv \
	", "", d)}'
# pkg groups for graphic test program
IMAGE_INSTALL_append_rcar-gen2 = ' ${@base_contains("USE_GLES_TEST", "1", " \
	 gles-test-module \
	", "", d)}'
