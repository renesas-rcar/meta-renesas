require image-gst.bb

IMAGE_FEATURES += "dev-pkgs tools-sdk qt4-pkgs \
        tools-debug tools-profile tools-testapps debug-tweaks ssh-server-openssh"

IMAGE_INSTALL += "kernel-dev"

