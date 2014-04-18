WAYLAND_ENABLE ?= "0"
require ${@base_conditional("WAYLAND_ENABLE", "1", "recipes-core/images/core-image-minimal.bb", "recipes-sato/images/core-image-sato.bb", d)}

IMAGE_INSTALL += " \
	libdrm-kms \
	alsa-utils alsa-tools \
"
IMAGE_INSTALL += '${@base_conditional("WAYLAND_ENABLE", "1", " \
						gstreamer1.0 \
						gstreamer1.0-plugins-base \
						gstreamer1.0-plugins-good \
						gstreamer1.0-plugins-bad \
						gstreamer1.0-plugins-ugly \
						gstreamer1.0-omx \
						gstreamer1.0-libav \
						", " \
						gst-plugins-base-videorate \
						gst-plugins-base-videotestsrc \
						gst-plugins-good \
						gst-plugins-good-isomp4 \
						gst-plugins-good-video4linux2 \
						gst-plugins-good-videocrop \
						gst-plugins-bad \
						gst-plugins-bad-asfmux \
						gst-plugins-bad-fbdevsink \
						gst-plugins-bad-h264parse \
						gst-plugins-bad-faad \
						gst-plugins-bad-videoparsersbad \
						gst-plugins-ugly \
						gst-plugins-ugly-asf \
						gst-openmax \
						", d)}'

# for Middle software                                                                                                                                                                        
CHECK_OMXIL_SH_MW = "${USE_RENESAS_MW_VCP1}"                                                                                                                                                 
CHECK_OMXIL_SH_MW += "${USE_RENESAS_MW_VPU5}"                                                                                                                                                
IMAGE_INSTALL_append_armadillo800eva = ' ${@base_contains("CHECK_OMXIL_SH_MW", "1", "omxil-sh", "", d)} \
	gst-plugins-bad-dfbvideosink \
	v4l-utils \
	libshvio \
	libshmeram \
'
