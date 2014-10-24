require recipes-graphics/images/core-image-x11.bb
include core-image-x11.inc

DESCRIPTION = "Image with x11 support that includes everything within \
core-image-x11 plus meta-toolchain, development headers and libraries to \
form a standalone SDK."

IMAGE_FEATURES += "dev-pkgs tools-sdk tools-debug debug-tweaks ssh-server-openssh"

IMAGE_INSTALL += "kernel-dev \
				  ltp"
