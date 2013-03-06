require recipes-sato/images/core-image-sato.bb

IMAGE_INSTALL += " \
	libshvio \
	libshmeram \
	gst-plugins-base-videotestsrc \
	gst-plugins-good-isomp4 \
	gst-plugins-good-videocrop \
	gst-plugins-good-video4linux2 \
	gst-plugins-bad \
	gst-plugins-bad-dfbvideosink \
	gst-plugins-bad-h264parse \
	gst-plugins-bad-faad \
	gst-plugins-ugly \
	gst-plugins-ugly-mad \
	gst-plugins-ugly-asf \
	gst-plugins-ugly-mad-dev \
	gst-ffmpeg \
	gst-openmax \
"
