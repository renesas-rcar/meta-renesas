include core-image-renesas-base.inc

IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "rgx", \
 	" gles-kernel-module gles-user-module ", "", d)}'

IMAGE_INSTALL_append_rcar-gen2 = '${@base_contains("MACHINE_FEATURES", "sgx", \
 	" gles-kernel-module gles-user-module", "", d)}'

IMAGE_FEATURES += "x11-sato ssh-server-dropbear"

IMAGE_INSTALL += "packagegroup-core-x11-sato-games"
