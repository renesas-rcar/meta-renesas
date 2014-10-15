include core-image-renesas-base.inc

# gstreamer 1.0 plugins. waylandsink plugin is provided in gstreamer 1.0 only.
IMAGE_INSTALL_append = ' ${@base_contains("USE_GSTREAMER_1_00", "1", " \
	gstreamer1.0-plugins-bad-waylandsink \
	", "", d)}'

IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "rgx", \
	" gles-kernel-module libegl libegl-dev \
	wayland-wsegl-dev libgbm-dev ldd", "", d)}'

IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "sgx", \
	" gles-kernel-module libegl libegl-dev \
	wayland-wsegl-dev libgbm-dev ldd", "", d)}'
