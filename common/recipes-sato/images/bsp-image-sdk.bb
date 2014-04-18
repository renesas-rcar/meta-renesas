WAYLAND_ENABLE ?= "0"
require ${@base_conditional("WAYLAND_ENABLE", "1", "recipes-core/images/core-image-minimal.bb", "recipes-sato/images/core-image-sato-sdk.bb", d)}

IMAGE_FEATURES += '${@base_conditional("WAYLAND_ENABLE", "1", "dev-pkgs tools-sdk \
    tools-debug eclipse-debug tools-profile debug-tweaks ssh-server-openssh", "", d)}'

IMAGE_INSTALL += '${@base_conditional("WAYLAND_ENABLE", "1", "kernel-dev", "", d)}'

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
