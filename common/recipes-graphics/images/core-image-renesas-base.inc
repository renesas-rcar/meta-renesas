# common
IMAGE_INSTALL_append = " \
	alsa-utils alsa-tools \
	libdrm-tests \
	libdrm-kms \
	media-ctl \
"

# gstreamer 0.10 plugins
IMAGE_INSTALL_append = ' ${@base_contains("USE_GSTREAMER_0_10", "1", " \
	gst-plugins-base-videorate \
	gst-plugins-base-videotestsrc \
	gst-plugins-good \
	gst-plugins-good-isomp4 \
	gst-plugins-good-video4linux2 \
	gst-plugins-good-videocrop \
	gst-plugins-good-audioparsers \
	gst-plugins-bad \
	gst-plugins-bad-asfmux \
	gst-plugins-bad-fbdevsink \
	gst-plugins-bad-h264parse \
	gst-plugins-bad-faad \
	gst-plugins-bad-videoparsersbad \
	gst-plugins-ugly \
	gst-plugins-ugly-asf \
	gst-openmax \
	", "", d)}'

# gstreamer 1.0 plugins
IMAGE_INSTALL_append = ' ${@base_contains("USE_GSTREAMER_1_00", "1", " \
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
	gstreamer1.0-plugins-ugly-asf \
	gstreamer1.0-omx \
	", "", d)}'

# for Middle software
CHECK_OMXIL_SH_MW = "${USE_RENESAS_MW_VCP1}"
CHECK_OMXIL_SH_MW += "${USE_RENESAS_MW_VPU5}"
IMAGE_INSTALL_append_armadillo800eva = ' ${@base_contains("CHECK_OMXIL_SH_MW", "1", "omxil-sh", "", d)} \
	gst-plugins-bad-dfbvideosink \	
	v4l-utils \
	libshvio \
	libshmeram \
' 
