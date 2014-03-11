require recipes-sato/images/core-image-sato.bb

IMAGE_INSTALL += " \
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
	libdrm-kms \
	alsa-utils alsa-tools \
"
