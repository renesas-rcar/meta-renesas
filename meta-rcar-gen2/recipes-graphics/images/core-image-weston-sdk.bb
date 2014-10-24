require recipes-graphics/images/core-image-weston.bb
include core-image-weston.inc

DESCRIPTION = "Image with weston support that includes everything within \
core-image-weston plus meta-toolchain, development headers and libraries to \
form a standalone SDK."

IMAGE_FEATURES += "dev-pkgs tools-sdk tools-debug debug-tweaks ssh-server-openssh"

IMAGE_INSTALL += "kernel-dev \
				  ltp"
