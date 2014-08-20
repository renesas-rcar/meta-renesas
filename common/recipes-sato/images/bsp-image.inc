# Append more package for multimedia
IMAGE_INSTALL_append += " media-ctl"
IMAGE_INSTALL_append += " libdrm-tests"
IMAGE_INSTALL_append += " bash"
IMAGE_INSTALL_append += " initscripts"

# Append more package for gstreamer1.0 and gstreamer1.0 plugins
IMAGE_INSTALL_append += " gstreamer1.0 \
						  gstreamer1.0-plugins-base \
						  gstreamer1.0-plugins-base-alsa \
						  gstreamer1.0-plugins-base-audioconvert \
						  gstreamer1.0-plugins-base-audioresample \
						  gstreamer1.0-plugins-base-playback \
						  gstreamer1.0-plugins-base-videoconvert \
						  gstreamer1.0-plugins-base-typefindfunctions \
						  gstreamer1.0-plugins-base-videoscale \
						  gstreamer1.0-plugins-base-videorate \
						  gstreamer1.0-plugins-base-videotestsrc \
						  gstreamer1.0-plugins-base-audiotestsrc \
						  gstreamer1.0-plugins-good \
						  gstreamer1.0-plugins-good-audioparsers \
						  gstreamer1.0-plugins-good-autodetect \
						  gstreamer1.0-plugins-good-avi \
						  gstreamer1.0-plugins-good-isomp4 \
						  gstreamer1.0-plugins-good-videocrop \
						  gstreamer1.0-plugins-bad \
						  gstreamer1.0-plugins-bad-asfmux \
						  gstreamer1.0-plugins-bad-fbdevsink \
						  gstreamer1.0-plugins-bad-videoparsersbad \
						  gstreamer1.0-plugins-bad-faad \
						  gstreamer1.0-plugins-bad-mpegtsdemux \
						  gstreamer1.0-plugins-bad-debugutilsbad \
						  gstreamer1.0-plugins-ugly \
						  gstreamer1.0-plugins-ugly-asf \
						"

# Plugins for only X11
IMAGE_INSTALL += '${@base_conditional("WAYLAND_ENABLE", "0", " gstreamer1.0-plugins-base-ximagesink \
															gstreamer1.0-plugins-base-xvimagesink", "", d)}'

# For gles package
IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "rgx", \
 	" gles-kernel-module gles-user-module ", "", d)}'

IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "sgx", \
 	" gles-kernel-module gles-user-module", "", d)}'
