# Configure for using linaro toolchain
PREFERRED_VERSION_gcc-cross = "linaro-4.8"
PREFERRED_VERSION_gcc-cross-initial = "linaro-4.8"
PREFERRED_VERSION_gcc-cross-intermediate = "linaro-4.8"
PREFERRED_VERSION_gcc-crosssdk = "linaro-4.8"
PREFERRED_VERSION_gcc-crosssdk-initial = "linaro-4.8"
PREFERRED_VERSION_gcc-crosssdk-intermediate = "linaro-4.8"
PREFERRED_VERSION_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} = "linaro-4.8"

##......................................................................
# Configure for wayland
include ${@'conf/machine/include/wayland.inc' if ${WAYLAND_ENABLE}==1 else 'conf/machine/include/x11.inc'}

# Configure gles features for specific machine
MACHINE_FEATURES_append_lager = "${@' rgx' if ${GLES_ENABLE} == 1 else ''}"
MACHINE_FEATURES_append_koelsch = "${@' sgx' if ${GLES_ENABLE} == 1 else ''}"
MACHINE_FEATURES_append_alt = "${@' sgx' if ${GLES_ENABLE} == 1  else ''}"
